package com.puresoltechnologies.commons.misc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;

public class HashAlgorithmsTest {

	@Test
	public void testForAvailability() throws NoSuchAlgorithmException {
		HashAlgorithm[] algorithms = HashAlgorithm.class.getEnumConstants();
		for (HashAlgorithm algorithm : algorithms) {
			assertNotNull(MessageDigest.getInstance(algorithm.getAlgorithmName()));
		}
	}

	@Test
	public void testFormName() throws NoSuchAlgorithmException {
		for (HashAlgorithm algorithm : HashAlgorithm.values()) {
			assertNotNull(HashAlgorithm.fromAlgorithmName(algorithm.getAlgorithmName()));
		}
	}

	@Test
	public void testIllegalFormName() throws NoSuchAlgorithmException {
		assertThrows(IllegalArgumentException.class, () -> {
			HashAlgorithm.fromAlgorithmName("UnkownAlgorithm");
		});
	}

}
