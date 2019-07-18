package com.puresoltechnologies.commons.ddd;

public abstract class AbstractScalarValueObject<T> extends AbstractValueObject {

    private final T value;
    private final int hashCode;

    public AbstractScalarValueObject(T value) {
	super();
	this.value = value;
	this.hashCode = value.hashCode();
    }

    public final T getValue() {
	return value;
    }

    @Override
    public final int hashCode() {
	return hashCode;
    }

    @Override
    public final boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	AbstractScalarValueObject<?> other = (AbstractScalarValueObject<?>) obj;
	if (hashCode != other.hashCode) {
	    return false;
	}
	if (value == null) {
	    if (other.value != null) {
		return false;
	    }
	} else if (!value.equals(other.value)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return value.toString();
    }
}
