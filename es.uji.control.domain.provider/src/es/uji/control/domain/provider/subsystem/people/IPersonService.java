/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.subsystem.people;

import es.uji.control.domain.people.IAccreditation;
import es.uji.control.domain.people.IPersonIdentifier;
import es.uji.control.domain.provider.service.connectionfactory.ControlConnectionException;

public interface IPersonService {
	
	public void getAllPersons(IPersonStream personStream) throws ControlConnectionException; 

	public void getPerson(IAccreditation accreditation, IPersonStream personStream) throws ControlConnectionException;
	
	public void getAllPhotos(IPhotoStream photoStream) throws ControlConnectionException;
	
	public void getPhoto(IPersonIdentifier personIdentifier, IPhotoStream photoStream) throws ControlConnectionException;
	
}
