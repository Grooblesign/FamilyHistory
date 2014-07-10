package uk.me.paulgarner.familyhistory.dao;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Source;

public interface SourceDao {
	List<Source> findAllSources();
	Source findSource(int id);
}
