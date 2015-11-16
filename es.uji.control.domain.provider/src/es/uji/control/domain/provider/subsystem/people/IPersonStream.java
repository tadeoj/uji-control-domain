/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.subsystem.people;

import java.util.List;

import es.uji.control.domain.people.IPerson;

public interface IPersonStream {
	 void onNext(List<IPerson> persons);
	 void onError(Throwable t);
	 void onCompleted();
}
