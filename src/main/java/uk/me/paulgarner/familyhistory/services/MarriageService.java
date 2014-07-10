package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Marriage;

public interface MarriageService {
	List<Marriage> findAllMarriagesForPerson(int personId);
}
