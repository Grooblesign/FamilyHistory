package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;
import uk.me.paulgarner.familyhistory.model.Person;
import uk.me.paulgarner.familyhistory.services.PersonService;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class CensusDaoImpl implements CensusDao {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	PersonService personService;

	@Override
	public List<Census> findAllCensuses() {
		
		List<Census> censuses = new ArrayList<Census>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM census ORDER BY id");
			
			while (rs.next()) {
				censuses.add(this.findCensus(rs.getInt("id")));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return censuses;
	}
	
	@Override
	public List<CensusEntry> findAllCensusEntriesForPerson(int personId) {
		
		List<CensusEntry> censusEntries = new ArrayList<CensusEntry>();
		
		List<Census> censuses = findAllCensuses();
		
		for (Census census : censuses) {
			CensusEntry censusEntry = this.findCensusEntryForPerson(census.getId(), personId);
			
			if (censusEntry != null) {
				censusEntries.add(censusEntry);
			}
		}
		
		return censusEntries;
	}

	@Override
	public CensusEntry findCensusEntryForPerson(int censusId, int personId) {
		
		CensusEntry censusEntry = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM censushouseholdperson as chp, censushousehold, census WHERE personid=%s AND censusid=census.id AND censushouseholdid=censushousehold.id AND census.id=%s", personId, censusId));
			
			if (rs.next()) {
				censusEntry = new CensusEntry();
				
				censusEntry.setCensusId(censusId);
				
				censusEntry.setCensusHouseholdId(rs.getInt("censushouseholdid"));
				
				censusEntry.setAddress(rs.getString("address"));
				censusEntry.setAge(rs.getString("age"));
				censusEntry.setBirthplace(rs.getString("birthplace"));
				censusEntry.setDate(rs.getString("date"));
				censusEntry.setFolio(rs.getString("folio"));
				censusEntry.setName(rs.getString("name"));
				censusEntry.setOccupation(rs.getString("occupation"));
				censusEntry.setPage(rs.getString("page"));
				censusEntry.setPiece(rs.getString("piece"));
				censusEntry.setRelationshipToHead(rs.getString("relationshiptohead"));
				censusEntry.setStatus(rs.getString("status"));
				censusEntry.setTitle(rs.getString("title"));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
				
		return censusEntry;
	}

	@Override
	public Census findCensus(int censusId) {

		Census census = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM census WHERE id=%s", censusId));
			
			if (rs.next()) {
				census = new Census();
				
				census.setId(censusId);
				census.setDate(rs.getString("date"));
				census.setTitle(rs.getString("title"));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return census;
	}

	@Override
	public CensusHousehold findCensusHouseholdForPerson(int censusId, int personId) {
		
		CensusHousehold censusHousehold = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM censushousehold, census WHERE personid=%s AND censusid=census.id AND cemsus.id=%s", personId, censusId));
			
			if (rs.next()) {
				censusHousehold = new CensusHousehold();
			}
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return censusHousehold;
	}

	@Override
	public List<CensusEntry> findAllCensusEntriesForHousehold(int censusHouseholdId) {
		
		List<CensusEntry> censusEntries = new ArrayList<CensusEntry>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM censushouseholdperson WHERE censushouseholdid=%s", censusHouseholdId));
			
			while (rs.next()) {
				CensusEntry censusEntry = new CensusEntry();
				
				censusEntry.setCensusHouseholdId(rs.getInt("censushouseholdid"));
				
				censusEntry.setCensusHouseholdPersonId(rs.getInt("id"));

				censusEntry.setAge(rs.getString("age"));
				censusEntry.setBirthplace(rs.getString("birthplace"));
				censusEntry.setName(rs.getString("name"));
				censusEntry.setOccupation(rs.getString("occupation"));
				censusEntry.setPersonId(rs.getInt("personid"));
				censusEntry.setRelationshipToHead(rs.getString("relationshiptohead"));
				censusEntry.setStatus(rs.getString("status"));
				
				if (censusEntry.getName().length() == 0 && censusEntry.getPersonId() > 0) {
					Person person = personService.findPerson(censusEntry.getPersonId());
					censusEntry.setName(person.getFullname());
				}
				
				censusEntries.add(censusEntry);
			}
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		System.out.println(censusHouseholdId);
		System.out.println(censusEntries.size());
		
		return censusEntries;
	}

	@Override
	public CensusHousehold findCensusHousehold(int censusHouseholdId) {
		
		CensusHousehold censusHousehold = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM censushousehold WHERE id=%s", censusHouseholdId));
			
			if (rs.next()) {
				censusHousehold = new CensusHousehold();
				
				censusHousehold.setAddress(rs.getString("address"));
				censusHousehold.setCensusId(rs.getInt("censusid"));
				censusHousehold.setFolio(rs.getString("folio"));
				censusHousehold.setId(censusHouseholdId);
				censusHousehold.setNotes(rs.getString("notes"));
				censusHousehold.setPage(rs.getString("page"));
				censusHousehold.setPiece(rs.getString("piece"));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		return censusHousehold;
	}

	@Override
	public int addCensusHouseholdEntry(CensusEntry entry) {

		logger.info("method entry");
		
		int result = 0;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("INSERT INTO censushouseholdperson (censushouseholdid, personid, name, relationshiptohead, age, status, occupation, birthplace) VALUES (%s, %s, '%s', '%s','%s', '%s', '%s', '%s') RETURNING id", entry.getCensusHouseholdId(), entry.getPersonId(), entry.getName(), entry.getRelationshipToHead(), entry.getAge(), entry.getStatus(), entry.getOccupation(), entry.getBirthplace()));
			
			if (rs.next()) {
				result = rs.getInt("id");
			}

		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public int addCensusHousehold(CensusHousehold censusHousehold) {

		logger.info("addCensusHousehold(): method entry");

		int result = 0;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("INSERT INTO censushousehold (address, censusid, folio, page, piece) VALUES ('%s', %s, '%s', '%s', '%s') RETURNING id", censusHousehold.getAddress(), censusHousehold.getCensusId(), censusHousehold.getFolio(), censusHousehold.getPage(), censusHousehold.getPiece()));

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

	@Override
	public List<CensusHousehold> findAllCensusHouseholds() {
		
		logger.info("findAllCensusHousehold(): method entry");

		List<CensusHousehold> censusHouseholds = new ArrayList<CensusHousehold>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM censushousehold ORDER BY censusid, address");
			
			while (rs.next()) {
				CensusHousehold censusHousehold = new CensusHousehold();
				
				censusHousehold.setAddress(rs.getString("address"));
				censusHousehold.setCensusId(rs.getInt("censusid"));
				censusHousehold.setFolio(rs.getString("folio"));
				censusHousehold.setId(rs.getInt("id"));
				censusHousehold.setPage(rs.getString("page"));
				censusHousehold.setPiece(rs.getString("piece"));
				
				censusHouseholds.add(censusHousehold);
			}
			rs.close();
			
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}

		logger.info("findAllCensusHousehold(): method exit");

		return censusHouseholds;
	}

	@Override
	public boolean updateCensusHouseholdEntry(CensusEntry entry) {

		boolean result = true;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			st.execute(String.format("UPDATE censushouseholdperson SET personid = %s, name = '%s', relationshiptohead = '%s', age = '%s', status = '%s', occupation = '%s', birthplace = '%s' WHERE id = %s", entry.getPersonId(), entry.getName(), entry.getRelationshipToHead(), entry.getAge(), entry.getStatus(), entry.getOccupation(), entry.getBirthplace(), entry.getCensusHouseholdPersonId()));
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
			result = false;
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean deleteCensusHouseholdEntry(int censusHouseholdPersonId) {
		boolean result = true;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			st.execute(String.format("DELETE FROM censushouseholdperson WHERE id = %s", censusHouseholdPersonId));
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
			result = false;
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			result = false;
		}
		
		return result;
	}
}
