package uk.me.paulgarner;

import junit.framework.TestCase;

import org.jmock.Mockery;

import uk.me.paulgarner.familyhistory.model.Person;

public class TestPerson extends TestCase {
	Mockery context = new Mockery();
	
	public void test1() {
		final Person person = context.mock(Person.class);
		System.out.println(person.getId());
	}
}
