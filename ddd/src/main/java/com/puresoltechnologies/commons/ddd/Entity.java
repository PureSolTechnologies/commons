package com.puresoltechnologies.commons.ddd;

/**
 * This is the interface for a DDD Entity. The interface is used as a marker.
 * The actual implementation needs to be immutable.
 * <p>
 * Entity objets have an identifier and comparison is done by the identifier
 * only.
 *
 * @author Rick-Rainer Ludwig
 *
 * @param <I> is the type of the ID.
 */
public interface Entity<I> extends DDDDataObject {

    /**
     * This method returns the ID of the entity.
     *
     * @return The id is returned with type T.
     */
    I getId();

}
