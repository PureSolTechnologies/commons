package com.puresoltechnologies.commons.math;

/**
 * This class defines an one dimensional interval.
 *
 * @author Rick-Rainer Ludwig
 *
 * @param <T> is the actual type of the values. The only condition is that these
 *            values are comparable.
 */
public class Interval<T extends Comparable<T>> {

    private final T lowerBound;
    private final boolean lowerBoundIncluded;
    private final T upperBound;
    private final boolean upperBoundIncluded;

    public Interval(T lowerBound, boolean lowerBoundIncluded, T upperBound, boolean upperBoundIncluded) {
	super();
	this.lowerBound = lowerBound;
	this.lowerBoundIncluded = lowerBoundIncluded;
	this.upperBound = upperBound;
	this.upperBoundIncluded = upperBoundIncluded;
    }

    public T getLowerBound() {
	return lowerBound;
    }

    public boolean isLowerBoundIncluded() {
	return lowerBoundIncluded;
    }

    public T getUpperBound() {
	return upperBound;
    }

    public boolean isUpperBoundIncluded() {
	return upperBoundIncluded;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	if (lowerBound == null) {
	    builder.append("(");
	} else {
	    if (lowerBoundIncluded) {
		builder.append("[");
	    } else {
		builder.append("(");
	    }
	    builder.append(lowerBound);
	}
	builder.append(",");
	if (upperBound == null) {
	    builder.append(")");
	} else {
	    builder.append(upperBound);
	    if (upperBoundIncluded) {
		builder.append("]");
	    } else {
		builder.append(")");
	    }
	}
	return builder.toString();
    }
}
