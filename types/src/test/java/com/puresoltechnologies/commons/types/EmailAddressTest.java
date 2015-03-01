package com.puresoltechnologies.commons.types;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class EmailAddressTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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
	EmailAddress
		.validateDomainPart("abcdefg10.abcdefg20.abcdefg30.abcdefg40.abcdefg50.abcdefg60.abcdefg70.abcdefg80.abcdefg90.abcdef100.abcdef110.abcdef120.abcdef130.abcdef140.abcdef150.abcdef160.abcdef170.abcdef180.abcdef190.abcdef200.abcdef210.abcdef220.abcdef230.abcdef240.abcdef250.com");
    }

    @Test
    public void testDomainPartTooLong() {
	String domainPart = "abcdefg10.abcdefg20.abcdefg30.abcdefg40.abcdefg50.abcdefg60.abcdefg70.abcdefg80.abcdefg90.abcdef100.abcdef110.abcdef120.abcdef130.abcdef140.abcdef150.abcdef160.abcdef170.abcdef180.abcdef190.abcdef200.abcdef210.abcdef220.abcdef230.abcdef240.abcdef250.info";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException
		.expectMessage("Email address '?@"
			+ domainPart
			+ "' is invalid.\n"
			+ "Domain part is longer than 253 characters (254 characters).");
	EmailAddress.validateDomainPart(domainPart);
    }

    @Test
    public void testDomainPartLastDomainTooShort() {
	String domainPart = "a.a";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException.expectMessage("Email address '" + domainPart
		+ "' is invalid.\nDomain part is invalid.");
	EmailAddress.validateDomainPart(domainPart);
    }

    @Test
    public void testValidateWithoutAt() {
	String address = "XXXXXXXX";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException.expectMessage("Email address '" + address
		+ "' is invalid.\nNo @ character included.");
	EmailAddress.validate(address);
    }

    @Test
    public void testValidateMultipleAt() {
	String address = "XXX@XXX@xxx.de";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException.expectMessage("Email address '" + address
		+ "' is invalid.\nMultiple @ characters included.");
	EmailAddress.validate(address);
    }

    @Test
    public void testValidateNoLocalPart() {
	String address = "@puresol-technologies.com";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException.expectMessage("Email address '" + address
		+ "' is invalid.\n" + "Local part must no be empty.");
	EmailAddress.validate(address);
    }

    @Test
    public void testValidateNoDomainPart() {
	String address = "ludwig@";
	expectedException.expect(IllegalEmailAddressException.class);
	expectedException.expectMessage("Email address '" + address
		+ "' is invalid.\n" + "Domain part must not be empty.");
	EmailAddress.validate(address);
    }

    @Test
    public void testJSONSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	EmailAddress original = new EmailAddress(
		"ludwig@puresol-technologies.com");
	String serialized = JSONSerializer.toJSONString(original);
	EmailAddress deserialized = JSONSerializer.fromJSONString(serialized,
		EmailAddress.class);
	assertEquals(original, deserialized);
    }

}
