package uk.me.paulgarner.familyhistory.model;

public class CensusEntry extends AbstractDateModel {
	private int censusId;
	private int personId;
	private int censusHouseholdId;
	private int censusHouseholdPersonId;
	
	private String title = "";
	private String address = "";
	private String piece = "";
	private String folio = "";
	private String page = "";
	
	private String name = "";
	private String relationshipToHead = "";
	private String age = "";
	private String status = "";
	private String occupation = "";
	private String birthplace = "";

	public int getCensusId() {
		return censusId;
	}
	public void setCensusId(int censusId) {
		this.censusId = censusId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getCensusHouseholdId() {
		return censusHouseholdId;
	}
	public void setCensusHouseholdId(int censusHouseholdId) {
		this.censusHouseholdId = censusHouseholdId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationshipToHead() {
		return relationshipToHead;
	}
	public void setRelationshipToHead(String relationshipToHead) {
		this.relationshipToHead = relationshipToHead;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public int getCensusHouseholdPersonId() {
		return censusHouseholdPersonId;
	}
	public void setCensusHouseholdPersonId(int censusHouseholdPersonId) {
		this.censusHouseholdPersonId = censusHouseholdPersonId;
	}
}
