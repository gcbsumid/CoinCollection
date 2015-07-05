package com.glennsumido.coincollector.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;

import com.glennsumido.coincollector.R;

import com.glennsumido.coincollector.activities.CoinTableActivity;
import com.glennsumido.coincollector.adapters.CountryAdapter;
import com.glennsumido.coincollector.database.CountryXmlParser;
import com.glennsumido.coincollector.enums.CollectionTypeEnum;
import com.glennsumido.coincollector.objects.Country;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CountrySelectionFragment extends Fragment implements ListView.OnItemClickListener{
    private static final String COLLECTION_TYPE = "collection_type";

    private CollectionTypeEnum mCollectionType;
    private ListView mCountryListView;

    private ProcessCountriesTask mProcessCountriesTask;
    private CountryAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CountrySelectionFragment.
     */
    public static CountrySelectionFragment newInstance(CollectionTypeEnum collectionType) {
        CountrySelectionFragment fragment = new CountrySelectionFragment();
        Bundle args = new Bundle();
        args.putSerializable(COLLECTION_TYPE, collectionType);
        fragment.setArguments(args);
        return fragment;
    }

    public CountrySelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCollectionType = (CollectionTypeEnum) getArguments().getSerializable(COLLECTION_TYPE);
        }
        mAdapter = new CountryAdapter(getActivity().getApplicationContext());

        mProcessCountriesTask = new ProcessCountriesTask();
        // Processes xml to get task
        mProcessCountriesTask.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_selection, container, false);

        mCountryListView = (ListView) view.findViewById(R.id.countryListView);
        mCountryListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Country country = (Country) mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), CoinTableActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("COUNTRY", country);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Async Task that will handle the processing of the static countries list
     */
    private class ProcessCountriesTask extends AsyncTask<Void, Void, ArrayList<Country>> {
        @Override
        protected ArrayList<Country> doInBackground(Void... params) {
            ArrayList<Country> list = new ArrayList<>();

            try {
                XmlResourceParser parser = getResources().getXml(R.xml.country_codes);

                list = CountryXmlParser.parse(parser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<Country> result) {
            mAdapter.setCountries(result);
            mCountryListView.setAdapter(mAdapter);
        }
    }
}
