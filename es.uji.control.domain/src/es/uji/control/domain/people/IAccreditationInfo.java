package es.uji.control.domain.people;

import java.time.LocalDateTime;

public interface IAccreditationInfo {
	public LocalDateTime getEmisionDate();
	public LocalDateTime getCancelationDate();
	public IAccreditation getAccreditation();
}
