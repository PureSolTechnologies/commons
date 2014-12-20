package com.puresoltechnologies.commons.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class PasswordTest {

    @Test
    public void testToStringDoesNotContainPassword() {
	Password pw = new Password(":-)");
	assertFalse(pw.toString().contains(":-)"));
	assertEquals("XXXXXXXX (password)", pw.toString());
    }

    @Test
    public void testJSONSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	Password original = new Password(
		"This is a super secret passwords with number 123 and special characters #'!§$ AND UPPER CASE! :-D");
	String serialized = JSONSerializer.toJSONString(original);
	Password deserialized = JSONSerializer.fromJSONString(serialized,
		Password.class);
	assertEquals(original, deserialized);
    }
}
