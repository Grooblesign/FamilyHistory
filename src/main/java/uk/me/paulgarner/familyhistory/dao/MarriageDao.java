package uk.me.paulgarner.familyhistory.dao;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Marriage;

public interface MarriageDao {
	List<Marriage> findAllMarriagesForPerson(int personId);
}
