/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.provider.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.prefs.PreferencesService;

import es.uji.control.domain.provider.service.connectionfactory.IControlConnectionFactory;
import es.uji.control.domain.provider.service.factoryselector.ConnectionFactoryKey;
import es.uji.control.domain.provider.service.factoryselector.IControlConnectionFactorySelector;
import es.uji.control.domain.provider.spi.IControlConnectionFactorySPI;

@Component(name="connectionfactory", immediate=true)
public class ControlComponent implements IControlConnectionFactorySelector {
	
	final static String CURRENT_FACTORY_KEY = "current";
	
	private BundleContext bundlecontext;
	private PreferencesService preferencesService;
	private Map<ConnectionFactoryKey, ConnectionFactoryBucket> factories = new HashMap<>();
	private ConnectionFactoryRegistration registration;
	
	@Activate
	public void activate(ComponentContext componentContext) throws Exception {
		synchronized (this) {
			// Se anota el contexto del bundle
			this.bundlecontext = componentContext.getBundleContext();
			// Se regulariza el registro del servicio
			updateRegistration();
		}
	}

	@Deactivate
	public void deactivate(ComponentContext componentContext) {
		synchronized (this) {
			// Se anota el contexto del bundle
			this.bundlecontext = null;
			// Se regulariza el registro del servicio
			updateRegistration();
		}
	}

	@Reference(policy=ReferencePolicy.STATIC, cardinality=ReferenceCardinality.MANDATORY, name="preferences")
	public void bindPreferences(PreferencesService preferencesService) {
		synchronized (this) {
			// Se anota el servicio de registro
			this.preferencesService = preferencesService;
			// Se regulariza el registro del servicio
			updateRegistration();
		}
	}
	
	public void unbindPreferences(PreferencesService preferencesService) {
		synchronized (this) {
			// Se resetea el servicio de registro
			this.preferencesService = null;
			// Se regulariza el registro del servicio
			updateRegistration();
		}
	}

	@Override
	public Set<ConnectionFactoryKey> getFactoryKeys() {
		synchronized (this) {
			return Collections.unmodifiableSet(factories.keySet());
		}
	}
	
	@Override
	public String getFactoryDescription(ConnectionFactoryKey key) {
		synchronized (this) {
			ConnectionFactoryBucket bucket = factories.get(key);
			return bucket == null ? "Removed Connection Factory" : bucket.getDescription(); 
		}
	}

	@Override
	public ConnectionFactoryKey getCurrentFactoryKey() {
		synchronized (this) {
			return new ConnectionFactoryKey(preferencesService.getSystemPreferences().get(CURRENT_FACTORY_KEY, null));
		}
	}

	@Override
	public void setCurrentFactoryKey(ConnectionFactoryKey key) {
		synchronized (this) {
			preferencesService.getSystemPreferences().put(CURRENT_FACTORY_KEY, key.toString());
		}
	}

	@Reference(policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.OPTIONAL, name="connectionFactorySPI")
	public void addConnectionFactorySPI(IControlConnectionFactorySPI connectionFactorySPI, Map<String,?> properties) {
		// Obtenemos la propiedad para indexar el servicio
		String connectionFactoryKey = (String) properties.get(IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY);
		String connectionFactoryDesc = (String) properties.get(IControlConnectionFactorySPI.CONNECTION_FACTORY_DESCRIPTION);
		if (connectionFactoryKey == null) {
			throw new IllegalStateException(String.format("El servicio '%s' no se ha publicado con la propiedad '%s' informada." , 
					IControlConnectionFactorySPI.class.getSimpleName(), 
					IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY)
				);
		}
		// Se registra el nuevo servicio
		synchronized (this) {
			factories.put(new ConnectionFactoryKey(connectionFactoryKey), new ConnectionFactoryBucket(connectionFactorySPI, connectionFactoryDesc));
			updateRegistration();
		}
	}
	
	public void removeConnectionFactorySPI(IControlConnectionFactorySPI connectionFactorySPI, Map<String,?> properties) {
		// Obtenemos la propiedad para indexar el servicio
		String connectionFactoryKey = (String) properties.get(IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY);
		if (connectionFactoryKey == null) {
			throw new IllegalStateException(String.format("El servicio '%s' no se ha des-publicado con la propiedad '%s' informada." , 
					IControlConnectionFactorySPI.class.getSimpleName(), 
					IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY)
				);
		}
		// Se registra el nuevo servicio
		synchronized (this) {
			if (factories.remove(new ConnectionFactoryKey(connectionFactoryKey)) == null) {
				throw new IllegalStateException(String.format("El servicio '%s' con la propiedad '%s' informada se ha intentado desregistrar pero no consta en el registro." , 
						IControlConnectionFactorySPI.class.getSimpleName(), 
						IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY)
					);
			}
			updateRegistration();
		}
	}
	
	private void updateRegistration() {

		// Se verifica si hay que des-registrar los servicios
		boolean mustDesregister = registration != null && (bundlecontext == null || factories.keySet().contains(registration.getKey()));
		if (mustDesregister) {
			desregisterConnectionFactory();
		}
		
		// Se verifica si hay que registrar los registros
		boolean mustRegister = registration == null && (bundlecontext != null && factories.keySet().contains(getCurrentFactoryKey()));
		if (mustRegister) {
			registerConnectionFactory(factories.get(getCurrentFactoryKey()).getConnectionFactorySPI(), getCurrentFactoryKey());
		}
		
	}
	
	private void registerConnectionFactory(IControlConnectionFactory controlConnectionFactory, ConnectionFactoryKey key) {
		if (registration != null) {
			throw new IllegalStateException(String.format("'%s' ya esta registrado previamente." , registration));
		}
		registration = new ConnectionFactoryRegistration(
				bundlecontext.registerService(IControlConnectionFactory.class, controlConnectionFactory, null), 
				controlConnectionFactory, 
				key
			); 
	}
	
	private void desregisterConnectionFactory() {
		if (registration != null) {
			registration.getRegistration().unregister();;
			registration = null;
		}
	}
	
	final private class ConnectionFactoryBucket {
		
		private IControlConnectionFactorySPI connectionFactorySPI;
		private String description;
		
		public ConnectionFactoryBucket(IControlConnectionFactorySPI connectionFactorySPI, String description) {
			super();
			this.connectionFactorySPI = connectionFactorySPI;
			this.description = description;
		}

		public IControlConnectionFactorySPI getConnectionFactorySPI() {
			return connectionFactorySPI;
		}

		public String getDescription() {
			return description;
		}
		
	}
	
	final private class ConnectionFactoryRegistration {
		
		private ServiceRegistration<IControlConnectionFactory> registration;
		private IControlConnectionFactory service;
		private ConnectionFactoryKey key;
		
		public ConnectionFactoryRegistration(ServiceRegistration<IControlConnectionFactory> registration, IControlConnectionFactory service, ConnectionFactoryKey key) {
			this.registration = registration;
			this.service = service;
			this.key = key;
		}

		public ServiceRegistration<IControlConnectionFactory> getRegistration() {
			return registration;
		}

		public IControlConnectionFactory getService() {
			return service;
		}

		public ConnectionFactoryKey getKey() {
			return key;
		}
		
		@Override
		public String toString() {
			return String.format("%s [%s=%s]", getService().getClass().getSimpleName(), IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY, getKey());
		}
		
	}
}