package com.puresoltechnologies.commons.ddd;

import com.puresoltechnologies.streaming.iterators.StreamIterable;

/**
 * This interface marks a class a repository for a certain type of objects.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 *            is the actual type of the objects to be stored and read.
 */
public interface Repository<T extends DDDDataObject> extends DDDElement {

    /**
     * This method inserts the provided object into the repository.
     * 
     * @param o
     *              is the object to be stored.
     */
    public void insert(T o);

    /**
     * This method deletes the provided object from the repository.
     * 
     * @param o
     *              is the object to be deleted.
     */
    public void delete(T o);

    /**
     * This method returns all elements from the repository as
     * {@link StreamIterable}.
     * 
     * @return A {@link StreamIterable} is returned containing all elements from
     *         repositories.
     */
    public StreamIterable<T> selectAll();

}
