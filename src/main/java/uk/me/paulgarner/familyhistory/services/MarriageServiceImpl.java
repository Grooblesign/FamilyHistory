package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.me.paulgarner.familyhistory.dao.MarriageDao;
import uk.me.paulgarner.familyhistory.model.Marriage;

@Service("marriageService")
public class MarriageServiceImpl implements MarriageService {

	@Autowired
	MarriageDao marriageDao;
	
	@Override
	public List<Marriage> findAllMarriagesForPerson(int personId) {
		return marriageDao.findAllMarriagesForPerson(personId);
	}
}
