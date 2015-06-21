package com.glennsumido.coincollector.database;

import android.util.Xml;

import com.glennsumido.coincollector.objects.Country;

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

    public static ArrayList<Country> parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        return readFeed(parser);
    }

    private static ArrayList<Country> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException{
        ArrayList<Country> countries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "countries");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("country")) {
                countries.add(readCountry(parser));
            } else {
                skip(parser);
            }
        }
        return countries;
    }

    private static Country readCountry(XmlPullParser parser) throws IOException, XmlPullParserException {
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
        parser.require(XmlPullParser.END_TAG, ns, "country");

        // Todo: this should never be "NA" for the country
        return country;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException{
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
