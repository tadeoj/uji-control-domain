package es.uji.control.domain;

import es.uji.control.domain.IPeopleDomainConnection;

public interface IPeopleDomainConnectionFactory {
	abstract IPeopleDomainConnection createConnection() throws PeopleDomainConnectionException;
}
