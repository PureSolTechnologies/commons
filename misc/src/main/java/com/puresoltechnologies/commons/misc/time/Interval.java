package com.puresoltechnologies.commons.misc.time;

import java.time.Instant;

/**
 * This class is an addition to {@link java.time}. It add support for time
 * interval what is a missing feature. It is simply adding some functionality
 * Joda Time's Interval implementation provided.
 *
 * @author Rick-Rainer Ludwig
 */
public class Interval {

    public static Interval of(Instant from, Instant to) {
	return new Interval(from, to);
    }

    private final Instant from;
    private final Instant to;

    public Interval(Instant from, Instant to) {
	if ((!from.isBefore(to)) && (!from.equals(to))) {
	    throw new IllegalArgumentException("From instant is not before to instant.");
	}
	this.from = from;
	this.to = to;
    }

    public boolean contains(Instant time) {
	if ((from.isAfter(time)) || (to.isBefore(time))) {
	    return false;
	} else {
	    return true;
	}
    }

    public boolean overlaps(Interval interval) {
	if ((from.isAfter(interval.to)) || (interval.to.isBefore(from))) {
	    return false;
	} else {
	    return true;
	}
    }
}