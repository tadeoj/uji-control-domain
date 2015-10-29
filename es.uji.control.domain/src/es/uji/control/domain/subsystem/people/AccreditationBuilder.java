/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.subsystem.people;

import java.util.Date;

public class AccreditationBuilder {

	private AccreditationType type;
	private String extraType;
	private String raw;
	private Date emisionDate;
	private Date cancelationDate;
	
	public AccreditationBuilder setType(AccreditationType type) {
		this.type = type;
		return this;
	}
	
	public AccreditationBuilder setExtraType (String extraType) {
		this.extraType = extraType;
		return this;
	}
	
	public AccreditationBuilder setRaw(String raw) {
		this.raw = raw;;
		return this;
	}
	
	public AccreditationBuilder setEmisionDate (Date emisionDate) {
		this.emisionDate = emisionDate;
		return this;
	}
	
	public AccreditationBuilder setCancelationDate(Date cancelationDate) {
		this.cancelationDate = cancelationDate;
		return this;
	}
	
	public Accreditation build() {
		if (type == null) throw new IllegalStateException("type isn't defined");
		if (raw == null) throw new IllegalStateException("raw isn't defined");
		if (emisionDate == null) throw new IllegalStateException("emisionDate isn't defined");
		if (cancelationDate == null) throw new IllegalStateException("cancelationDate isn't defined");
		
		return new Accreditation(type, extraType, raw, emisionDate, cancelationDate);
	}
	
	static private class Accreditation implements IAccreditation {

		private static final long serialVersionUID = 1657010167191488308L;

		private final AccreditationType type;
		private final String extraType;
		private final String raw;
		private final Date emisionDate;
		private final Date cancelationDate;
		
		private Accreditation(AccreditationType type, String extraType, String raw, Date emisionDate, Date cancelationDate) {
			super();
			this.type = type;
			this.extraType = extraType;
			this.raw = raw;
			this.emisionDate = emisionDate;
			this.cancelationDate = cancelationDate;
		}
		
		@Override
		public AccreditationType getType() {
			return type;
		}

		@Override
		public String getExtraType() {
			return extraType;
		}

		@Override
		public String getRaw() {
			return raw;
		}

		@Override
		public Date getEmisionDate() {
			return emisionDate;
		}

		@Override
		public Date getCancelationDate() {
			return cancelationDate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((cancelationDate == null) ? 0 : cancelationDate
							.hashCode());
			result = prime * result
					+ ((emisionDate == null) ? 0 : emisionDate.hashCode());
			result = prime * result
					+ ((extraType == null) ? 0 : extraType.hashCode());
			result = prime * result + ((raw == null) ? 0 : raw.hashCode());
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
			if (cancelationDate == null) {
				if (other.cancelationDate != null)
					return false;
			} else if (!cancelationDate.equals(other.cancelationDate))
				return false;
			if (emisionDate == null) {
				if (other.emisionDate != null)
					return false;
			} else if (!emisionDate.equals(other.emisionDate))
				return false;
			if (extraType == null) {
				if (other.extraType != null)
					return false;
			} else if (!extraType.equals(other.extraType))
				return false;
			if (raw == null) {
				if (other.raw != null)
					return false;
			} else if (!raw.equals(other.raw))
				return false;
			if (type != other.type)
				return false;
			return true;
		}
		
	}
	
}
