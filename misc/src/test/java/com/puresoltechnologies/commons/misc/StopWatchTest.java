package com.puresoltechnologies.commons.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StopWatchTest {

	@Test
	public void testInitialValues() {
		StopWatch stopWatch = new StopWatch();
		assertNull(stopWatch.getStartTime());
		assertNull(stopWatch.getStopTime());
		assertEquals(0, stopWatch.getMillis());
		assertEquals(0.0, stopWatch.getSeconds(), 1e-10);
	}

	@Test
	public void testMeasurement() throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Thread.sleep(100);
		stopWatch.stop();
		assertNotNull(stopWatch.getStartTime());
		assertNotNull(stopWatch.getStopTime());
		long milliseconds = stopWatch.getMillis();
		assertTrue(milliseconds >= 90);
		assertEquals(stopWatch.getSeconds(), milliseconds / 1000.0, 0.01);
	}

}
