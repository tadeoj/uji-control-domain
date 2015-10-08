package es.uji.control.domain;

final public class PeopleDomainFactoryKey {
	
	private String innerKey;
	
	public PeopleDomainFactoryKey(String innerKey) {
		// Normalicemos
		this.innerKey = innerKey.toUpperCase().trim();
	}
	
	@Override
	public String toString() {
		return this.innerKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((innerKey == null) ? 0 : innerKey.hashCode());
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
		PeopleDomainFactoryKey other = (PeopleDomainFactoryKey) obj;
		if (innerKey == null) {
			if (other.innerKey != null)
				return false;
		} else if (!innerKey.equals(other.innerKey))
			return false;
		return true;
	}

}
