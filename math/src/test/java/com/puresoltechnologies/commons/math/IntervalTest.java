package com.puresoltechnologies.commons.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IntervalTest {

    @Test
    public void testInvalidRangeLeftUnboundButIncluded() {
	IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
		() -> new Interval<>(null, true, 1.0, true));
	assertEquals("An unbound lower boundary can not be defined as closed.", thrown.getMessage());
    }

    @Test
    public void testInvalidRangeRightUnboundButIncluded() {
	IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
		() -> new Interval<>(1.0, true, null, true));
	assertEquals("An unbound upper boundary can not be defined as closed.", thrown.getMessage());
    }

    @Test
    public void testIncludesClosed() {
	Interval<Double> range = new Interval<>(1.0, true, 2.0, true);
	assertFalse(range.includes(0.9999));
	assertTrue(range.includes(1.0));
	assertTrue(range.includes(1.5));
	assertTrue(range.includes(2.0));
	assertFalse(range.includes(2.01));
    }

    @Test
    public void testIncludesLeftClosedRightOpen() {
	Interval<Double> range = new Interval<>(1.0, true, 2.0, false);
	assertFalse(range.includes(0.9999));
	assertTrue(range.includes(1.0));
	assertTrue(range.includes(1.5));
	assertFalse(range.includes(2.0));
	assertFalse(range.includes(2.01));
    }

    @Test
    public void testIncludesLeftOpenRightClosed() {
	Interval<Double> range = new Interval<>(1.0, false, 2.0, true);
	assertFalse(range.includes(0.9999));
	assertFalse(range.includes(1.0));
	assertTrue(range.includes(1.5));
	assertTrue(range.includes(2.0));
	assertFalse(range.includes(2.01));
    }

    @Test
    public void testIncludesOpen() {
	Interval<Double> range = new Interval<>(1.0, false, 2.0, false);
	assertFalse(range.includes(0.9999));
	assertFalse(range.includes(1.0));
	assertTrue(range.includes(1.5));
	assertFalse(range.includes(2.0));
	assertFalse(range.includes(2.01));
    }

    @Test
    public void testToStringClosedInterval() {
	Interval<Double> range = new Interval<>(1.0, true, 2.0, true);
	assertEquals("[1.0, 2.0]", range.toString());
    }

    @Test
    public void testToStringLeftClosedRightOpenInterval() {
	Interval<Double> range = new Interval<>(1.0, true, 2.0, false);
	assertEquals("[1.0, 2.0)", range.toString());
    }

    @Test
    public void testToStringLeftOpenRightClosedInterval() {
	Interval<Double> range = new Interval<>(1.0, false, 2.0, true);
	assertEquals("(1.0, 2.0]", range.toString());
    }

    @Test
    public void testToStringOpenInterval() {
	Interval<Double> range = new Interval<>(1.0, false, 2.0, false);
	assertEquals("(1.0, 2.0)", range.toString());
    }

    @Test
    public void testToStringLeftClosedRightUnboundInterval() {
	Interval<Double> range = new Interval<>(1.0, true, null, false);
	assertEquals("[1.0, )", range.toString());
    }

    @Test
    public void testToStringLeftOpenRightUnboundInterval() {
	Interval<Double> range = new Interval<>(1.0, false, null, false);
	assertEquals("(1.0, )", range.toString());
    }

    @Test
    public void testToStringLeftUnboundRightClosedInterval() {
	Interval<Double> range = new Interval<>(null, false, 1.0, true);
	assertEquals("(, 1.0]", range.toString());
    }

    @Test
    public void testToStringLeftUnboundRightOpenInterval() {
	Interval<Double> range = new Interval<>(null, false, 1.0, false);
	assertEquals("(, 1.0)", range.toString());
    }

}
