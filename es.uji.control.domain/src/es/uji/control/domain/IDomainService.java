package es.uji.control.domain;

import java.util.List;

import es.uji.control.domain.accreditations.Accreditation;
import es.uji.control.domain.people.Person;
import es.uji.control.domain.people.Photo;

public interface IDomainService {

	public Person getPerson(long id);

	public List<Person> getPeople();

	public Photo getPhoto(long perId);
	
	public List<Accreditation> getAccreditationsByPerson(long personId);

}
