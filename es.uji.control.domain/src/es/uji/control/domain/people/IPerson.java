package es.uji.control.domain.people;

import java.util.List;

public interface IPerson {
	public IPersonIdentifier getId();
	public String getName();
	public String getLastName1();
	public String getLastName2();
	public String getIdentification();
	public List<ILinkage>getLinkages();
	public List<IAccreditation> getAccreditations();
}
