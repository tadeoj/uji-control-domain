package es.uji.control.domain.service.factoryselector;

import java.util.Set;

public interface IControlConnectionFactorySelector {
	public abstract Set<ConnectionFactoryKey> getFactoryKeys();
	public abstract String getFactoryDescription(ConnectionFactoryKey key);
	public abstract ConnectionFactoryKey getCurrentFactoryKey();
	public abstract void setCurrentFactoryKey(ConnectionFactoryKey key);
}
