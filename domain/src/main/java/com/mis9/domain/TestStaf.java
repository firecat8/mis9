package com.mis9.domain;

import java.util.Locale;

/**
 *
 * @author gdimitrova
 */
public class TestStaf {

    public static void main(String[] args) throws InterruptedException {
        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            System.out.println("Country Code = " + obj.getCountry()
                    + ", ISO3Country Code = " + obj.getISO3Country()
                    + ", Country Name = " + obj.getDisplayCountry());

        }
    }
}
