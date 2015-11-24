/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.people;

import java.time.LocalDateTime;

public class AccreditationInfoBuilder {

	private IAccreditation accreditation;
	private LocalDateTime  emisionDate;
	private LocalDateTime cancelationDate;
	
	public AccreditationInfoBuilder setAccreditation (IAccreditation accreditation) {
		this.accreditation = accreditation;
		return this;
	}
	
	public AccreditationInfoBuilder setEmisionDate (LocalDateTime emisionDate) {
		this.emisionDate = emisionDate;
		return this;
	}
	
	public AccreditationInfoBuilder setCancelationDate(LocalDateTime cancelationDate) {
		this.cancelationDate = cancelationDate;
		return this;
	}
	
	public AccreditationInfo build() {
		if (accreditation == null) throw new IllegalStateException("accreditation isn't defined");
		if (emisionDate == null) throw new IllegalStateException("emisionDate isn't defined");
		if (cancelationDate == null) throw new IllegalStateException("cancelationDate isn't defined");
		
		return new AccreditationInfo(accreditation, emisionDate, cancelationDate);
	}
	
	static private class AccreditationInfo implements IAccreditationInfo {

		private final IAccreditation accreditation;
		private final LocalDateTime emisionDate;
		private final LocalDateTime cancelationDate;
		
		private AccreditationInfo(IAccreditation accreditation, LocalDateTime emisionDate, LocalDateTime cancelationDate) {
			super();
			this.accreditation = accreditation;
			this.emisionDate = emisionDate;
			this.cancelationDate = cancelationDate;
		}
		
		@Override
		public LocalDateTime getEmisionDate() {
			return emisionDate;
		}

		@Override
		public LocalDateTime getCancelationDate() {
			return cancelationDate;
		}

		@Override
		public IAccreditation getAccreditation() {
			return accreditation;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accreditation == null) ? 0 : accreditation.hashCode());
			result = prime * result + ((cancelationDate == null) ? 0 : cancelationDate.hashCode());
			result = prime * result + ((emisionDate == null) ? 0 : emisionDate.hashCode());
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
			AccreditationInfo other = (AccreditationInfo) obj;
			if (accreditation == null) {
				if (other.accreditation != null)
					return false;
			} else if (!accreditation.equals(other.accreditation))
				return false;
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
			return true;
		}
		
	}
	
}