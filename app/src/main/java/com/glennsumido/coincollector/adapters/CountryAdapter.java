package com.glennsumido.coincollector.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.glennsumido.coincollector.R;
import com.glennsumido.coincollector.activities.CoinTableActivity;
import com.glennsumido.coincollector.objects.Country;

import java.util.ArrayList;

/**
 * Created by christian on 6/21/15.
 */
public class CountryAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<Country> mCountries;
    LayoutInflater mInflater;

    public CountryAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCountries = new ArrayList<>();
    }

    public void setCountries(ArrayList<Country> countries) {
        mCountries = countries;
    }

    @Override
    public int getCount() {
        return mCountries.size();
    }

    @Override
    public Object getItem(int position) {
        return mCountries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.country_row, parent, false);
            assert contentView != null;
        }

        TextView countryName = (TextView) contentView.findViewById(R.id.country_name);
        TextView currencyCode = (TextView) contentView.findViewById(R.id.currency_code);

        Country country = mCountries.get(position);
        countryName.setText(country.getCountryName());
        currencyCode.setText(country.getCurrencyCode());

        return contentView;
    }
}
