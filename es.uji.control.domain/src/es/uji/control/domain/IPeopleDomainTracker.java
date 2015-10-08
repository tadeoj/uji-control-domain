package es.uji.control.domain;

public interface IPeopleDomainTracker {
	abstract void availableConnectorionFactory(IPeopleDomainConnectionFactory factory);
	abstract void close();
}
