package com.glennsumido.coincollector.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.glennsumido.coincollector.R;

import com.glennsumido.coincollector.adapters.CountryAdapter;
import com.glennsumido.coincollector.database.CountryXmlParser;
import com.glennsumido.coincollector.enums.CollectionTypeEnum;
import com.glennsumido.coincollector.objects.Country;
import com.google.android.gms.plus.PlusOneButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link CountrySelectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountrySelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountrySelectionFragment extends Fragment {
    // the fragment initialization type for the bundle'd argument
    private static final String COLLECTION_TYPE = "collection_type";

    private CollectionTypeEnum mCollectionType;
    private ListView mCountryListView;

    private OnFragmentInteractionListener mListener;
    private ProcessCountriesTask mProcessCountriesTask;

    /**
     * Async Task that will handle the processing of the static countries list
     */
    private class ProcessCountriesTask extends AsyncTask<Void, Void, ArrayList<Country>> {
        @Override
        protected ArrayList<Country> doInBackground(Void... params) {
            Resources res = getActivity().getResources();
            ArrayList<Country> list = new ArrayList<>();

            try {
                XmlPullParser parser = res.getXml(R.xml.country_codes);
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
            mCountryListView.setAdapter(new CountryAdapter(getActivity(), result));
        }
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_selection, container, false);

        mCountryListView = (ListView) view.findViewById(R.id.countryListView);

        // Processes xml to get task
        mProcessCountriesTask.execute();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
