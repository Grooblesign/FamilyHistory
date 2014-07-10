package uk.me.paulgarner.familyhistory.services;

import java.util.List;

import uk.me.paulgarner.familyhistory.model.Person;

public interface PersonService {
	List<Person> findAllPersons();
	public Person findPerson(int personId);
	List<Person> findAllChildrenOfPerson(int id);
	int addPerson(Person person);
	String findPersonBirthdate(Integer id);
}
