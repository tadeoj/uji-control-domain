/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.service.connectionfactory;

public class ControlConnectionException extends Exception {

	private static final long serialVersionUID = -2193253742266299523L;

	public ControlConnectionException(String message) {
		super(message);
	}
	
	public ControlConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
