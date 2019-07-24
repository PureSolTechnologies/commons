package com.puresoltechnologies.commons.ddd;

/**
 * This is an abstract implementation of a {@link Aggregate} which can be used
 * to create the DDD Aggregates.
 * <p>
 * Main purpose is to enforce implementers to define their own implementations
 * of {@link #hashCode()}, {@link #equals(Object)} and {@link #toString()} and
 * also to enforce consistency checks.
 *
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractAggregate implements Aggregate {

    /**
     * This method is enforced to be implemented to have a check for consistency of
     * the aggregated values. This method has to be called by the implemented as
     * last statement in its constructor. This cannot be done by
     * {@link AbstractAggregate}.
     *
     * @throws NullPointerException     is thrown in cases of <code>null</code>
     *                                  values where values are expected. (Better
     *                                  use Optionals for nullable values.)
     * @throws IllegalArgumentException is thrown if non-null values are invalid or
     *                                  combination of aggregated values are not
     *                                  matching.
     */
    protected abstract void checkConsistency() throws NullPointerException, IllegalArgumentException;

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

}
