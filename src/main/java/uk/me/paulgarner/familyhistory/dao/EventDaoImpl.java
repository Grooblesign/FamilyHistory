package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import uk.me.paulgarner.familyhistory.model.Event;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class EventDaoImpl implements EventDao {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Override
	public Event find(int id) {
		
		Event event = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM event WHERE id=%s", id));
			if (rs.next()) {
				event = new Event();
				
				event.setId(rs.getInt("id"));
				event.setDate(rs.getString("date").trim());
				event.setDetails(rs.getString("details").trim());
				event.setLocation(rs.getString("location").trim());
				event.setType(rs.getString("type").trim());
				event.setCitationId(rs.getInt("citationid"));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return event;
	}

	@Override
	public List<Event> findAllForPerson(int personId) {
		
		List<Event> events = new ArrayList<Event>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM event WHERE personid=%s ORDER BY id", personId));
			while (rs.next()) {
				Event event = new Event();
				
				event.setId(rs.getInt("id"));
				event.setDate(rs.getString("date").trim());
				event.setDetails(rs.getString("details").trim());
				event.setLocation(rs.getString("location").trim());
				event.setType(rs.getString("type").trim());
						
				events.add(event);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return events;
	}

	@Override
	public List<Event> findAllOfTypeForPerson(String type, int personId) {

		logger.info(String.format("getAllEventsOfTypeForPerson: method entry (%s)", System.currentTimeMillis()));
		
		List<Event> events = new ArrayList<Event>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			logger.info(String.format("getAllEventsOfTypeForPerson: stage 1 (%s)", System.currentTimeMillis()));

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT id, date, details, location, type, isprimary FROM event WHERE type='%s' AND personid=%s ORDER BY id", type, personId));
			while (rs.next()) {
				Event event = new Event();
				
				event.setId(rs.getInt("id"));
				event.setDate(rs.getString("date").trim());
				event.setDetails(rs.getString("details").trim());
				event.setLocation(rs.getString("location").trim());
				event.setType(rs.getString("type").trim());
				event.setPrimary(rs.getBoolean("isprimary"));
						
				events.add(event);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
	
		logger.info(String.format("getAllEventsOfTypeForPerson: stage 2 (%s)", System.currentTimeMillis()));

		ConnectionFactory.closeConnection(connection);
		
		logger.info(String.format("getAllEventsOfTypeForPerson: method exit (%s)", System.currentTimeMillis()));

		return events;
	}

	@Override
	public List<Event> findAllOfType(String type) {
		logger.info(String.format("getAllEventsOfType: method entry (%s)", System.currentTimeMillis()));
		
		List<Event> events = new ArrayList<Event>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			logger.info(String.format("getAllEventsOfType: stage 1 (%s)", System.currentTimeMillis()));

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT id, date, details, location, personid, type, isprimary FROM event WHERE type='%s' ORDER BY personid", type));
			while (rs.next()) {
				Event event = new Event();
				
				event.setId(rs.getInt("id"));
				event.setDate(rs.getString("date").trim());
				event.setDetails(rs.getString("details").trim());
				event.setLocation(rs.getString("location").trim());
				event.setPersonId(rs.getInt("personid"));
				event.setType(rs.getString("type").trim());
				event.setPrimary(rs.getBoolean("isprimary"));
						
				events.add(event);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
	
		logger.info(String.format("getAllEventsOfType: stage 2 (%s)", System.currentTimeMillis()));

		ConnectionFactory.closeConnection(connection);
		
		logger.info(String.format("getAllEventsOfType: method exit (%s)", System.currentTimeMillis()));

		return events;
	}

	@Override
	public boolean update(Event event) {
		boolean result = true;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			st.execute(String.format("UPDATE event SET date = '%s', details = '%s', location = '%s' WHERE id = %s", event.getDate(), event.getDetails(), event.getLocation(), event.getId()));
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
			result = false;
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			result = false;
		}
		
		ConnectionFactory.closeConnection(connection);

		return result;
	}
}
