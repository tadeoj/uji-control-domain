/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.people;

import java.util.Arrays;

public class AccreditationBuilder {

	private AccreditationType type;
	private byte[] raw;
	private long id;
	
	public AccreditationBuilder setType(AccreditationType type) {
		this.type = type;
		return this;
	}
	
	public AccreditationBuilder setRaw(byte[] raw) {
		this.raw = raw;;
		return this;
	}
	
	public AccreditationBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public Accreditation build() {
		if (type == null) throw new IllegalStateException("type isn't defined");
		if (raw == null) throw new IllegalStateException("raw isn't defined");
		
		return new Accreditation(type, raw, id);
	}
	
	static private class Accreditation implements IAccreditation {

		private static final long serialVersionUID = 1657010167191488308L;

		private final AccreditationType type;
		private final byte[] raw;
		private final long id;
		
		private Accreditation(AccreditationType type, byte[] raw, long id) {
			super();
			this.type = type;
			this.raw = raw;
			this.id = id;
		}
		
		@Override
		public AccreditationType getType() {
			return type;
		}

		@Override
		public byte[] getRaw() {
			return raw;
		}
		
		@Override
		public long getId() {
			return id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + Arrays.hashCode(raw);
			result = prime * result + ((type == null) ? 0 : type.hashCode());
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
			Accreditation other = (Accreditation) obj;
			if (id != other.id)
				return false;
			if (!Arrays.equals(raw, other.raw))
				return false;
			if (type != other.type)
				return false;
			return true;
		}

	}
	
}
