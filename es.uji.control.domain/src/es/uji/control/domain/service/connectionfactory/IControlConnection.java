package es.uji.control.domain.service.connectionfactory;

import es.uji.control.domain.subsystem.authorizations.IAuthorizationsService;
import es.uji.control.domain.subsystem.people.IPersonService;

public interface IControlConnection {
	abstract IPersonService getPersonService() throws ControlNotImplementedException;
	abstract IAuthorizationsService getAuthorizationsService() throws ControlNotImplementedException;
	abstract void close();
}
