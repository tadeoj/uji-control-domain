package es.uji.control.domain;

import es.uji.control.domain.IControlConnection;

public interface IControlConnectionFactory {
	abstract IControlConnection createConnection() throws ControlConnectionException;
}
