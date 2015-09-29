package es.uji.control.domain.people;

import java.util.List;

public class PersonBuilder {

	private IPersonIdentifier id;
	private String name;
	private String firstLastName;
	private String secondLastName;
	private String identification;
	private List<ILinkage> linkages;
	private List<IAccreditation> accreditation;

	public void setId(IPersonIdentifier id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public void addLinkages(List<ILinkage> linkages) {
		this.linkages = linkages;
	}

	public void addAccreditation(List<IAccreditation> accreditation) {
		this.accreditation = accreditation;
	}

	public Person build() {
		return new Person(id, name, firstLastName, secondLastName, identification, linkages, accreditation);
	}

	private class Person implements IPerson {

		private final IPersonIdentifier id;
		private final String name;
		private final String firstLastName;
		private final String secondLastName;
		private final String identification;
		private final List<ILinkage> linkages;
		private final List<IAccreditation> accreditation;

		private Person(IPersonIdentifier id, String name, String firstLastName,
				String secondLastName, String identification,
				List<ILinkage> linkages, List<IAccreditation> accreditation) {
			super();
			this.id = id;
			this.name = name;
			this.firstLastName = firstLastName;
			this.secondLastName = secondLastName;
			this.identification = identification;
			this.linkages = linkages;
			this.accreditation = accreditation;
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
		public List<IAccreditation> getAccreditations() {
			return accreditation;
		}
		
		

	}

}
