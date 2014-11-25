package com.puresoltechnologies.commons.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class PasswordTest {

    @Test
    public void testToStringDoesNotContainPassword() {
	Password pw = new Password(":-)");
	assertFalse(pw.toString().contains(":-)"));
	assertEquals("XXXXXXXX (password)", pw.toString());
    }
}
