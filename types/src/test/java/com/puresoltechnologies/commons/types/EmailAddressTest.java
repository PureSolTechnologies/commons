package com.puresoltechnologies.commons.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class EmailAddressTest {

	@Test
	public void testValidDomainParts() {
		EmailAddress.validateDomainPart("example.com");
		EmailAddress.validateDomainPart("example.com.");
	}

	public void testIpAddressDomainPart() {
		EmailAddress.validateDomainPart("[1.1.1.1]");
		EmailAddress.validateDomainPart("[12.12.12.12]");
		EmailAddress.validateDomainPart("[123.123.123.123]");
	}

	public void testMinimalDomain() {
		EmailAddress.validateDomainPart("a.ab");
	}

	@Test
	public void testDomainPartMaximumLength() {
		EmailAddress.validateDomainPart(
				"abcdefg10.abcdefg20.abcdefg30.abcdefg40.abcdefg50.abcdefg60.abcdefg70.abcdefg80.abcdefg90.abcdef100.abcdef110.abcdef120.abcdef130.abcdef140.abcdef150.abcdef160.abcdef170.abcdef180.abcdef190.abcdef200.abcdef210.abcdef220.abcdef230.abcdef240.abcdef250.com");
	}

	@Test
	public void testDomainPartTooLong() {
		String domainPart = "abcdefg10.abcdefg20.abcdefg30.abcdefg40.abcdefg50.abcdefg60.abcdefg70.abcdefg80.abcdefg90.abcdef100.abcdef110.abcdef120.abcdef130.abcdef140.abcdef150.abcdef160.abcdef170.abcdef180.abcdef190.abcdef200.abcdef210.abcdef220.abcdef230.abcdef240.abcdef250.info";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validateDomainPart(domainPart);
		});
		assertEquals("Email address '?@" + domainPart + "' is invalid.\n"
				+ "Domain part is longer than 253 characters (254 characters).", exception.getMessage());
	}

	@Test
	public void testDomainPartLastDomainTooShort() {
		String domainPart = "a.a";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validateDomainPart(domainPart);
		});
		assertEquals("Email address '" + domainPart + "' is invalid.\nDomain part is invalid.", exception.getMessage());
	}

	@Test
	public void testValidateWithoutAt() {
		String address = "XXXXXXXX";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validate(address);
		});
		assertEquals("Email address '" + address + "' is invalid.\nNo @ character included.", exception.getMessage());
	}

	@Test
	public void testValidateMultipleAt() {
		String address = "XXX@XXX@xxx.de";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validate(address);
		});
		assertEquals("Email address '" + address + "' is invalid.\nMultiple @ characters included.",
				exception.getMessage());
	}

	@Test
	public void testValidateNoLocalPart() {
		String address = "@puresol-technologies.com";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validate(address);
		});
		assertEquals("Email address '" + address + "' is invalid.\n" + "Local part must no be empty.",
				exception.getMessage());
	}

	@Test
	public void testValidateNoDomainPart() {
		String address = "ludwig@";
		IllegalEmailAddressException exception = assertThrows(IllegalEmailAddressException.class, () -> {
			EmailAddress.validate(address);
		});
		assertEquals("Email address '" + address + "' is invalid.\n" + "Domain part must not be empty.",
				exception.getMessage());
	}

	@Test
	public void testJSONSerialization() throws JsonGenerationException, JsonMappingException, IOException {
		EmailAddress original = new EmailAddress("ludwig@puresol-technologies.com");
		String serialized = JSONSerializer.toJSONString(original);
		EmailAddress deserialized = JSONSerializer.fromJSONString(serialized, EmailAddress.class);
		assertEquals(original, deserialized);
	}

	@Test
	public void testJSONSerializationFromSimpleString()
			throws JsonGenerationException, JsonMappingException, IOException {
		String serialized = "{\"address\":\"a@a.de\"}";
		EmailAddress deserialized = JSONSerializer.fromJSONString(serialized, EmailAddress.class);
		assertEquals("a@a.de", deserialized.getAddress());
	}

}
