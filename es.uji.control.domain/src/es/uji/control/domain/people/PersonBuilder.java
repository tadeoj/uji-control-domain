/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.people;

import java.util.Collections;
import java.util.List;

public class PersonBuilder {

	private IPersonIdentifier id;
	private String name;
	private String firstLastName;
	private String secondLastName;
	private String identification;
	private List<ILinkage> linkages;
	private List<IAccreditationInfo> accreditationsInfo;

	public PersonBuilder setId(IPersonIdentifier id) {
		this.id = id;
		return this;
	}

	public PersonBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public PersonBuilder setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
		return this;
	}

	public PersonBuilder setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
		return this;
	}

	public PersonBuilder setIdentification(String identification) {
		this.identification = identification;
		return this;
	}

	public PersonBuilder setLinkages(List<ILinkage> linkages) {
		this.linkages = linkages;
		return this;
	}

	public PersonBuilder setAccreditationsInfo(List<IAccreditationInfo> accreditationsInfo) {
		this.accreditationsInfo = accreditationsInfo;
		return this;
	}

	public Person build() {
		if (id == null) throw new IllegalStateException("id isn't defined");
		if (name == null) throw new IllegalStateException("name isn't defined");
		if (firstLastName == null) throw new IllegalStateException("firstLastName isn't defined");
		if (secondLastName == null) throw new IllegalStateException("secondLastName isn't defined");
		if (identification == null) throw new IllegalStateException("identification isn't defined");
		if (linkages == null) throw new IllegalStateException("linkages isn't defined");
		if (accreditationsInfo == null) throw new IllegalStateException("accreditationsInfo isn't defined");
		return new Person(
			id, 
			name, 
			firstLastName, 
			secondLastName, 
			identification, 
			Collections.unmodifiableList(linkages), 
			Collections.unmodifiableList(accreditationsInfo) 
		);
	}

	static private class Person implements IPerson {

		private static final long serialVersionUID = 3165046532886676795L;

		private final IPersonIdentifier id;
		private final String name;
		private final String firstLastName;
		private final String secondLastName;
		private final String identification;
		private final List<ILinkage> linkages;
		private final List<IAccreditationInfo> accreditationsInfo;

		private Person(IPersonIdentifier id, String name, String firstLastName,
				String secondLastName, String identification,
				List<ILinkage> linkages, List<IAccreditationInfo> accreditationsInfo) {
			super();
			this.id = id;
			this.name = name;
			this.firstLastName = firstLastName;
			this.secondLastName = secondLastName;
			this.identification = identification;
			this.linkages = linkages;
			this.accreditationsInfo = accreditationsInfo;
		}

		@Override
		public IPersonIdentifier getId() {
			return id;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getFirstLastName() {
			return firstLastName;
		}

		@Override
		public String getSecondLastName() {
			return secondLastName;
		}

		@Override
		public String getIdentification() {
			return identification;
		}

		@Override
		public List<ILinkage> getLinkages() {
			return linkages;
		}

		@Override
		public List<IAccreditationInfo> getAccreditationsInfo() {
			return accreditationsInfo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accreditationsInfo == null) ? 0 : accreditationsInfo.hashCode());
			result = prime * result + ((firstLastName == null) ? 0 : firstLastName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((identification == null) ? 0 : identification.hashCode());
			result = prime * result + ((linkages == null) ? 0 : linkages.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((secondLastName == null) ? 0 : secondLastName.hashCode());
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
			Person other = (Person) obj;
			if (accreditationsInfo == null) {
				if (other.accreditationsInfo != null)
					return false;
			} else if (!accreditationsInfo.equals(other.accreditationsInfo))
				return false;
			if (firstLastName == null) {
				if (other.firstLastName != null)
					return false;
			} else if (!firstLastName.equals(other.firstLastName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (identification == null) {
				if (other.identification != null)
					return false;
			} else if (!identification.equals(other.identification))
				return false;
			if (linkages == null) {
				if (other.linkages != null)
					return false;
			} else if (!linkages.equals(other.linkages))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (secondLastName == null) {
				if (other.secondLastName != null)
					return false;
			} else if (!secondLastName.equals(other.secondLastName))
				return false;
			return true;
		}

	}

}
