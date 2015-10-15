package es.uji.control.domain.subsystem.people;

import java.util.List;

public interface IPersonStream {
	 void onNext(List<IPerson> l);
	 void onError(Throwable t);
	 void onCompleted();
}
