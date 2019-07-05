package com.puresoltechnologies.commons.ddd;

/**
 * This interface is used to define a custom type for DDD to assure type safety.
 * It is used to wrap generic types like String, double,... to make them
 * explicit type likes EmailAddress, Bias, ...
 *
 * @param <T> is the original type of the value to be wrapped.
 *
 * @author Rick-Rainer Ludwig
 */
public interface CustomType<T> {

    /**
     * This method returns the actual value wrapped by this class.
     *
     * @return The value in its original type T is returned.
     */
    T getValue();

}
