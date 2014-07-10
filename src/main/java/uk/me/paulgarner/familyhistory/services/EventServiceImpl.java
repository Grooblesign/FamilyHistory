package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.me.paulgarner.familyhistory.dao.EventDao;
import uk.me.paulgarner.familyhistory.model.Event;

public class EventServiceImpl implements EventService {

	@Autowired
	EventDao eventDao;
	
	@Override
	public Event get(int id) {
		
		return eventDao.find(id);
	}

	@Override
	public List<Event> findAllForPerson(int personId) {
		
		return eventDao.findAllForPerson(personId);
	}

	@Override
	public List<Event> findAllOfTypeForPerson(String type, int personId) {
		
		return eventDao.findAllOfTypeForPerson(type, personId);
	}

	@Override
	public List<Event> findAllOfType(String type) {
		return eventDao.findAllOfType(type);
	}

	@Override
	public boolean update(Event event) {
		return eventDao.update(event);
	}
}
