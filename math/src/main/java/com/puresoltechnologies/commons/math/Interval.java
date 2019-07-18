package com.puresoltechnologies.commons.math;

import java.util.Objects;

/**
 * This class defines an one dimensional interval.
 *
 * This interval supports all possible combinations of bound and unbound and
 * closed and open intervals. Unbound intervals are set with null values instead
 * of specific values and open or closed boundaries are specified with flags.
 *
 * @author Rick-Rainer Ludwig
 *
 * @param <T> is the actual type of the values. The only condition is that these
 *            values are comparable.
 */
public class Interval<T extends Comparable<T>> {

    private final T lowerBound;
    private final boolean lowerBoundClosed;
    private final T upperBound;
    private final boolean upperBoundClosed;
    private final int hashCode;

    /**
     * This is the full initial value constructor.
     *
     * @param lowerBound       is the lower value of this interval. This value may
     *                         be null to signal that there is no lower boundary
     *                         (not left bounded).
     * @param lowerBoundClosed specifies whether the lower bound is included in the
     *                         range (whether it is left closed). This value is not
     *                         allowed to be true in case of no lower boundary.
     * @param upperBound       is the upper value of this interval. This value may
     *                         be null to signal that there is no upper boundary
     *                         (not right bounded).
     * @param upperBoundClosed specifies whether the upper bound is included in the
     *                         range (whether it is right closed). This value is not
     *                         allowed to be true in case of no upper boundary.
     */
    public Interval(T lowerBound, boolean lowerBoundClosed, T upperBound, boolean upperBoundClosed) {
	super();
	if ((lowerBound == null) && (lowerBoundClosed)) {
	    throw new IllegalArgumentException("An unbound lower boundary can not be defined as closed.");
	}
	if ((upperBound == null) && (upperBoundClosed)) {
	    throw new IllegalArgumentException("An unbound upper boundary can not be defined as closed.");
	}
	this.lowerBound = lowerBound;
	this.lowerBoundClosed = lowerBoundClosed;
	this.upperBound = upperBound;
	this.upperBoundClosed = upperBoundClosed;
	this.hashCode = Objects.hash(lowerBound, lowerBoundClosed, upperBound, upperBoundClosed);
    }

    /**
     * Returns the lower bound.
     *
     * @return Returns the value of the lower bound or <code>null</code> if it is
     *         left unbound.
     */
    public T getLowerBound() {
	return lowerBound;
    }

    /**
     * Returns whether the lower bound is part of the interval (left closed).
     *
     * @return <code>true</code> is returned in case the lower bound is included in
     *         the interval. <code>false</code> is returned if not.
     */
    public boolean isLowerBoundClosed() {
	return lowerBoundClosed;
    }

    /**
     * Returns the upper bound.
     *
     * @return Returns the value of the upper bound or <code>null</code> if it is
     *         right unbound.
     */
    public T getUpperBound() {
	return upperBound;
    }

    /**
     * Returns whether the upper bound is part of the interval (right closed).
     *
     * @return <code>true</code> is returned in case the upper bound is included in
     *         the interval. <code>false</code> is returned if not.
     */
    public boolean isUpperBoundClosed() {
	return upperBoundClosed;
    }

    /**
     * Checks whether a specified version is included in the version range or not.
     *
     * @param value is the {@link Version} to be tested agains this range.
     * @return <code>true</code> is returned in case the version is within the
     *         current range. <code>false</code> is returned otherwise.
     */
    public final boolean includes(T value) {
	if (lowerBound != null) {
	    int minimumComparison = lowerBound.compareTo(value);
	    if (minimumComparison > 0) {
		return false;
	    }
	    if ((!lowerBoundClosed) && (minimumComparison == 0)) {
		return false;
	    }
	}
	if (upperBound != null) {
	    int maximumComparison = upperBound.compareTo(value);
	    if (maximumComparison < 0) {
		return false;
	    }
	    if ((!upperBoundClosed) && (maximumComparison == 0)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public int hashCode() {
	return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Interval other = (Interval) obj;
	if (hashCode != other.hashCode) {
	    return false;
	}
	if (lowerBound == null) {
	    if (other.lowerBound != null) {
		return false;
	    }
	} else if (!lowerBound.equals(other.lowerBound)) {
	    return false;
	}
	if (lowerBoundClosed != other.lowerBoundClosed) {
	    return false;
	}
	if (upperBound == null) {
	    if (other.upperBound != null) {
		return false;
	    }
	} else if (!upperBound.equals(other.upperBound)) {
	    return false;
	}
	if (upperBoundClosed != other.upperBoundClosed) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	if (lowerBound == null) {
	    builder.append("(");
	} else {
	    if (lowerBoundClosed) {
		builder.append("[");
	    } else {
		builder.append("(");
	    }
	    builder.append(lowerBound);
	}
	builder.append(", ");
	if (upperBound == null) {
	    builder.append(")");
	} else {
	    builder.append(upperBound);
	    if (upperBoundClosed) {
		builder.append("]");
	    } else {
		builder.append(")");
	    }
	}
	return builder.toString();
    }
}
