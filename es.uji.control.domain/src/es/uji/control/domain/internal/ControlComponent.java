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

import es.uji.control.domain.IControlConnectionFactory;
import es.uji.control.domain.IControlConnectionFactorySelector;
import es.uji.control.domain.spi.IConnectionFactorySPI;

@Component(name="connectionfactory", immediate=true)
public class ControlComponent implements IControlConnectionFactorySelector {
	
	final static String CURRENT_FACTORY_KEY = "current";
	
	private BundleContext bundlecontext;
	private PreferencesService preferencesService;
	private ServiceRegistration<IControlConnectionFactory> registration;
	private Map<ConnectionFactoryKey, IConnectionFactorySPI> factories = new HashMap<>();
	
	@Activate
	public void activate(ComponentContext componentContext) throws Exception {
		// Se anota el contexto del bundle
		this.bundlecontext = componentContext.getBundleContext();
		// Se regulariza el registro del servicio
		synchronized (this) {
			updateRegistration();
		}
	}

	@Deactivate
	public void deactivate(ComponentContext componentContext) {
		// Se anota el contexto del bundle
		this.bundlecontext = null;
		// Se regulariza el registro del servicio
		synchronized (this) {
			updateRegistration();
		}
	}

	@Reference(policy=ReferencePolicy.STATIC, cardinality=ReferenceCardinality.MANDATORY, name="preferences")
	public void bindPreferences(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
		// Se regulariza el registro del servicio
		synchronized (this) {
			updateRegistration();
		}
	}
	
	public void unbindPreferences(PreferencesService preferencesService) {
		this.preferencesService = null;
	}

	@Override
	public String getCurrentFactory() {
		synchronized (this) {
			return preferencesService.getSystemPreferences().get(CURRENT_FACTORY_KEY, null);
		}
	}

	@Override
	public void setCurrentFactory(String factoryKey) {
		synchronized (this) {
			preferencesService.getSystemPreferences().put(CURRENT_FACTORY_KEY, factoryKey);
		}
	}

	@Reference(policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.OPTIONAL, name="connectionFactorySPI")
	public void addConnectionFactorySPI(IConnectionFactorySPI connectionFactorySPI, Map<String,?> properties) {
		// Obtenemos la propiedad para indexar el servicio
		String connectionFactoryKey = (String) properties.get(IConnectionFactorySPI.CONNECTION_FACTORY_KEY);
		if (connectionFactoryKey == null) {
			throw new IllegalStateException(String.format("El servicio '%s' no se ha publicado con la propiedad '%s' informada." , 
					IConnectionFactorySPI.class.getSimpleName(), 
					IConnectionFactorySPI.CONNECTION_FACTORY_KEY)
				);
		}
		// Se registra el nuevo servicio
		synchronized (connectionFactoryKey) {
			factories.put(new ConnectionFactoryKey(connectionFactoryKey), connectionFactorySPI);
			updateRegistration();
		}
	}
	
	public void removeConnectionFactorySPI(IConnectionFactorySPI connectionFactorySPI, Map<String,?> properties) {
		// Obtenemos la propiedad para indexar el servicio
		String connectionFactoryKey = (String) properties.get(IConnectionFactorySPI.CONNECTION_FACTORY_KEY);
		if (connectionFactoryKey == null) {
			throw new IllegalStateException(String.format("El servicio '%s' no se ha des-publicado con la propiedad '%s' informada." , 
					IConnectionFactorySPI.class.getSimpleName(), 
					IConnectionFactorySPI.CONNECTION_FACTORY_KEY)
				);
		}
		// Se registra el nuevo servicio
		synchronized (connectionFactoryKey) {
			if (factories.remove(new ConnectionFactoryKey(connectionFactoryKey)) == null) {
				throw new IllegalStateException(String.format("El servicio '%s' con la propiedad '%s' informada se ha intentado desregistrar pero no consta en el registro." , 
						IConnectionFactorySPI.class.getSimpleName(), 
						IConnectionFactorySPI.CONNECTION_FACTORY_KEY)
					);
			}
			updateRegistration();
		}
	}
	
	private void updateRegistration() {
		
	}
	
	private void registerConnectionFactory(IControlConnectionFactory controlConnectionFactory) {
		if (registration != null) {
			throw new IllegalStateException(String.format("El servicio '%s' ya esta registrado previamente." , IControlConnectionFactory.class.getSimpleName()));
		}
		registration = bundlecontext.registerService(IControlConnectionFactory.class, controlConnectionFactory, null);
	}
	
	private void desregisterConnectionFactory() {
		if (registration != null) {
			registration.unregister();
			registration = null;
		}
	}
	
}
