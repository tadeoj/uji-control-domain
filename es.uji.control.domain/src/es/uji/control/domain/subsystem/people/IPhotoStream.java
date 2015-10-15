package es.uji.control.domain.subsystem.people;

import java.util.List;

public interface IPhotoStream {
	 void onNext(List<IPhoto> l);
	 void onError(Throwable t);
	 void onCompleted();
}
