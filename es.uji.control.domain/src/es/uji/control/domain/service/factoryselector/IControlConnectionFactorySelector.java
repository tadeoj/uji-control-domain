package es.uji.control.domain.service.factoryselector;

public interface IControlConnectionFactorySelector {
	public abstract ConnectionFactoryKey getCurrentFactoryKey();
	public abstract void setCurrentFactoryKey(ConnectionFactoryKey key);
}
