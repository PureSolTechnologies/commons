package com.puresoltechnologies.commons.misc;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * This class is a simple implementation of a stop watch used for debugging and
 * performance tests.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StopWatch implements Serializable {

    private static final long serialVersionUID = -8824942249939423671L;

    private Instant startTime = null;
    private Instant stopTime = null;
    private Duration duration = null;

    public void start() {
	stopTime = null;
	duration = null;
	startTime = Instant.now();
    }

    public void stop() {
	stopTime = Instant.now();
	duration = Duration.between(startTime, stopTime);
    }

    public Instant getStartTime() {
	return startTime != null ? startTime : null;
    }

    public Instant getStopTime() {
	return stopTime != null ? stopTime : null;
    }

    public Duration getDuration() {
	return duration;
    }

    public double getSeconds() {
	return duration != null ? duration.get(ChronoUnit.NANOS) / 1e9 : 0.0;
    }

    public long getMillis() {
	return duration != null
		? (long) (duration.get(ChronoUnit.SECONDS) * 1000.0 + duration.get(ChronoUnit.NANOS) / 1e6) : 0l;
    }

    @Override
    public String toString() {
	return getSeconds() + "s";
    }
}
