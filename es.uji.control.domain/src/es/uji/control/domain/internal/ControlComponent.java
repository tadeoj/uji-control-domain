package es.uji.control.domain.internal;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import es.uji.control.domain.IControlConnectionFactorySelector;
import es.uji.control.domain.spi.IConnectionFactorySPI;

@Component(name="connectionfactory", immediate=true)
public class ControlComponent implements IControlConnectionFactorySelector {

	@Activate
	public void activate(ComponentContext componentContext) throws Exception {
	}

	@Deactivate
	public void deactivate(ComponentContext componentContext) {
	}

	@Override
	public String getCurrentFactory() {
		return null;
	}

	@Override
	public void setCurrentFactory(String factoryKey) {
	}

	@Reference(policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.OPTIONAL)
	public void bindDao(IConnectionFactorySPI connectionFactorySPI) {
	}
	
	public void unbindDao(IConnectionFactorySPI connectionFactorySPI) {
	}
	
}
