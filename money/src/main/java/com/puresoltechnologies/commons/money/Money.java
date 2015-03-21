package com.puresoltechnologies.commons.money;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class is inspired by the Money implementation in Fowler's book
 * "Enterprise Application Patterns".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Money extends Number implements Comparable<Money>, Serializable {

    private static final long serialVersionUID = -6697323264404131582L;

    /**
     * This field keeps the currency symbol of the currency.
     */
    private final String currency;

    /**
     * This field contains the amount of the currency. This field contains the
     * amount in the smallest unit for the specified currency! For Dollar it
     * would be Cent for instance.
     */
    private final long amount;

    /**
     * This field contains the fraction of the currency's smallest unit to the
     * major unit.
     */
    private final int fraction;

    public Money(String currency, int fraction, long amount) {
	if (currency == null) {
	    throw new IllegalArgumentException("Currency must not be null");
	}
	this.currency = currency;
	this.fraction = fraction;
	checkFraction();
	this.amount = amount;
    }

    private void checkFraction() {
	if (fraction <= 0) {
	    throw new IllegalArgumentException(
		    "The fraction must not be negative or zero.");
	}
	int temp = fraction;
	while (temp > 10) {
	    if (temp == 1) {
		break;
	    }
	    if (temp % 10 != 0) {
		throw new IllegalArgumentException(
			"The fraction must be a positive number with any positive exponent to the base of 10.");
	    }
	    temp /= 10;
	}
    }

    public String getCurrency() {
	return currency;
    }

    public long getAmount() {
	return amount;
    }

    public int getFraction() {
	return fraction;
    }

    @Override
    public String toString() {
	long major = amount / fraction;
	long minor = amount % fraction;
	StringBuilder builder = new StringBuilder();
	builder.append(major);
	if (minor != 0) {
	    builder.append(".");
	    builder.append(minor);
	}
	builder.append(currency);
	return builder.toString();
    }

    /**
     * This method calculates shares of this money object which are weigthed by
     * given ratios.
     * 
     * <b>Attention:</b> It is not guaranteed that all shares have exactly the
     * ratios to each other as specified due to the side condition that the sum
     * of all shares need to be <b>exactly</b> the source amount of money. The
     * error of the share difference is maximal the amount of the smallest
     * possible money amount.
     * 
     * @param ratios
     *            are the ration to be taken into acount for share calculation.
     * @return An array of {@link Money} is returned containing the shares.
     */
    public Money[] share(double... ratios) {
	if (ratios.length == 0) {
	    throw new IllegalArgumentException(
		    "At least one ration value needs to be provided.");
	}
	long[] amountAllocation = MoneyMath.allocate(amount, ratios);
	Money allocation[] = new Money[ratios.length];
	for (int i = 0; i < ratios.length; i++) {
	    allocation[i] = new Money(currency, fraction, amountAllocation[i]);
	}
	return allocation;
    }

    /**
     * The method calculates equal shares. Only the number of shares need to be
     * specified.
     * 
     * <b>Attention:</b> It is not guaranteed that all shares have an equal
     * amount due to the side condition that the sum of all shares need to be
     * <b>exactly</b> the source amount of money. The error of the share
     * difference is maximal the amount of the smallest possible money amount.
     * 
     * @param numberOfShares
     *            is the number of shares the current amount of money is split
     *            into.
     * @return An array of {@link Money} is returned containing the shares.
     */
    public Money[] share(int numberOfShares) {
	if (numberOfShares <= 0) {
	    throw new IllegalArgumentException(
		    "Number of shares has to be greater than zero.");
	}
	int ratios[] = new int[numberOfShares];
	Arrays.fill(ratios, 1);
	long[] amountAllocation = MoneyMath.allocate(amount, ratios);
	Money allocation[] = new Money[ratios.length];
	for (int i = 0; i < ratios.length; i++) {
	    allocation[i] = new Money(currency, fraction, amountAllocation[i]);
	}
	return allocation;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (amount ^ (amount >>> 32));
	result = prime * result
		+ ((currency == null) ? 0 : currency.hashCode());
	result = prime * result + fraction;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Money other = (Money) obj;
	if (amount != other.amount)
	    return false;
	if (currency == null) {
	    if (other.currency != null)
		return false;
	} else if (!currency.equals(other.currency))
	    return false;
	if (fraction != other.fraction)
	    return false;
	return true;
    }

    @Override
    public int compareTo(Money o) {
	if (!currency.equals(o.currency)) {
	    throw new IllegalArgumentException(
		    "Comparing two money values is only supported with same currency, got '"
			    + currency + "' and '" + o.currency + "'.");
	}
	if (fraction != o.fraction) {
	    throw new IllegalArgumentException(
		    "Comparing two money values is only supported with same fraction, got '"
			    + fraction + "' and '" + o.fraction + "'.");
	}
	return Long.valueOf(amount).compareTo(o.amount);
    }

    /**
     * Implementation of {@link Number#intValue()}. It returns the result of
     * {@link #getAmount()} as int.
     * 
     * An {@link IllegalStateException} is thrown in case the int value is to
     * small for the amount.
     * 
     * @return An int is returned containing the amount of money.
     */
    @Override
    public int intValue() {
	if (Math.abs(amount) <= Integer.MAX_VALUE) {
	    return (int) amount;
	}
	throw new IllegalStateException("Amount of '" + amount
		+ "' cannot be converted into an Integer.");
    }

    /**
     * Implementation of {@link Number#longValue()}. It returns the result of
     * {@link #getAmount()} as long.
     * 
     * 
     * @return A long is returned containing the amount of money.
     */
    @Override
    public long longValue() {
	return amount;
    }

    /**
     * <p>
     * Implementation of {@link Number#floatValue()}. It returns the result of
     * {@link #getAmount()} <b>divided</b> by {@link #getFraction()}. This is
     * the difference to {@link #intValue()} and {@link #longValue()} where the
     * correct number of amount is returned. Here the result is calculated
     * already with fractions into a floating point number.
     * </p>
     * <p>
     * <b>Beware:</b> Do not use the result of this method for further money
     * calculations!
     * </p>
     * 
     * @return A float is returned containing the amount of money.
     */
    @Override
    public float floatValue() {
	return (float) amount / (float) fraction;
    }

    /**
     * <p>
     * Implementation of {@link Number#doubeValue()}. It returns the result of
     * {@link #getAmount()} <b>divided</b> by {@link #getFraction()}. This is
     * the difference to {@link #intValue()} and {@link #longValue()} where the
     * correct number of amount is returned. Here the result is calculated
     * already with fractions into a floating point number.
     * </p>
     * <p>
     * <b>Beware:</b> Do not use the result of this method for further money
     * calculations!
     * </p>
     * 
     * @return A double is returned containing the amount of money.
     */
    @Override
    public double doubleValue() {
	return (double) amount / (double) fraction;
    }
}
