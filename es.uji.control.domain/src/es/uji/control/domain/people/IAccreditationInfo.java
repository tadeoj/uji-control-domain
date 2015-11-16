package es.uji.control.domain.people;

import java.util.Date;

public interface IAccreditationInfo {
	public Date getEmisionDate();
	public Date getCancelationDate();
	public IAccreditation getAccreditation();
}
