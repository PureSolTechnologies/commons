package com.puresoltechnologies.commons.ddd;

/**
 * This is an abstract implementation of a {@link ValueObject} which can be used
 * to create the DDD ValueObjects.
 * <p>
 * Main purpose is to enforce implementers to define their own implementations
 * of {@link #hashCode()}, {@link #equals(Object)} and {@link #toString()}.
 *
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractValueObject implements ValueObject {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

}
