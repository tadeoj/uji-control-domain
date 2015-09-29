package es.uji.control.domain.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonBuilder {

	private IPersonIdentifier id;
	private String name;
	private String firstLastName;
	private String secondLastName;
	private String identification;
	private ArrayList<ILinkage> linkages = new ArrayList<>();
	private ArrayList<IAccreditation> accreditations = new ArrayList<>();

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

	public PersonBuilder addLinkage(ILinkage linkage) {
		linkages.add(linkage);
		return this;
	}

	public PersonBuilder addAccreditation(IAccreditation accreditation) {
		accreditations.add(accreditation);
		return this;
	}

	public Person build() {
		if (id == null) throw new IllegalStateException("id isn't defined");
		if (name == null) throw new IllegalStateException("name isn't defined");
		if (firstLastName == null) throw new IllegalStateException("firstLastName isn't defined");
		if (secondLastName == null) throw new IllegalStateException("secondLastName isn't defined");
		if (identification == null) throw new IllegalStateException("identification isn't defined");
		return new Person(
			id, 
			name, 
			firstLastName, 
			secondLastName, 
			identification, 
			Collections.unmodifiableList(linkages), 
			Collections.unmodifiableList(accreditations) 
		);
	}

	static private class Person implements IPerson {

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
