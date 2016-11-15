package com.puresoltechnologies.commons.misc.io;

/**
 * This is a abstract implementation
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 */
public abstract class AbstractPeekingCloseableIterator<T> implements PeekingCloseableIterator<T> {

    private T next = null;

    @Override
    public final boolean hasNext() {
	if (next == null) {
	    next = readNext();
	}
	return next != null;
    }

    @Override
    public final T next() {
	if (next == null) {
	    next = readNext();
	}
	T result = next;
	next = null;
	return result;
    }

    @Override
    public final T peek() {
	if (next == null) {
	    next = readNext();
	}
	return next;
    }

    protected abstract T readNext();

}
