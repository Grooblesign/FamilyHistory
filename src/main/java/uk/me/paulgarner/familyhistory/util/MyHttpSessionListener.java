package uk.me.paulgarner.familyhistory.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.me.paulgarner.familyhistory.controllers.HomeController;

public class MyHttpSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		logger.info("Session created - " + sessionEvent.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		logger.info("Session destroyed - " + sessionEvent.getSession().getId());
	}

}
