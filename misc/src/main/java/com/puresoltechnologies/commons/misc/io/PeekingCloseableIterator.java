package com.puresoltechnologies.commons.misc.io;

import com.puresoltechnologies.commons.misc.PeekingIterator;

/**
 * This interface is a combination of {@link CloseableIterator} and
 * {@link PeekingIterator}.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 */
public interface PeekingCloseableIterator<T> extends CloseableIterator<T>, PeekingIterator<T> {
}
