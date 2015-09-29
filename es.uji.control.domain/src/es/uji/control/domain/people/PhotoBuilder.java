package es.uji.control.domain.people;

import java.sql.Date;
import java.util.Arrays;

public class PhotoBuilder {

	private IPersonIdentifier personId;
	private Date date;
	private byte[] image;
	
	public PhotoBuilder setPersonId(IPersonIdentifier personId) {
		this.personId = personId;
		return this;
	}
	
	public PhotoBuilder setDate(Date date) {
		this.date = date;
		return this;
	}
	
	public PhotoBuilder setImage(byte[] image) {
		this.image = image;
		return this;
	}
	
	public Photo build() {
		if (personId == null) throw new IllegalStateException("personId isn´t defined");
		if (date == null) throw new IllegalStateException("date isn´t defined");
		if (image == null) throw new IllegalStateException("image isn´t defined");
		return new Photo(personId, date, image);
	}
	
	static private class Photo implements IPhoto {

		private static final long serialVersionUID = 1520108919911121865L;

		private final IPersonIdentifier personId;
		private final Date date;
		private final byte[] image;
		
		private Photo(IPersonIdentifier personId, Date date, byte[] image) {
			super();
			this.personId = personId;
			this.date = date;
			this.image = image;
		}

		@Override
		public IPersonIdentifier getPersonIdentifier() {
			return personId;
		}

		@Override
		public java.util.Date getDate() {
			return date;
		}

		@Override
		public byte[] getImage() {
			return image;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + Arrays.hashCode(image);
			result = prime * result
					+ ((personId == null) ? 0 : personId.hashCode());
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
			Photo other = (Photo) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (!Arrays.equals(image, other.image))
				return false;
			if (personId == null) {
				if (other.personId != null)
					return false;
			} else if (!personId.equals(other.personId))
				return false;
			return true;
		}
		
	}
	
}
