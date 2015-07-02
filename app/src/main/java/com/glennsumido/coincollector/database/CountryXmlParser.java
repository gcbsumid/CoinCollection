package com.glennsumido.coincollector.database;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import com.glennsumido.coincollector.objects.Country;
import com.google.android.gms.analytics.Logger;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by christian on 6/21/15.
 * Parsing XML Data: http://developer.android.com/training/basics/network-ops/xml.html#instantiate
 */
public class CountryXmlParser {
    private static final String ns = null;

    public static ArrayList<Country> parse(XmlResourceParser parser) throws XmlPullParserException, IOException {
        return readFeed(parser);
    }

    private static ArrayList<Country> readFeed(XmlResourceParser parser) throws XmlPullParserException, IOException{
        ArrayList<Country> countries = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("country")) {
                countries.add(readCountry(parser));
            }
        }
        return countries;
    }

    private static Country readCountry(XmlResourceParser parser) throws IOException, XmlPullParserException {
        Country country = Country.newInstance("NA", "NA", "NA");

        parser.require(XmlPullParser.START_TAG, ns, "country");
        String tag = parser.getName();
        String countryCode = parser.getAttributeValue(null, "countryCode");
        String countryName = parser.getAttributeValue(null, "countryName");
        String currencyCode = parser.getAttributeValue(null, "currencyCode");
        if (tag.equals("country")) {
            if (!countryCode.isEmpty() && !countryName.isEmpty() && !currencyCode.isEmpty()) {
                country = Country.newInstance(countryCode, countryName, currencyCode);
            }
        }
//        Log.i("XmlParser", String.format("Country: %s", countryName));

        // Todo: this should never be "NA" for the country
        return country;
    }
}
