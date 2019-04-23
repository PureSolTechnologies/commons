package com.puresoltechnologies.commons.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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

	@Test
	public void testZeroFraction() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Money("EUR", 0, 1234);
		});
	}

	@Test
	public void testNegativeFraction() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Money("EUR", -10, 1234);
		});
	}

	@Test
	public void testInvalidFraction() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Money("EUR", 15, 1234);
		});
	}

	@Test
	public void testToString() {
		Money money = new Money("EUR", 100, 1234567);
		assertEquals("12345.67EUR", money.toString());
	}

	@Test
	public void testToIntValue() {
		Money money = new Money("EUR", 100, 12345);
		assertEquals(12345, money.intValue());
		money = new Money("EUR", 100, 0);
		assertEquals(0, money.intValue());
		money = new Money("EUR", 100, -12345);
		assertEquals(-12345, money.intValue());
	}

	@Test
	public void testToLongValue() {
		Money money = new Money("EUR", 100, 12345678901l);
		assertEquals(12345678901l, money.longValue());
		money = new Money("EUR", 100, 0);
		assertEquals(0, money.longValue());
		money = new Money("EUR", 100, -12345678901l);
		assertEquals(-12345678901l, money.longValue());
	}

	@Test
	public void testToFloatValue() {
		Money money = new Money("EUR", 100, 12345678901l);
		assertEquals(123456789.01f, money.floatValue(), 1e-12);
	}

	@Test
	public void testToDoubleValue() {
		Money money = new Money("EUR", 100, 12345678901l);
		assertEquals(123456789.01, money.doubleValue(), 1e-12);
	}

	@Test
	public void testToIntValueWithTooLargeAmount() {
		assertThrows(IllegalStateException.class, () -> {
			Money money = new Money("EUR", 100, 12345678901l);
			money.intValue();
		});
	}
}
