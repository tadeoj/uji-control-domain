package es.uji.control.domain.subsystem.people;

import java.io.Serializable;
import java.util.List;

public interface IPerson extends Serializable {
	public IPersonIdentifier getId();
	public String getName();
	public String getFirstLastName();
	public String getSecondLastName();
	public String getIdentification();
	public List<ILinkage>getLinkages();
	public List<IAccreditation> getAccreditations();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
