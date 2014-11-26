package com.puresoltechnologies.commons.money;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoneyTest {

    @Test
    public void testAmounts() {
	new Money("EUR", 1, -1234);
	new Money("EUR", 1, 0);
	new Money("EUR", 1, 1234);
    }

    @Test
    public void testValidFractions() {
	new Money("EUR", 1, 1234);
	new Money("EUR", 10, 1234);
	new Money("EUR", 100, 1234);
	new Money("EUR", 1000, 1234);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroFraction() {
	new Money("EUR", 0, 1234);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeFraction() {
	new Money("EUR", -10, 1234);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFraction() {
	new Money("EUR", 15, 1234);
    }

    @Test
    public void testToString() {
	Money money = new Money("EUR", 100, 1234567);
	assertEquals("12345.67EUR", money.toString());
    }

}