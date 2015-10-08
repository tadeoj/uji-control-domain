package es.uji.control.domain;

public interface IPeopleDomainTracker {
	abstract void opennedConnectorionFactory(IPeopleDomainConnectionFactory factory);
	abstract void close();
}
