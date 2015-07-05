package com.glennsumido.coincollector.objects;

import java.io.Serializable;

/**
 * Created by christian on 6/21/15.
 */
public class Country implements Serializable{
    private String countryCode;
    private String countryName;
    private String currencyCode;

    public static Country newInstance(String countryCode, String countryName, String currencyCode) {
        Country country = new Country(countryCode, countryName, currencyCode);
        return country;
    }

    public Country(String countryCode, String countryName, String currencyCode) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencyCode = currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
