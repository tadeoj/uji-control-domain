package es.uji.control.domain.people;

import java.util.List;

public interface IDomainPerson {
	
	public IPersonStream getAllPersons(); 

	public IPersonStream getPerson(IPersonIdentifier personIdentifier);

	public IPhotoStream getAllPhotos();
	
}
