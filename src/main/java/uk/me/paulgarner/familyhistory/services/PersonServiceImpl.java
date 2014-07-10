package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.me.paulgarner.familyhistory.dao.PersonDao;
import uk.me.paulgarner.familyhistory.model.Event;
import uk.me.paulgarner.familyhistory.model.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	EventService eventService;
	
	@Autowired
	PersonDao personDao;
	
	public PersonServiceImpl() {
		logger.info("PersonServiceImpl: constructor");
	}
	
	@Override
	public Person findPerson(int personId) {
		Person person = personDao.find(personId);

		return person;
	}

	@Override
	public List<Person> findAllChildrenOfPerson(int id) {
		List<Person> persons = personDao.findChildren(id);
		
		return persons;
	}

	@Override
	public List<Person> findAllPersons() {
		List<Person> persons = personDao.findAll();
		
		return persons;
	}

	@Override
	public int addPerson(Person person) {

		return personDao.add(person);
	}

	@Override
	public String findPersonBirthdate(Integer id) {
		String birthDate = "";
		
		List<Event> birthEvents = eventService.findAllOfTypeForPerson("Birth",  id);
		if (birthEvents.size() > 0) {
			for (Event event : birthEvents) {
				if (event.isPrimary()) {
					birthDate = event.getDate();
					break;
				}
				if (birthDate.length() == 0) {
					birthDate = event.getDate();
				}
			}
		} else {
			List<Event> baptismEvents = eventService.findAllOfTypeForPerson("Baptism",  id);
			for (Event event : baptismEvents) {
				if (event.isPrimary()) {
					birthDate = event.getDate() + " (bap)";
					break;
				}
				if (birthDate.length() == 0) {
					birthDate = event.getDate() + " (bap)";
				}
			}
		}
		
		return birthDate;
	}

}
