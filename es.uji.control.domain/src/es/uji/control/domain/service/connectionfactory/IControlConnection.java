/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.service.connectionfactory;

import es.uji.control.domain.subsystem.authorizations.IAuthorizationsService;
import es.uji.control.domain.subsystem.people.IPersonService;

public interface IControlConnection {
	abstract IPersonService getPersonService() throws ControlNotImplementedException;
	abstract IAuthorizationsService getAuthorizationsService() throws ControlNotImplementedException;
	abstract void close();
}
