/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.subsystem.people;

import java.util.Set;

import es.uji.control.domain.service.connectionfactory.ControlConnectionException;

public interface IPersonService {
	
	public IPersonStream getAllPersons() throws ControlConnectionException; 

	public IPersonStream getPerson(IPersonIdentifier personIdentifier) throws ControlConnectionException;

	public IPhotoStream getAllPhotos() throws ControlConnectionException;
	
	public Set<ILinkage> getLinkages() throws ControlConnectionException;
	
}
