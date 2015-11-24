/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.test.people;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import es.uji.control.domain.people.AccreditationBuilder;
import es.uji.control.domain.people.AccreditationInfoBuilder;
import es.uji.control.domain.people.AccreditationType;
import es.uji.control.domain.people.IAccreditationInfo;
import es.uji.control.domain.people.ILinkage;
import es.uji.control.domain.people.IPerson;
import es.uji.control.domain.people.LinkageBuilder;
import es.uji.control.domain.people.PersonBuilder;
import es.uji.control.domain.people.PersonIdentifierBuilder;
import es.uji.control.domain.people.PersonIdentifierType;

@RunWith(JUnit4.class)
public class PersonTest {

	private LocalDateTime emisionDate;
	private LocalDateTime cancelationDate;
	
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
		assertEquals("Accreditation Type", AccreditationType.MIFARE_SERIAL_NUMBER, person.getAccreditationsInfo().get(0).getAccreditation().getType());
		byte[] raw = ByteBuffer.allocate(4).putInt(20202020).array();
		assertEquals("Accreditation Raw", raw, person.getAccreditationsInfo().get(0).getAccreditation().getRaw());

	}
	
	public IPerson creation() {
		
		emisionDate = LocalDateTime.now();
		cancelationDate = LocalDateTime.now();
		
		List<IAccreditationInfo> accreditationsInfo = new ArrayList<>();
		List<ILinkage> linkagesInfo = new ArrayList<>();
		
		PersonIdentifierBuilder idBuilder = new PersonIdentifierBuilder();
		idBuilder.setType(PersonIdentifierType.GENERAL_LONG_ID);
		byte[] rawId = ByteBuffer.allocate(4).putInt(12345678).array();
		idBuilder.setRaw(rawId);
		
		AccreditationBuilder accreditationBuilder = new AccreditationBuilder();
		accreditationBuilder.setType(AccreditationType.MIFARE_SERIAL_NUMBER);
		byte[] rawAccreditation = ByteBuffer.allocate(4).putInt(20202020).array();
		accreditationBuilder.setRaw(rawAccreditation);
		
		AccreditationInfoBuilder accreditationInfoBuilder = new AccreditationInfoBuilder();
		accreditationInfoBuilder.setAccreditation(accreditationBuilder.build());		
		accreditationInfoBuilder.setEmisionDate(emisionDate);
		accreditationInfoBuilder.setCancelationDate(cancelationDate);
		accreditationInfoBuilder.setAccreditation(accreditationBuilder.build());
		
		LinkageBuilder linkageBuilder = new LinkageBuilder();
		linkageBuilder.setName("Alumno");
		
		PersonBuilder builder = new PersonBuilder();
		builder.setId(idBuilder.build());
		builder.setName("Tadeo");
		builder.setFirstLastName("Julián");
		builder.setSecondLastName("Segarra");
		builder.setIdentification("11111111L");
		accreditationsInfo.add(accreditationInfoBuilder.build());
		builder.setAccreditationsInfo(accreditationsInfo);
		linkagesInfo.add(linkageBuilder.build());
		builder.setLinkages(linkagesInfo);
		return builder.build();
	}

}
