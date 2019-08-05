package com.puresoltechnologies.commons.ddd;

/**
 * This interface marks a class a repository for a certain type of objects.
 *
 * @author Rick-Rainer Ludwig
 *
 * @param <T> is the actual type of the objects to be stored and read.
 */
public interface Repository<T extends DDDDataObject> extends DDDElement {

    /**
     * This method inserts the provided object into the repository.
     * 
     * @param o is the object to be stored.
     */
    void insert(T o);

    /**
     * This method deletes the provided object from the repository.
     * 
     * @param o is the object to be deleted.
     */
    void delete(T o);

    /**
     * This method returns all elements from the repository as {@link Iterable}.
     * 
     * @return A {@link Iterable} is returned containing all elements from
     *         repositories.
     */
    Iterable<T> selectAll();

}
