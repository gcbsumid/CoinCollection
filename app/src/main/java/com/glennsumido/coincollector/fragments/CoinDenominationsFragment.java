package com.glennsumido.coincollector.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glennsumido.coincollector.R;
import com.glennsumido.coincollector.objects.Country;

/**
 * A placeholder fragment containing a simple view.
 */
public class CoinDenominationsFragment extends Fragment {
    private static final String countryArgTag = "COUNTRY";
    private Country mCountry;

    public CoinDenominationsFragment() {
    }

    public static CoinDenominationsFragment newInstance(Country country) {
        CoinDenominationsFragment fragment = new CoinDenominationsFragment();
        Bundle args = new Bundle();
        args.putSerializable(countryArgTag, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCountry = (Country) getArguments().getSerializable(countryArgTag);
        } else {
            Log.e("CoinDominationsFragment", "There are no arguments passed.");
        }
        Log.i("CoinDominationsFragment", "Country Selected: " + mCountry.getCountryName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coin_denominations, container, false);
    }
}
