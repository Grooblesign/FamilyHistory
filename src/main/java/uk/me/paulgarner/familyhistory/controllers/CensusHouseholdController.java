package uk.me.paulgarner.familyhistory.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;
import uk.me.paulgarner.familyhistory.services.CensusService;

import com.google.gson.Gson;

@Controller
public class CensusHouseholdController {

	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	CensusService censusService;
	
	@RequestMapping(value = "/census/{censusId}/censushousehold", method = RequestMethod.PUT)
	public @ResponseBody String addCensusHousehold(@RequestBody String body) {

		logger.info("addCensusHousehold(): method entry");
		logger.info(body);

		String result = "{ result: -1 }";
		
		try {
			Gson gson = new Gson();
			CensusHousehold censusHousehold = gson.fromJson(body, CensusHousehold.class);

			if (censusService.addCensusHousehold(censusHousehold) > 0) {
				result = String.format("{ result: %s }", result);
			}
		} catch (Exception exception) {
			logger.info(exception.getMessage());
		}
		
		return result;
	}
	
    @RequestMapping(value = "census/{censusId}/censushousehold/{censusHouseholdId}/person", method = RequestMethod.PUT)
    public @ResponseBody String addPerson(@PathVariable("censusId") int censusId, @PathVariable("censusHouseholdId") int censusHouseholdId, @RequestBody String body) {

    	logger.info("addPerson(): method entry");
		logger.info(body);
		
		String result = "{result: -1}";

		try {
			Gson gson = new Gson();
			CensusEntry entry = gson.fromJson(body, CensusEntry.class);
    	
			entry.setCensusHouseholdId(censusHouseholdId);
    	
			int newId = censusService.addCensusHouseholdEntry(entry);
			
			result = String.format(result = "{result: %s}", newId);
		} catch (Exception exception) {
			logger.info(exception.getMessage());
		}
    	
    	logger.info("addPerson(): result = " + result);
    	logger.info("addPerson(): method exit");

    	return result;
    }

    @RequestMapping(value = "/census/{censusId}/censushousehold/{censusHouseholdId}", method = RequestMethod.GET)
	public String findCensusHousehold(Model model, @PathVariable("censusId") int censusId, @PathVariable("censusHouseholdId") int censusHouseholdId) {
		CensusHousehold censusHousehold  = censusService.findCensusHousehold(censusHouseholdId);
		model.addAttribute("censusHousehold", censusHousehold);
		
		Census census = censusService.findCensus(censusHousehold.getCensusId());
		model.addAttribute("census", census);
		
		List<CensusEntry> censusHouseholdEntries = censusService.findAllCensusEntriesForHousehold(censusHouseholdId);
		model.addAttribute("censusHouseholdEntries", censusHouseholdEntries);
		
		return "censushousehold";
	}
	
    @RequestMapping(value = "census/{censusId}/censushousehold/{censusHouseholdId}/person/{censusHouseholdPersonId}", method = RequestMethod.POST)
    public @ResponseBody String updatePerson(@PathVariable int censusHouseholdPersonId, @RequestBody String body) {

    	logger.info("updatePerson(): method entry");
		logger.info(body);
    	
    	Gson gson = new Gson();
    	CensusEntry entry = gson.fromJson(body, CensusEntry.class);
    	
    	entry.setCensusHouseholdPersonId(censusHouseholdPersonId);
    	
    	boolean result = censusService.updateCensusHouseholdEntry(entry);
    	
        return result == true ? "{ result: true }": "{ result: false }";
    }
    
    @RequestMapping(value = "census/{censusId}/censushousehold/{censusHouseholdId}/person/{censusHouseholdPersonId}", method = RequestMethod.DELETE)
    public @ResponseBody String deletePerson(@PathVariable int censusHouseholdPersonId, @RequestBody String body) {

    	logger.info("deletePerson(): method entry");
    	
    	boolean result = censusService.deleteCensusHouseholdEntry(censusHouseholdPersonId);
    	
    	logger.info("deletePerson(): method exit");

    	return result == true ? "{ result: true }": "{ result: false }";
    }
}
