package uk.me.paulgarner.familyhistory.dao;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Person;

public interface PersonDao {
	Person find(int id);
	List<Person> findAll();
	List<Person> findChildren(int id);
	int add(Person person);
}
