/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.people;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IPhoto extends Serializable {
	public IPersonIdentifier getPersonIdentifier();
	public LocalDateTime getDate();
	public byte[] getImage();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
