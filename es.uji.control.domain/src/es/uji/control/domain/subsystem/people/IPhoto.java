package es.uji.control.domain.subsystem.people;

import java.io.Serializable;
import java.util.Date;

public interface IPhoto extends Serializable {
	public IPersonIdentifier getPersonIdentifier();
	public Date getDate();
	public byte[] getImage();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
