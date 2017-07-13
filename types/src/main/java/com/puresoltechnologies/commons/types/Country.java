package com.puresoltechnologies.commons.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to provide country information of ISO_3166-1.
 * 
 * @see https://en.wikipedia.org/wiki/ISO_3166-1
 * 
 * @author Rick-Rainer Ludwig
 */
public class Country {

    private static final List<Country> countries = new ArrayList<>();
    static {
	Locale[] locales = Locale.getAvailableLocales();
	for (Locale locale : locales) {
	    try {
		String name = locale.getDisplayCountry(Locale.US);
		if ((name == null) || (name.isEmpty())) {
		    continue;
		}
		String iso2 = locale.getCountry();
		if ((iso2 == null) || (iso2.isEmpty())) {
		    continue;
		}
		String iso3 = locale.getISO3Country();
		countries.add(new Country(name, iso2, iso3));
	    } catch (MissingResourceException e) {
		// Something is missing, so we skip it..
		// For debugging: System.err.println(e.getMessage());
	    }
	}
    }

    public static List<Country> getCountries() {
	return Collections.unmodifiableList(countries);
    }

    private final String name;
    private final String alpha2Code;
    private final String alpha3Code;

    @JsonCreator
    public Country( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("alpha2Code") String alpha2Code, //
	    @JsonProperty("alpha3Code") String alpha3Code //
    ) {
	super();
	this.name = name;
	this.alpha2Code = alpha2Code;
	this.alpha3Code = alpha3Code;
    }

    public String getName() {
	return name;
    }

    public String getAlpha2Code() {
	return alpha2Code;
    }

    public String getAlpha3Code() {
	return alpha3Code;
    }

    @Override
    public String toString() {
	return name + " (" + alpha2Code + "/" + alpha3Code + ")";
    }

}
