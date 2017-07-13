package com.puresoltechnologies.commons.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class CountryTest {

    @Test
    public void testGetCountries() {
	List<Country> countries = Country.getCountries();
	assertNotNull("Countries list is null.", countries);
	assertFalse("Countries list is empty.", countries.isEmpty());
	System.out.println("Supported countries:");
	for (Country country : countries) {
	    System.out.println(country.toString());
	}
    }

    @Test
    public void testIsoCountries() {
	String[] isoCountries = Locale.getISOCountries();
	for (String country : isoCountries) {
	    System.out.println(country);
	}
    }

    @Test
    public void testIsoLanguages() {
	String[] languages = Locale.getISOLanguages();
	for (String language : languages) {
	    System.out.println(language);
	}
    }

    @Test
    public void testLocales() {
	Locale[] locales = Locale.getAvailableLocales();
	for (Locale locale : locales) {
	    System.out.println(locale);
	}
    }
}
