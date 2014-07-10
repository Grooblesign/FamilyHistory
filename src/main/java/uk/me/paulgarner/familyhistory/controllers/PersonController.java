package uk.me.paulgarner.familyhistory.controllers;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.Event;
import uk.me.paulgarner.familyhistory.model.Marriage;
import uk.me.paulgarner.familyhistory.model.Person;
import uk.me.paulgarner.familyhistory.model.Source;
import uk.me.paulgarner.familyhistory.services.CensusService;
import uk.me.paulgarner.familyhistory.services.EventService;
import uk.me.paulgarner.familyhistory.services.MarriageService;
import uk.me.paulgarner.familyhistory.services.PersonService;
import uk.me.paulgarner.familyhistory.services.SourceService;
import uk.me.paulgarner.familyhistory.util.DateComparator;

import com.google.gson.Gson;

@Controller
public class PersonController {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	// @Autowired
	@Inject
	PersonService personService;

	// @Autowired
	@Inject
	MarriageService marriageService;
	
	// @Autowired
	@Inject
	EventService eventService;
	
	// @Autowired
	@Inject
	CensusService censusService;
	
	// @Autowired
	@Inject
	SourceService sourceService;
	
	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public @ResponseBody String addPperson(@RequestBody String body) {
    	Gson gson = new Gson();
    	Person person = gson.fromJson(body, Person.class);
    	
    	int id = personService.addPerson(person);
    	
    	String result = String.format("%s", id);
    	
        return result;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public @ResponseBody String getAllPersons(@RequestBody String body) {
		logger.info(String.format("getAllPersons: method entry (%s)", System.currentTimeMillis()));
		
		List<Person> persons = personService.findAllPersons();
		
		List<Event> birthEvents = eventService.findAllOfType("Birth");
		List<Event> baptismEvents = eventService.findAllOfType("Baptism");
		
		for (Person person : persons) {
			List<Event> births = filter(having(on(Event.class).getPersonId(), equalTo(person.getId())), birthEvents);  
					
			String birthDate = "";
			String birthPlace = "";
			
			for (Event event : births) {
				if (event.isPrimary()) {
					birthDate = event.getDate();
					birthPlace = event.getLocation();
					break;
				}
				if (birthDate.length() == 0) {
					birthDate = event.getDate();
					birthPlace = event.getLocation();
				}
			}
			
			if (birthDate.trim().length() == 0) {
				List<Event> baptisms = filter(having(on(Event.class).getPersonId(), equalTo(person.getId())), baptismEvents);  
				
				for (Event event : baptisms) {
					if (event.isPrimary()) {
						birthDate = event.getDate() + " (bap)";
						birthPlace = event.getLocation();
						break;
					}
					if (birthDate.length() == 0) {
						birthDate = event.getDate() + " (bap)";
						birthPlace = event.getLocation();
					}
				}
			}
			
			person.setBirthDate(birthDate);
			person.setBirthPlace(birthPlace);
		}
		
		logger.info(String.format("getAllPersons: persons = %s", persons.size()));

		logger.info(String.format("getAllPersons: method exit (%s)", System.currentTimeMillis()));

    	Gson gson = new Gson();
    	String result = gson.toJson(persons);

    	return result;
	}
	
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
	public String getPperson(Model model, @PathVariable("personId") int id) {
		
		logger.info(String.format("getPerson: start of method (%s)", System.nanoTime()/1000000));

		if (logger.isDebugEnabled()) {
			logger.debug("A debug message");
		}
		
		Person person = personService.findPerson(id);
		
		model.addAttribute("person", person);
		model.addAttribute("forenames", person.getForenames());
		model.addAttribute("surname", person.getSurname());

		person.setFather(personService.findPerson(person.getFatherId()));
		person.setMother(personService.findPerson(person.getMotherId()));
		
		logger.info(String.format("getPerson: children (%s)", System.nanoTime()/1000000));

		List<Person> children = personService.findAllChildrenOfPerson(id);
		for (Person child: children) {
			child.setFather(personService.findPerson(child.getFatherId()));
			child.setMother(personService.findPerson(child.getMotherId()));
			
			child.setBirthDate(personService.findPersonBirthdate(child.getId()));
		}
		model.addAttribute("children", children);
		
		logger.info(String.format("getPerson: marriages (%s)", System.nanoTime()/1000000));

		List<Marriage> marriages = marriageService.findAllMarriagesForPerson(id);
		Collections.sort(marriages, new DateComparator());
		
		for (Marriage marriage: marriages) {
			if (person.getGender().equals("F")) {
				if (marriage.getHusbandId() > 0) {
					marriage.setHusband(personService.findPerson(marriage.getHusbandId()));
				}
			} else {
				if (marriage.getWifeId() > 0) {
					marriage.setWife(personService.findPerson(marriage.getWifeId()));
				}
			}
		}
		model.addAttribute("marriages", marriages);

		logger.info(String.format("getPerson: events (%s)", System.nanoTime()/1000000));

		List<Event> events = eventService.findAllForPerson(id);
		Collections.sort(events, new DateComparator());
		model.addAttribute("events", events);

		logger.info(String.format("getPerson: census (%s)", System.nanoTime()/1000000));

		List<CensusEntry> censusEntries = censusService.findAllCensusEntriesForPerson(id);
		Collections.sort(censusEntries, new DateComparator());
		model.addAttribute("censusEntries", censusEntries);
		
		List<Source> sources = sourceService.findAllSources();
		model.addAttribute("sources", sources);
				
		logger.info(String.format("getPerson: end of method (%s)", System.nanoTime()/1000000));

		return "person";
	}
	
	@ModelAttribute("spouse")
	public String getSpouse() {
		return "Spouse Name";
	}
}
