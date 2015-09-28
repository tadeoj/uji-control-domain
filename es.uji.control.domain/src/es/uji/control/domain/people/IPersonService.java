package es.uji.control.domain.people;

import java.util.Set;

public interface IPersonService {
	
	public IPersonStream getAllPersons(); 

	public IPersonStream getPerson(IPersonIdentifier personIdentifier);

	public IPhotoStream getAllPhotos();
	
	public Set<ILinkage> getLinkages();
	
}
