package com.puresoltechnologies.commons.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MoneyMathTest {

	@Test
	public void testAllocate() {
		long[] values = MoneyMath.allocate(100, 1.0, 2.0, 3.0);
		long sum = 0;
		for (long value : values) {
			System.out.println(value);
			sum += value;
		}
		assertEquals(100, sum);
		assertTrue(((values[0] * 2) - values[1]) <= 1);
		assertTrue(((values[0] * 3) - values[2]) <= 1);
	}

}
