package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import uk.me.paulgarner.familyhistory.model.Person;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class PersonDaoImpl implements PersonDao {

	static Logger logger = Logger.getLogger("uk.me.paulgarner");	
	
	@Override
	public Person find(int id) {
		logger.info("findPerson: start of method");
		
		Person person = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM person WHERE id=%s", id));
			
			if (rs.next()) {		
				person = new Person();
				person.setId(id);
				person.setFatherId(rs.getInt("fatherid"));
				person.setForenames(rs.getString("forenames").trim());
				person.setMotherId(rs.getInt("motherid"));
				person.setSurname(rs.getString("surname").trim());
				person.setGender(rs.getString("gender").trim());
			}
			
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		logger.info("findPerson: end of method");
		
		return person;
	}

	@Override
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT forenames, id, surname FROM person ORDER BY surname, forenames");
			while (rs.next()) {
				Person person = new Person();
				
				person.setId(rs.getInt("id"));
				person.setSurname(rs.getString("surname"));
				person.setForenames(rs.getString("forenames"));
				
				persons.add(person);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return persons;
	}

	@Override
	public List<Person> findChildren(int id) {
		
		List<Person> persons = new ArrayList<Person>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT id FROM person WHERE fatherid=%s OR motherid=%s ORDER BY surname, forenames", id, id));
			while (rs.next()) {
				Person person = this.find(rs.getInt("id"));
				persons.add(person);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return persons;
	}

	@Override
	public int add(Person person) {

		logger.info("method entry");
		
		int result = 0;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			// st.execute(String.format("INSERT INTO person (forenames, gender, surname) VALUES ('%s', '%s','%s') RETURNING id", person.getForenames(), person.getGender(), person.getSurname()));
			ResultSet rs = st.executeQuery(String.format("INSERT INTO person (forenames, gender, surname) VALUES ('%s', '%s','%s') RETURNING id", person.getForenames(), person.getGender(), person.getSurname()));

			if (rs.next()) {
				result = rs.getInt("id");
			}
			
			rs.close();

			st.close();
			connection.close();
		} catch (SQLException exception) {
			logger.info(exception.getMessage());
		}
		
		return result;
	}
}
