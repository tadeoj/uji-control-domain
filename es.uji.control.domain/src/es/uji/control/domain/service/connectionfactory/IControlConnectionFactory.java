package es.uji.control.domain.service.connectionfactory;

import es.uji.control.domain.service.connectionfactory.IControlConnection;

public interface IControlConnectionFactory {
	abstract IControlConnection createConnection() throws ControlConnectionException;
}
