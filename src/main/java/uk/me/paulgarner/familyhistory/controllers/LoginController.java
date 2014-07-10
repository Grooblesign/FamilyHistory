package uk.me.paulgarner.familyhistory.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@RequestMapping(value="/login2", method = RequestMethod.GET)
	public String login(ModelMap model) {
		logger.info("login2");
		return "loginpage";
 	}
}
