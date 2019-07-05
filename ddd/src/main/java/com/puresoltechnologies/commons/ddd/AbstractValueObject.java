package com.puresoltechnologies.commons.ddd;

/**
 * This is an abstract implementation of a {@link ValueObject} which can be used
 * to create the DDD ValueObjects.
 *
 * @author Rick-Rainer Ludwig
 *
 */
public abstract class AbstractValueObject implements ValueObject {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

}
