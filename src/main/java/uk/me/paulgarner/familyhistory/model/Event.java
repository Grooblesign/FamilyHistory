package uk.me.paulgarner.familyhistory.model;

public class Event extends AbstractDateModel {

	private int id;
	private int citationId;
	private String details;
	private boolean isPrimary;
	private String location;
	private int personId;
	private String type;

	public int getCitationId() {
		return citationId;
	}

	public void setCitationId(int citationId) {
		this.citationId = citationId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;	
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
