package com.glennsumido.coincollector.objects;

/**
 * Created by christian on 6/21/15.
 */
public class Country {
    private String mCountryCode;
    private String mCountryName;
    private String mCurrencyCode;

    public static Country newInstance(String countryCode, String countryName, String currencyCode) {
        Country country = new Country(countryCode, countryName, currencyCode);
        return country;
    }

    public Country(String countryCode, String countryName, String currencyCode) {
        mCountryCode = countryCode;
        mCountryName = countryName;
        mCurrencyCode = currencyCode;
    }

    public String GetCountryCode() {
        return mCountryCode;
    }

    public String GetCountryName() {
        return mCountryName;
    }

    public String GetCurrencyCode() {
        return mCurrencyCode;
    }
}
