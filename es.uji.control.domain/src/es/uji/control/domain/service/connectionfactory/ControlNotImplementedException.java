/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.service.connectionfactory;

public class ControlNotImplementedException extends Exception {

	private static final long serialVersionUID = -664857390469990142L;

	public ControlNotImplementedException(String message) {
		super(message);
	}
	
}
