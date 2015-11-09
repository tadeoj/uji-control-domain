/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.service.factoryselector;

import java.util.Set;

public interface IControlConnectionFactorySelector {
	public abstract Set<ConnectionFactoryKey> getFactoryKeys();
	public abstract String getFactoryDescription(ConnectionFactoryKey key);
	public abstract ConnectionFactoryKey getCurrentFactoryKey();
	public abstract void setCurrentFactoryKey(ConnectionFactoryKey key);
}
