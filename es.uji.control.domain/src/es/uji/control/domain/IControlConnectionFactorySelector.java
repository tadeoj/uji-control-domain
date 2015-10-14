package es.uji.control.domain;

public interface IControlConnectionFactorySelector {
	public abstract String getCurrentFactory();
	public abstract void setCurrentFactory();
}
