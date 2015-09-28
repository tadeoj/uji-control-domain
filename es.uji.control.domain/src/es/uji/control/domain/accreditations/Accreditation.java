package es.uji.control.domain.accreditations;

public abstract class Accreditation {

	public abstract long getPersonId();

	public abstract void setPersonId(long personId);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract long getSerialNumber();

	public abstract void setSerialNumber(long serialNumber);

}
