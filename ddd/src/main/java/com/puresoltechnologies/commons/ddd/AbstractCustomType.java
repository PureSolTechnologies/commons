package com.puresoltechnologies.commons.ddd;

/**
 * This is an abstract implementation for {@link CustomType} to provide generic
 * functionality common for all custom types.
 * <p>
 * The implementation is immutable. Therefore, it is safe to use references
 * only. It is also thread-safe by nature.
 * <p>
 * Implementations extending this class should make there constructors private
 * and implement static <code>.of(T value)</code> methods. These
 * <code>.of(T)</code> methods should take the primitive, non-wrapper types for
 * the values to assure, that there is no <code>null</code> value provided.
 *
 * @author Rick-Rainer Ludwig
 *
 * @param <T> is the original type of the value to be wrapped.
 */
public abstract class AbstractCustomType<T> implements CustomType<T> {

    private final T value;
    private int hashCode;

    /**
     * This is the default constructor which takes any primitive value to wrap it.
     *
     * @param value is the value to be wrapped.
     */
    protected AbstractCustomType(T value) {
	super();
	this.value = value;
	hashCode = value.hashCode();
    }

    /**
     * This is a copy constructor for convenience only. As this type is immutable,
     * there should be no need to use it.
     *
     * @param wrappedValue is the wrapped value to be used. The wrapped value is
     *                     unwrapped and re-wrapped.
     */
    protected AbstractCustomType(CustomType<T> wrappedValue) {
	super();
	this.value = wrappedValue.getValue();
    }

    @Override
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
	AbstractCustomType<?> other = (AbstractCustomType<?>) obj;
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

}
