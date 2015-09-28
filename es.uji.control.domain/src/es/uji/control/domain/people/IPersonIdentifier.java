package es.uji.control.domain.people;

public interface IPersonIdentifier {
	public PersonIdentifierType getType();
	public String getRawId();
}
