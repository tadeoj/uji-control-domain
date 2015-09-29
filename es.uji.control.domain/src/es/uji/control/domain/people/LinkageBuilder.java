package es.uji.control.domain.people;


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
