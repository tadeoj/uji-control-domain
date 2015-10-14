package es.uji.control.domain;

import es.uji.control.domain.authorizations.IAuthorizationsService;
import es.uji.control.domain.people.IPersonService;

public interface IControlConnection {
	abstract IPersonService getPersonService() throws ControlNotImplementedException;
	abstract IAuthorizationsService getAuthorizationsService() throws ControlNotImplementedException;
	abstract void close();
}
