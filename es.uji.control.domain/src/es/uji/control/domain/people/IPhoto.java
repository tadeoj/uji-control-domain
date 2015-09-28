package es.uji.control.domain.people;

import java.util.Date;

public interface IPhoto {
	public IPersonIdentifier getPersonIdentifier();
	public Date getDate();
	public byte[] getImage();
}
