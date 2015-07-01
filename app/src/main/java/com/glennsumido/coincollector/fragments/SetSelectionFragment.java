 package com.glennsumido.coincollector.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glennsumido.coincollector.R;
import com.glennsumido.coincollector.enums.CollectionTypeEnum;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SetSelectionFragment extends Fragment {

    @Bind(R.id.circulation)
    TextView mCirculationView;
    @Bind(R.id.commemorative)
    TextView mComemmorative;

    public static SetSelectionFragment newInstance() {
        SetSelectionFragment fragment = new SetSelectionFragment();
        return fragment;
    }

    public SetSelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_set_selection, container, false);
        ButterKnife.bind(this, view);

        mCirculationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, CountrySelectionFragment.newInstance(CollectionTypeEnum.CIRCULATION))
                        .addToBackStack(null)
                        .commit();
            }
        });

        mCirculationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, CountrySelectionFragment.newInstance(CollectionTypeEnum.COMMEMORATIVE))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
