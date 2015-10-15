package es.uji.control.domain.subsystem.people;

import java.io.Serializable;
import java.util.Date;

public interface IAccreditation extends Serializable {
	public AccreditationType getType();
	public String getExtraType();
	public String getRaw();
	public Date getEmisionDate();
	public Date getCancelationDate();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
