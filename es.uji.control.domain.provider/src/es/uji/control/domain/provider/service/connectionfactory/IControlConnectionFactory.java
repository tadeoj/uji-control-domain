/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.service.connectionfactory;

import java.util.UUID;

import es.uji.control.domain.provider.service.connectionfactory.IControlConnection;

public interface IControlConnectionFactory {
	abstract UUID getSignature();
	abstract IControlConnection createConnection() throws ControlConnectionException;
}
