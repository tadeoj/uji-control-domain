/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.service.factoryselector;

final public class ConnectionFactoryKey {
	
	private String connectionFactoryKey;
	
	public ConnectionFactoryKey(String connectionFactoryKey) {
		if (connectionFactoryKey != null) {
			this.connectionFactoryKey = connectionFactoryKey.toUpperCase().trim();
		}
	}
	
	@Override
	public String toString() {
		return this.connectionFactoryKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionFactoryKey == null) ? 0 : connectionFactoryKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionFactoryKey other = (ConnectionFactoryKey) obj;
		if (connectionFactoryKey == null) {
			if (other.connectionFactoryKey != null)
				return false;
		} else if (!connectionFactoryKey.equals(other.connectionFactoryKey))
			return false;
		return true;
	}

}
