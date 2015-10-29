/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.subsystem.people;


public class LinkageBuilder {
	
	private String name;
	
	public LinkageBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public Linkage build() {
		if (name == null) throw new IllegalStateException("name isn't defined");
		return new Linkage(name);
	}
	
	static private class Linkage implements ILinkage {

		private static final long serialVersionUID = -2534486657172874301L;

		private final String name;
		
		private Linkage(String name) {
			super();
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Linkage other = (Linkage) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}

}
