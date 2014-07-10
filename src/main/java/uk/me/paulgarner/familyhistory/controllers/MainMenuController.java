package uk.me.paulgarner.familyhistory.controllers;

import java.util.List;
import java.util.Locale;

import org.neo4j.graphdb.RelationshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;
import uk.me.paulgarner.familyhistory.model.Source;
import uk.me.paulgarner.familyhistory.services.CensusService;
import uk.me.paulgarner.familyhistory.services.EventService;
import uk.me.paulgarner.familyhistory.services.PersonService;
import uk.me.paulgarner.familyhistory.services.SourceService;

@Controller
public class MainMenuController {
	
	@Autowired
	CensusService censusService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	SourceService sourceService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static enum RelTypes implements RelationshipType
	{
	    FATHER_OF,
	    MOTHER_OF,
	    SON_OF,
	    DAUGHTER_OF,
	    HUSBAND_OF,
	    WIFE_OF
	}
	
	public MainMenuController() {
		logger.info("MainMenuController constructor - " + this.hashCode());
	}
	 
	@RequestMapping(value = "/addcensushousehold", method = RequestMethod.GET)
	public String addCensusHousehold(Locale locale, Model model) {
				
		logger.info("method entry");
	
		List<Census> censusList = censusService.findAllCensuses();
		
		model.addAttribute("censuses", censusList	);

		return "newcensushousehold";
	}

	@RequestMapping(value = "/addperson", method = RequestMethod.GET)
	public String addPerson(Locale locale, Model model) {
				
		logger.info("method entry");
		
		return "newperson";
	}
	
	@RequestMapping(value = "/censushouseholdindex", method = RequestMethod.GET)
	public String censushouseholdIndex(Locale locale, Model model) {
				
		logger.info("method entry");
		
		List<Census> censuses = censusService.findAllCensuses();

		List<CensusHousehold> censusHouseholds = censusService.findAllCensusHouseholds();
		
		model.addAttribute("censuses", censuses);
		model.addAttribute("censusHouseholds", censusHouseholds);
		
		return "censushouseholdindex";
	}
	
	@RequestMapping(value = "/personindex", method = RequestMethod.GET)
	public String personIndex(Locale locale, Model model) {
				
		logger.info(String.format("personIndex: method entry (%s)", System.currentTimeMillis()));
		
		logger.info("MainMenuController id - " + this.hashCode());

		logger.info(String.format("personIndex: method exit (%s)", System.currentTimeMillis()));

		return "personindex";
	}
	
	@RequestMapping(value = "/sourceindex", method = RequestMethod.GET)
	public String sourceIndex(Locale locale, Model model) {
				
		logger.info(String.format("sourceIndex: method entry (%s)", System.currentTimeMillis()));
		logger.info("MainMenuController id - " + this.hashCode());
		
		List<Source> sources = sourceService.findAllSources();
	
		model.addAttribute("sources", sources);
		
		logger.info(String.format("sourceIndex: method exit (%s)", System.currentTimeMillis()));

		return "sourceindex";
	}
}
