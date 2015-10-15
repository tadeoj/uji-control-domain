package es.uji.control.domain.service.factoryselector;

final public class ConnectionFactoryKey {
	
	private String connectionFactoryKey;
	
	public ConnectionFactoryKey(String connectionFactoryKeyKey) {
		// Normalicemos
		this.connectionFactoryKey = connectionFactoryKeyKey.toUpperCase().trim();
	}
	
	@Override
	public String toString() {
		return this.connectionFactoryKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionFactoryKey == null) ? 0 : connectionFactoryKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionFactoryKey other = (ConnectionFactoryKey) obj;
		if (connectionFactoryKey == null) {
			if (other.connectionFactoryKey != null)
				return false;
		} else if (!connectionFactoryKey.equals(other.connectionFactoryKey))
			return false;
		return true;
	}

}
