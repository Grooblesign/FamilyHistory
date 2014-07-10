package uk.me.paulgarner.familyhistory.model;

public class Marriage extends AbstractDateModel {
	
	private int id;
	private int husbandId;
	private int wifeId;
	private String location;
	
	private Person husband;
	private Person wife;
	
	public Person getHusband() {
		return husband;
	}
	public void setHusband(Person husband) {
		this.husband = husband;
	}
	public Person getWife() {
		return wife;
	}
	public void setWife(Person wife) {
		this.wife = wife;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHusbandId() {
		return husbandId;
	}
	public void setHusbandId(int husbandId) {
		this.husbandId = husbandId;
	}
	public int getWifeId() {
		return wifeId;
	}
	public void setWifeId(int wifeId) {
		this.wifeId = wifeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
