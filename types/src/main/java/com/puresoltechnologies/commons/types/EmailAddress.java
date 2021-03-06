package com.puresoltechnologies.commons.types;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an email address.
 *
 * Details were taken from:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Email_address">
 * http://en.wikipedia.org/wiki/Email_address</a></li>
 * <li><a href=
 * "http://en.wikipedia.org/wiki/Hostname">http://en.wikipedia.org/wiki
 * /Hostname</a></li>
 * </ul>
 *
 * It is planned to introduce a complete check here for the email address to be
 * compliant with the related RFC and to provide some information whether this
 * email address is suspicious for spamming.
 *
 * @author Rick-Rainer Ludwig
 *
 */
public final class EmailAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String HOSTNAME_CHARACTERS = "[-a-zA-Z0-9]";
	private static final String IP_ADDRESS_REGEXP = "\\[(\\d{1,3}\\.){3}\\d{1,3}\\";
	private static final String DOMAIN_PART_REGEXP = "^((" + HOSTNAME_CHARACTERS + "{1,63}\\.)+" + HOSTNAME_CHARACTERS
			+ "{2,63}\\.?|" + IP_ADDRESS_REGEXP + "])$";

	private static final Pattern DOMAIN_PART_PATTERN = Pattern.compile(DOMAIN_PART_REGEXP);

	/**
	 * Validates an email address format.
	 *
	 * @param address is the email address string to validate.
	 * @throws IllegalEmailAddressException is thrown in case of an invalid address.
	 */
	public static void validate(String address) throws IllegalEmailAddressException {
		if (address == null) {
			throw new IllegalEmailAddressException("<null>", "Email address is null.");
		}
		if (address.length() > 254) {
			throw new IllegalEmailAddressException(address, "Email address is longer than 254 characters.");
		}
		String[] parts = address.split("@");
		if (parts.length < 2) {
			int index = address.indexOf('@');
			if (index == 0) {
				throw new IllegalEmailAddressException(address, "Local part must not be empty.");
			} else if (index == (address.length() - 1)) {
				throw new IllegalEmailAddressException(address, "Domain part must not be empty.");
			}
			throw new IllegalEmailAddressException(address, "No @ character included.");
		}
		if (parts.length > 2) {
			throw new IllegalEmailAddressException(address, "Multiple @ characters included.");
		}
		try {
			validateDomainPart(parts[1]);
			validateLocalPart(parts[0]);
		} catch (IllegalEmailAddressException e) {
			throw new IllegalEmailAddressException(address, e.getReason());
		}
	}

	public static void validateDomainPart(String domainPart) {
		int length = domainPart.length();
		if (length > 253) {
			throw new IllegalEmailAddressException("?@" + domainPart,
					"Domain part is longer than 253 characters (" + length + " characters).");
		}
		Matcher matcher = DOMAIN_PART_PATTERN.matcher(domainPart);
		if (!matcher.matches()) {
			throw new IllegalEmailAddressException(domainPart, "Domain part is invalid.");
		}
	}

	public static void validateLocalPart(String localPart) {
		if (localPart == null) {
			throw new IllegalEmailAddressException("<null>@?", "Local part must no be null.");
		}
		if (localPart.isEmpty()) {
			throw new IllegalEmailAddressException(localPart + "@?", "Local part must no be empty.");
		}
		if (localPart.length() > 64) {
			throw new IllegalEmailAddressException(localPart + "@?", "Local part is longer than 64 characters.");
		}
	}

	private final String localPart;
	private final String domainPart;
	private final String address;

	/**
	 * This default constructor is needed for Jackson JSON serialization.
	 */
	public EmailAddress() {
		localPart = null;
		domainPart = null;
		address = null;
	}

	@JsonCreator
	public EmailAddress(@JsonProperty("address") String address) {
		validate(address);
		String[] parts = address.split("@");
		this.address = address;
		this.localPart = parts[0];
		this.domainPart = parts[1];
	}

	public EmailAddress(String localPart, String domainPart) {
		this.address = localPart + "@" + domainPart;
		validate(address);
		this.localPart = localPart;
		this.domainPart = domainPart;
	}

	public final String getLocalPart() {
		return localPart;
	}

	public final String getDomainPart() {
		return domainPart;
	}

	public final String getAddress() {
		return address;
	}

	@Override
	public final String toString() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EmailAddress other = (EmailAddress) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		return true;
	}

	@Override
	protected EmailAddress clone() {
		return new EmailAddress(localPart, domainPart);
	}
}
