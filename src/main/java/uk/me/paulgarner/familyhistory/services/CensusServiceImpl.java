package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import uk.me.paulgarner.familyhistory.dao.CensusDao;
import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;
import uk.me.paulgarner.familyhistory.model.Person;

public class CensusServiceImpl implements CensusService {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Autowired
	CensusDao censusDao;
	
	@Autowired
	PersonService personService;
	
	@Override
	public CensusEntry findCensusEntryForPerson(int censusId, int personId) {
		return censusDao.findCensusEntryForPerson(censusId, personId);
	}

	@Override
	public Census findCensus(int censusId) {
		return censusDao.findCensus(censusId);
	}

	@Override
	public List<CensusEntry> findAllCensusEntriesForPerson(int personId) {
		return censusDao.findAllCensusEntriesForPerson(personId);
	}

	@Override
	public List<CensusEntry> findAllCensusEntriesForHousehold(int censusHouseholdId) {
		
		return censusDao.findAllCensusEntriesForHousehold(censusHouseholdId); 
	}

	@Override
	public CensusHousehold findCensusHousehold(int censusHouseholdId) {
		
		return censusDao.findCensusHousehold(censusHouseholdId);
	}

	@Override
	public int addCensusHouseholdEntry(CensusEntry entry) {
		
		return censusDao.addCensusHouseholdEntry(entry);
	}

	@Override
	public List<Census> findAllCensuses() {
		return censusDao.findAllCensuses();
	}

	@Override
	public int addCensusHousehold(CensusHousehold censusHousehold) {
		return censusDao.addCensusHousehold(censusHousehold);
	}

	@Override
	public List<CensusHousehold> findAllCensusHouseholds() {
		logger.info("CensusServiceImpl.findAllCensusHousehold(): method entry");

		List<CensusHousehold> censusHouseholds = censusDao.findAllCensusHouseholds();
		
		for (CensusHousehold censusHousehold : censusHouseholds) {
			List<CensusEntry> entries = censusDao.findAllCensusEntriesForHousehold(censusHousehold.getId());
			
			for (CensusEntry entry : entries) {
				if (entry.getRelationshipToHead().toLowerCase().equals("head")) {
					logger.info("CensusServiceImpl.findAllCensusHousehold(): name = " + entry.getName());
					
					String head = entry.getName();
					
					if (head.isEmpty()) {
						logger.info("CensusServiceImpl.findAllCensusHousehold(): personid = " + entry.getPersonId());
						if (entry.getPersonId() > 0) {
							Person person = personService.findPerson(entry.getPersonId());
							
							if (person != null) {
								head = person.getFullname();
							}
						}
					}
					
					censusHousehold.setHead(head);
					break;
				}
			}
		}
		logger.info("CensusServiceImpl.findAllCensusHousehold(): method exit");

		return censusHouseholds;
	}

	@Override
	public boolean updateCensusHouseholdEntry(CensusEntry entry) {
		return censusDao.updateCensusHouseholdEntry(entry);
	}

	@Override
	public boolean deleteCensusHouseholdEntry(int censusHouseholdPersonId) {
		return censusDao.deleteCensusHouseholdEntry(censusHouseholdPersonId);
	}

}
