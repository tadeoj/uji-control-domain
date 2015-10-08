package es.uji.control.domain.jdbc.internal;

import java.util.ArrayList;

import es.uji.control.domain.IPeopleDomainConnection;
import es.uji.control.domain.PeopleDomainConnectionFactoryException;
import es.uji.control.domain.PeopleNotImplementedException;
import es.uji.control.domain.authorizations.IAuthorizationsService;
import es.uji.control.domain.jdbc.IJdbcPeopleDomainConnectionGetter;
import es.uji.control.domain.jdbc.internal.authorizations.AuthorizationsImpl;
import es.uji.control.domain.jdbc.internal.people.PersonImpl;
import es.uji.control.domain.people.IPersonService;
import es.uji.control.domain.spi.IPeopleDomainConnectionFactorySPI;

public class JdbcConnectionFactory implements IPeopleDomainConnectionFactorySPI {

	private IJdbcPeopleDomainConnectionGetter globalConfig;
	private GetterImpl factoryConfig;
	
	private ArrayList<PeopleDomainConnection> connections = new ArrayList<>();
	
	public JdbcConnectionFactory(IJdbcPeopleDomainConnectionGetter config) {
		this.globalConfig = config;
		this.factoryConfig = new GetterImpl(globalConfig);
	}

	@Override
	public IPeopleDomainConnection createConnection() throws PeopleDomainConnectionFactoryException {
		synchronized (this) {
			PeopleDomainConnection newConnection = new PeopleDomainConnection();
			connections.add(newConnection);
			return newConnection;
		}
	}

	@Override
	public boolean hasPropertiesChanged() {
		synchronized (this) {
			GetterImpl localConfig = new GetterImpl(globalConfig);
			return !localConfig.equals(factoryConfig);
		}
	}

	@Override
	public void reset() {
		synchronized (this) {
			// Se cierran las conexiones activas
			connections.forEach(c->c.reset());
			// Se refrescan las propiedades de la conexion.
			this.factoryConfig = new GetterImpl(globalConfig);
		}
	}
	
	private class PeopleDomainConnection implements IPeopleDomainConnection {
		
		PersonImpl personImpl;
		AuthorizationsImpl authorizationsImpl;
		
		public PeopleDomainConnection() throws PeopleDomainConnectionFactoryException {
			
			// TODO: Se establece la conexiom..
			
			// TODO: Se crean las implementaciones de los subservicios
			personImpl = new PersonImpl(); // TODO: Hay que proporcionar al constructor los recursos de la conexion...
			authorizationsImpl = new AuthorizationsImpl();  // TODO: Hay que proporcionar al constructor los recursos de la conexion...
			
		}

		@Override
		public IPersonService getPersonService() throws PeopleNotImplementedException {
			if (personImpl == null) {
				throw new PeopleNotImplementedException("No implementado en este conector.");
			} else {
				return personImpl;
			}
		}

		@Override
		public IAuthorizationsService getAuthorizationsService() throws PeopleNotImplementedException {
			if (authorizationsImpl == null) {
				throw new PeopleNotImplementedException("No implementado en este conector.");
			} else {
				return authorizationsImpl;
			}
		}

		@Override
		public void reset() {
			
			// TODO: Se cierra la conexion...
			
			synchronized (JdbcConnectionFactory.this) {
				connections.remove(this);
			}
			
		}
		
	}

}
