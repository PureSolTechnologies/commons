package com.puresoltechnologies.commons.ddd;

/**
 * This is an abstract implementation of an {@link Entity}.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <I>
 *            is the actual type of the id.
 */
public class AbstractEntity<I> implements Entity<I> {

    private final I id;

    public AbstractEntity(I id) {
	super();
	this.id = id;
    }

    @Override
    public final I getId() {
	return id;
    }

    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public final boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractEntity<?> other = (AbstractEntity<?>) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
