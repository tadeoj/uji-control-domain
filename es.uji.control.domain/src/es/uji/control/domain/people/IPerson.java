package es.uji.control.domain.people;

import java.util.List;

public interface IPerson {
	public IPersonIdentifier getId();
	public String getName();
	public String getFirstLastName();
	public String getSecondLastName();
	public String getIdentification();
	public List<ILinkage>getLinkages();
	public List<IAccreditation> getAccreditations();
}
