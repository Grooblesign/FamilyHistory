package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Source;

public interface SourceService {
	List<Source> findAllSources();
	Source findSource(int id);
}
