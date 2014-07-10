package uk.me.paulgarner.familyhistory.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.me.paulgarner.familyhistory.model.Citation;
import uk.me.paulgarner.familyhistory.services.CitationService;

import com.google.gson.Gson;

@Controller
public class CitationController {
	
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	CitationService citationService;
	
	@RequestMapping(value = "/citation/{citationId}", method = RequestMethod.GET)
	public @ResponseBody String getCitation(@PathVariable("citationId") int id) {

		logger.info(String.format("getCitation: start of method (%s)", System.currentTimeMillis()));

		String result;
		
		Citation citation = citationService.findCitation(id);
		
    	Gson gson = new Gson();
    	result = gson.toJson(citation);
    	 
		logger.info(String.format("getCitation: end of method (%s)", System.currentTimeMillis()));

		return result;
	}
}
