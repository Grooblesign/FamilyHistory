package uk.me.paulgarner.familyhistory.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.me.paulgarner.familyhistory.model.Source;
import uk.me.paulgarner.familyhistory.services.SourceService;

import com.google.gson.Gson;

@Controller
public class SourceController {

	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	SourceService sourceService;
	
	@RequestMapping(value = "/source/{sourceId}", method = RequestMethod.GET)
	public @ResponseBody String getSource(@PathVariable("sourceId") int id) throws Exception {

		logger.info(String.format("getSource: start of method (%s)", System.currentTimeMillis()));

		String result = null;
		
		Source source = sourceService.findSource(id);
		
    	if (source != null) {
    		Gson gson = new Gson();
    		result = gson.toJson(source);
    	} else {
    		throw new Exception(String.format("Source %s not found", id));
    	}
    	
		logger.info(String.format("getSource: end of method (%s)", System.currentTimeMillis()));
		logger.info(result);
		
		return result;
	}
	
}
