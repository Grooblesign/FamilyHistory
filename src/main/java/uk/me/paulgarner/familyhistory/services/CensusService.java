package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Census;
import uk.me.paulgarner.familyhistory.model.CensusEntry;
import uk.me.paulgarner.familyhistory.model.CensusHousehold;

public interface CensusService {
	List<CensusEntry> findAllCensusEntriesForHousehold(int censusHouseholdId);
	List<CensusEntry> findAllCensusEntriesForPerson(int personId);
	Census findCensus(int censusId);
	CensusEntry findCensusEntryForPerson(int censusId, int personId);
	CensusHousehold findCensusHousehold(int censusHouseholdId);
	int addCensusHouseholdEntry(CensusEntry entry);
	List<Census> findAllCensuses();
	int addCensusHousehold(CensusHousehold censusHousehold);
	List<CensusHousehold> findAllCensusHouseholds();
	boolean updateCensusHouseholdEntry(CensusEntry entry);
	boolean deleteCensusHouseholdEntry(int censusHouseholdPersonId);
}
