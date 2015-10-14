package es.uji.control.domain.people;

import java.util.Set;

import es.uji.control.domain.ControlConnectionException;

public interface IPersonService {
	
	public IPersonStream getAllPersons() throws ControlConnectionException; 

	public IPersonStream getPerson(IPersonIdentifier personIdentifier) throws ControlConnectionException;

	public IPhotoStream getAllPhotos() throws ControlConnectionException;
	
	public Set<ILinkage> getLinkages() throws ControlConnectionException;
	
}
