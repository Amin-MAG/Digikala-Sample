package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.R;

public class FilterToolbarFragment extends Fragment {

    public static final String ARG_SEARCH_STRING = "arg_search_string";
    private String searchString;

    public static FilterToolbarFragment newInstance(String searchString) {

        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_STRING, searchString);

        FilterToolbarFragment fragment = new FilterToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FilterToolbarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        searchString = getArguments().getString(ARG_SEARCH_STRING);

    }

}
