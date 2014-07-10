package uk.me.paulgarner.familyhistory.model;

import javax.persistence.Entity;

@Entity
public class Person {
	private Integer id = null;
	private int fatherId = 0;
	private String gender;
	
	private String forenames = null;
	private int motherId = 0;
	private String surname = null;

	private Person father;
	private Person mother;
	
	private String birthDate;
	private String birthPlace;
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullname() {
		return String.format("%s %s", forenames, surname).trim();
	}
	
	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getMotherId() {
		return motherId;
	}

	public void setMotherId(int motherId) {
		this.motherId = motherId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setForenames(String forenames) {
		this.forenames = forenames;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}
	
	public String getForenames() {
		return forenames;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	public String getBirthPlace() {
		return this.birthPlace;
	}
}
