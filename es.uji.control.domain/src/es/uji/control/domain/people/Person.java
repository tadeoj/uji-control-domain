package es.uji.control.domain.people;

import java.util.List;

import es.uji.control.domain.accreditations.Accreditation;

public class Person {

	private long id;
	private String name;
	private String lastName1;
	private String lastName2;
	private String identification;
	private List<Linkage> linkages;
	private Photo photo;
	private List<Accreditation> accreditations;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public List<Linkage> getLinkages() {
		return linkages;
	}

	public void setLinkages(List<Linkage> linkages) {
		this.linkages = linkages;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public List<Accreditation> getAccreditations() {
		return accreditations;
	}

	public void setAccreditations(List<Accreditation> accreditations) {
		this.accreditations = accreditations;
	}
	
}
