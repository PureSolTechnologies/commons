package com.puresoltechnologies.commons.ddd;

/**
 * In DDD a service encapsulates operations which do not belong to
 * {@link ValueObject} or {@link Entity} objects.
 * <p>
 * As services do not have pre-defined functionality, this interface is an empty
 * marker interface.
 *
 * @author Rick-Rainer Ludwig
 */
public interface Service extends DDDElement {

}
