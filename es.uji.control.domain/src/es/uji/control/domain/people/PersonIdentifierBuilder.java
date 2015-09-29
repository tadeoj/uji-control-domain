package es.uji.control.domain.people;


public class PersonIdentifierBuilder {

	private PersonIdentifierType type;
	private String raw;

	public PersonIdentifierBuilder setType(PersonIdentifierType type) {
		this.type = type;
		return this;
	}

	public PersonIdentifierBuilder setRaw(String raw) {
		this.raw = raw;
		return this;
	}
	
	public PersonIdentifier build() {
		if (type == null) throw new IllegalStateException("personIdentifierType isn´t defined");
		if (raw == null) throw new IllegalStateException("raw isn´t defined");
		return new PersonIdentifier(type, raw);
	}

	static private class PersonIdentifier implements IPersonIdentifier {

		private static final long serialVersionUID = 4211652569433895239L;

		private final PersonIdentifierType type;
		private final String raw;

		private PersonIdentifier(PersonIdentifierType type, String raw) {
			super();
			this.type = type;
			this.raw = raw;
		}

		@Override
		public PersonIdentifierType getType() {
			return type;
		}

		@Override
		public String getRaw() {
			return raw;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
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
			PersonIdentifier other = (PersonIdentifier) obj;
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