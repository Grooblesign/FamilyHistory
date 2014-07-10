package uk.me.paulgarner.familyhistory.services;

import org.springframework.beans.factory.annotation.Autowired;

import uk.me.paulgarner.familyhistory.dao.CitationDao;
import uk.me.paulgarner.familyhistory.model.Citation;

public class CitationServiceImpl implements CitationService {

	@Autowired
	CitationDao citationDao;
	
	@Override
	public Citation findCitation(int id) {
		
		return citationDao.findCitation(id);
	}

}
