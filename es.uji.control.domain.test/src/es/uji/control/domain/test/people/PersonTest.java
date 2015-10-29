/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.test.people;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import es.uji.control.domain.subsystem.people.AccreditationBuilder;
import es.uji.control.domain.subsystem.people.AccreditationType;
import es.uji.control.domain.subsystem.people.IPerson;
import es.uji.control.domain.subsystem.people.LinkageBuilder;
import es.uji.control.domain.subsystem.people.PersonBuilder;
import es.uji.control.domain.subsystem.people.PersonIdentifierBuilder;
import es.uji.control.domain.subsystem.people.PersonIdentifierType;

@RunWith(JUnit4.class)
public class PersonTest {

	private Date emisionDate;
	private Date cancelationDate;
	
	@Test
	public void testCreation() {
		
		IPerson person = creation();
		
		// Person
		assertEquals("Name", "Tadeo", person.getName());
		assertEquals("First LastName", "Julián", person.getFirstLastName());
		assertEquals("Second LastName", "Segarra", person.getSecondLastName());
		assertEquals("Identification", "11111111L", person.getIdentification());
		
		// PersonIdentifier
		assertEquals("PersonIdType", PersonIdentifierType.GENERAL_LONG_ID, person.getId().getType());
		assertEquals("PersonIdRaw", "12345678", person.getId().getRaw());
		
		// Linkage
		assertEquals("Linkage", "Alumno", person.getLinkages().get(0).getName());
		
		// Accreditation
		assertEquals("Accreditation Type", AccreditationType.MIFARE_SERIAL_NUMBER, person.getAccreditations().get(0).getType());
		assertEquals("Accreditation Raw", "20202020", person.getAccreditations().get(0).getRaw());
		assertEquals("Extra type", null, person.getAccreditations().get(0).getExtraType());
		assertEquals("Emision Date", emisionDate, person.getAccreditations().get(0).getEmisionDate());
		assertEquals("Cancelation Date", cancelationDate, person.getAccreditations().get(0).getCancelationDate());

	}
	
	public IPerson creation() {
		
		emisionDate = new Date();
		cancelationDate = new Date();
		
		PersonIdentifierBuilder idBuilder = new PersonIdentifierBuilder();
		idBuilder.setType(PersonIdentifierType.GENERAL_LONG_ID);
		idBuilder.setRaw("12345678");
		
		AccreditationBuilder accreditationBuilder = new AccreditationBuilder();
		accreditationBuilder.setType(AccreditationType.MIFARE_SERIAL_NUMBER);
		accreditationBuilder.setRaw("20202020");
		accreditationBuilder.setEmisionDate(emisionDate);
		accreditationBuilder.setCancelationDate(cancelationDate);
		
		LinkageBuilder linkageBuilder = new LinkageBuilder();
		linkageBuilder.setName("Alumno");
		
		PersonBuilder builder = new PersonBuilder();
		builder.setId(idBuilder.build());
		builder.setName("Tadeo");
		builder.setFirstLastName("Julián");
		builder.setSecondLastName("Segarra");
		builder.setIdentification("11111111L");
		builder.addAccreditation(accreditationBuilder.build());
		builder.addLinkage(linkageBuilder.build());
		return builder.build();
	}

}
