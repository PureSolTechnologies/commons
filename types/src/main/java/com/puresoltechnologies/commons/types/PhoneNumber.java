package com.puresoltechnologies.commons.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Phone number in the form of E164.
 * 
 * @see https://en.wikipedia.org/wiki/E.164
 * @author Rick-Rainer Ludwig
 */
public class PhoneNumber {

    private static final List<CountryCode> countryCodes = new ArrayList<>();
    static {
	try (BufferedReader reader = new BufferedReader(
		new InputStreamReader(Country.class.getResourceAsStream("iso-3166-1.txt"), Charset.defaultCharset()))) {
	    String row;
	    while ((row = reader.readLine()) != null) {
		String[] columns = row.split("\t");
		String name = columns[0];
		String codes = columns[1];
		countryCodes.add(new CountryCode(name, codes));
	    }
	} catch (IOException e) {
	    throw new RuntimeException("Could not initialize countries list.", e);
	}
    }

    public static final List<CountryCode> getCountryCodes() {
	return Collections.unmodifiableList(countryCodes);
    }

    public static class CountryCode {

	private final String code;
	private final String iso2;

	public CountryCode(String code, String iso2) {
	    super();
	    this.code = code;
	    this.iso2 = iso2;
	}

	public String getCode() {
	    return code;
	}

	public String getIso2() {
	    return iso2;
	}

    }

    private final String countryCode;
    private final String identificationCode;
    private final String subscriberNumber;

    public PhoneNumber(String countryCode, String identificationCode, String subscriberNumber) {
	super();
	this.countryCode = countryCode;
	this.identificationCode = identificationCode;
	this.subscriberNumber = subscriberNumber;
    }

    public String getCountryCode() {
	return countryCode;
    }

    public String getIdentificationCode() {
	return identificationCode;
    }

    public String getSubscriberNumber() {
	return subscriberNumber;
    }

}
