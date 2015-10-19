package es.uji.control.domain.spi;

import es.uji.control.domain.service.connectionfactory.IControlConnectionFactory;

public interface IControlConnectionFactorySPI extends IControlConnectionFactory {
	static final String CONNECTION_FACTORY_KEY = "CONNECTION_FACTORY_KEY";
	static final String CONNECTION_FACTORY_DESCRIPTION = "CONNECTION_FACTORY_DESCRIPTION";
}