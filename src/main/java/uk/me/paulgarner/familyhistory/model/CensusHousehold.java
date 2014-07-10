package uk.me.paulgarner.familyhistory.model;

public class CensusHousehold {
	private int id;
	private int censusId;
	private String address = "";
	private String piece = "";
	private String folio = "";
	private String page = "";
	private String notes = "";
	
	private String head = "";
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getReference() {
		return this.piece + "/" + this.folio + "/" + this.page;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getCensusId() {
		return censusId;
	}
	public void setCensusId(int censusId) {
		this.censusId = censusId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
