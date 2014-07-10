package uk.me.paulgarner.familyhistory.dao;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;

public interface CensusDao {
	List<CensusEntry> findAllCensusEntriesForHousehold(int censusHouseholdId);
	List<Census> findAllCensuses();
	Census findCensus(int censusId);
	CensusEntry findCensusEntryForPerson(int censusId, int personId);
	List<CensusEntry> findAllCensusEntriesForPerson(int personId);
	CensusHousehold findCensusHousehold(int censusHouseholdId);
	CensusHousehold findCensusHouseholdForPerson(int censusId, int personId);
	int addCensusHouseholdEntry(CensusEntry entry);
	int addCensusHousehold(CensusHousehold censusHousehold);
	List<CensusHousehold> findAllCensusHouseholds();
	boolean updateCensusHouseholdEntry(CensusEntry entry);
	boolean deleteCensusHouseholdEntry(int censusHouseholdPersonId);
}
