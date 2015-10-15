package es.uji.control.domain;

public interface IControlConnectionFactorySelector {
	public abstract ConnectionFactoryKey getCurrentFactoryKey();
	public abstract void setCurrentFactoryKey(ConnectionFactoryKey key);
}
