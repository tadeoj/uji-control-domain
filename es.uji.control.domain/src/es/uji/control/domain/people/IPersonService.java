package es.uji.control.domain.people;

import java.util.Set;

import es.uji.control.domain.PeopleDomainConnectionException;

public interface IPersonService {
	
	public IPersonStream getAllPersons() throws PeopleDomainConnectionException; 

	public IPersonStream getPerson(IPersonIdentifier personIdentifier) throws PeopleDomainConnectionException;

	public IPhotoStream getAllPhotos() throws PeopleDomainConnectionException;
	
	public Set<ILinkage> getLinkages() throws PeopleDomainConnectionException;
	
}
