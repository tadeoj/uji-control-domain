package es.uji.control.domain.spi;

import es.uji.control.domain.IPeopleDomainConnectionFactory;

public interface IPeopleDomainConnectionFactorySPI extends IPeopleDomainConnectionFactory {
	
	static final String CONNECTION_FACTORY_KEY = "CONNECTION_FACTORY_KEY";
	static final String CONNECTION_FACTORY_DESCRIPTION = "CONNECTION_FACTORY_DESCRIPTION";
	
	abstract boolean hasPropertiesChanged();
	abstract void reset();
	
}
