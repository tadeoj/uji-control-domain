/*******************************************************************************
 * Copyright © Universitat Jaume I de Castelló 2015.
 * Aquest programari es distribueix sota les condicions de llicència EUPL 
 * o de qualsevol altra que la substituisca en el futur.
 * La llicència completa es pot descarregar de 
 * https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11
 *******************************************************************************/
package es.uji.control.domain.people;

import java.nio.ByteBuffer;

public enum AccreditationType {
	
	MIFARE_SERIAL_NUMBER;
	
	public static byte[] generalLongIdToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(0, x);
		return buffer.array();
	}

	public static long bytesToGeneralLongId(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();
		return buffer.getLong();
	}
	
}
