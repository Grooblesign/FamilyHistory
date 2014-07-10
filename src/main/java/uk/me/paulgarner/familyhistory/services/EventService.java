package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Event;

public interface EventService {
	Event get(int id);
	List<Event> findAllForPerson(int personId);
	List<Event> findAllOfTypeForPerson(String type, int personId);
	List<Event> findAllOfType(String type);
	boolean update(Event event);
}
