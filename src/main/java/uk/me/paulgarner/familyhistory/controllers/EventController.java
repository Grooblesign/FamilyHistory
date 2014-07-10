package uk.me.paulgarner.familyhistory.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.me.paulgarner.familyhistory.model.Event;
import uk.me.paulgarner.familyhistory.services.EventService;

import com.google.gson.Gson;

@Controller
public class EventController {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	EventService eventService;

	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
	public @ResponseBody String getEvent(@PathVariable("eventId") int id) {

		logger.info(String.format("getEvent: start of method (%s)", System.currentTimeMillis()));

		String result;
		
		Event event = eventService.get(id);

    	Gson gson = new Gson();
    	result = gson.toJson(event);
    	 
		logger.info(String.format("getEvent: end of method (%s)", System.currentTimeMillis()));

		return result;
	}
	
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.PUT)
	public @ResponseBody String updateEvent(@PathVariable("eventId") int id, @RequestBody String body) {

		logger.info(String.format("updateEvent: start of method (%s)", System.currentTimeMillis()));
		logger.info(body);
    	
    	Gson gson = new Gson();
    	Event event = gson.fromJson(body, Event.class);

    	event.setId(id);
    	
		boolean result = eventService.update(event);
    	
		logger.info(String.format("updateEvent: end of method (%s)", System.currentTimeMillis()));

		return result == true ? "{ result: true }": "{ result: false }";
	}
}
