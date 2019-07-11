package com.puresoltechnologies.commons.types;

/**
 * This is a special interface to be implemented by classes which implement a
 * counting functionality like streams counting bytes, iterators counting
 * elements or statistics classes counting samples.
 *
 * @author Rick-Rainer Ludwig
 * @param <T> is the type of the counter. It makes most sense to use an integer
 *            type like {@link Byte}, {@link Short}, {@link Integer} or
 *            {@link Long}.
 */
public interface Counter<T extends Number> {

    /**
     * This method returns the current count. <code>null</code> may be returned in
     * case the class was not initialized, yet.
     *
     * @return An object of T is returned.
     */
    T getCount();

}
