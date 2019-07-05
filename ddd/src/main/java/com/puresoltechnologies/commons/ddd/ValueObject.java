package com.puresoltechnologies.commons.ddd;

/**
 * This is the interface for a DDD ValueObject. The interface is used as a
 * marker. The actual implementation shall be immutable.
 * <p>
 * Value objects do not have an identity like {@link Entity} objects and just
 * store values. Comparison is done via the attributes.
 *
 * @author Rick-Rainer Ludwig
 *
 */
public interface ValueObject extends DDDDataObject {

}
