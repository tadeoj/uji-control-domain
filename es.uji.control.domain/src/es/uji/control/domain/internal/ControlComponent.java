package es.uji.control.domain.internal;

import java.util.HashMap;
import java.util.Map;

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

import es.uji.control.domain.ConnectionFactoryKey;
import es.uji.control.domain.IControlConnectionFactory;
import es.uji.control.domain.IControlConnectionFactorySelector;
import es.uji.control.domain.spi.IControlConnectionFactorySPI;

@Component(name="connectionfactory", immediate=true)
public class ControlComponent implements IControlConnectionFactorySelector {
	
	final static String CURRENT_FACTORY_KEY = "current";
	
	private BundleContext bundlecontext;
	private PreferencesService preferencesService;
	private Map<ConnectionFactoryKey, IControlConnectionFactorySPI> factories = new HashMap<>();
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
		if (connectionFactoryKey == null) {
			throw new IllegalStateException(String.format("El servicio '%s' no se ha publicado con la propiedad '%s' informada." , 
					IControlConnectionFactorySPI.class.getSimpleName(), 
					IControlConnectionFactorySPI.CONNECTION_FACTORY_KEY)
				);
		}
		// Se registra el nuevo servicio
		synchronized (this) {
			factories.put(new ConnectionFactoryKey(connectionFactoryKey), connectionFactorySPI);
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
		boolean mustRegister = registration == null && (bundlecontext != null || factories.keySet().contains(getCurrentFactoryKey()));
		if (mustRegister) {
			registerConnectionFactory(factories.get(getCurrentFactoryKey()), getCurrentFactoryKey());
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
	
	private class ConnectionFactoryRegistration {
		
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
