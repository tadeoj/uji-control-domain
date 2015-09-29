package es.uji.control.domain.people;

import java.io.Serializable;

public interface IPersonIdentifier extends Serializable {
	public PersonIdentifierType getType();
	public String getRaw();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
