package es.uji.control.domain.internal;

import es.uji.control.domain.IControlConnectionFactorySelector;

@Component(name="connectionfactory", immediate=true)
public class ControlComponent implements IControlConnectionFactorySelector {

	@Override
	public String getCurrentFactory() {
		return null;
	}

	@Override
	public void setCurrentFactory() {
	}

}
