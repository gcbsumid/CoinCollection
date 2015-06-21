package com.glennsumido.coincollector.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.glennsumido.coincollector.R;
import com.glennsumido.coincollector.objects.Country;

import java.util.ArrayList;

/**
 * Created by christian on 6/21/15.
 */
public class CountryAdapter extends BaseAdapter{
    // Todo: This
    Context mContext;
    ArrayList<Country> mCountries;
    LayoutInflater mInflater;

    public CountryAdapter(Context context, ArrayList<Country> countries) {
        mContext = context;
        mCountries = countries;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCountries.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        countryName.setText(country.GetCountryName());
        currencyCode.setText(country.GetCurrencyCode());

        return contentView;
    }
}
