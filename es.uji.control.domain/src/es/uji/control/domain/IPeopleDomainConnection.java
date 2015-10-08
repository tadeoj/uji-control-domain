package es.uji.control.domain;

import es.uji.control.domain.authorizations.IAuthorizationsService;
import es.uji.control.domain.people.IPersonService;

public interface IPeopleDomainConnection {
	abstract IPersonService getPersonService() throws PeopleNotImplementedException;
	abstract IAuthorizationsService getAuthorizationsService() throws PeopleNotImplementedException;
	abstract void reset();
}
