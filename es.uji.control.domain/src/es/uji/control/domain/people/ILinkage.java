package es.uji.control.domain.people;

import java.io.Serializable;

public interface ILinkage extends Serializable {
	public String getName(); 
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
