package es.uji.control.domain.people.impl;

import java.util.Date;

import es.uji.control.domain.people.OriginEnum;

public class Card extends Accreditation {

	private long id;
	private long personId;
	private String description;
	private long serialNumber;
	private Date creationDate;
	private Date emisionDate;
	private Date cancelationDate;
	private OriginEnum origin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getPersonId() {
		return personId;
	}

	@Override
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public long getSerialNumber() {
		return serialNumber;
	}

	@Override
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEmisionDate() {
		return emisionDate;
	}

	public void setEmisionDate(Date emisionDate) {
		this.emisionDate = emisionDate;
	}

	public Date getCancelationDate() {
		return cancelationDate;
	}

	public void setCancelationDate(Date cancelationDate) {
		this.cancelationDate = cancelationDate;
	}

	public OriginEnum getOrigin() {
		return origin;
	}

	public void setOrigin(OriginEnum origin) {
		this.origin = origin;
	}

}
