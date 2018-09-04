package com.puresoltechnologies.commons.ddd;

public abstract class AbstractScalarValueObject<T> extends AbstractValueObject {

    private final T value;

    public AbstractScalarValueObject(T value) {
	super();
	this.value = value;
    }

    public final T getValue() {
	return value;
    }

    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	AbstractScalarValueObject<?> other = (AbstractScalarValueObject<?>) obj;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    @Override
    public final String toString() {
	return value.toString();
    }
}
