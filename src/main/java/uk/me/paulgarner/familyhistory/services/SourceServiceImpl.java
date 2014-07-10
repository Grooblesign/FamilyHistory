package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.me.paulgarner.familyhistory.dao.SourceDao;
import uk.me.paulgarner.familyhistory.model.Source;

public class SourceServiceImpl implements SourceService {

	@Autowired
	SourceDao sourceDao;
	
	@Override
	public List<Source> findAllSources() {
		return sourceDao.findAllSources();
	}

	@Override
	public Source findSource(int id) {
		return sourceDao.findSource(id);
	}

}
