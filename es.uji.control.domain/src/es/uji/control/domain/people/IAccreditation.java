package es.uji.control.domain.people;

import java.util.Date;

public interface IAccreditation {
	public AccreditationType getType();
	public String getExtraType();
	public String getRaw();
	public Date getEmisionDate();
	public Date getCancelationDate();
}
