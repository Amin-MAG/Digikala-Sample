package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mag.digikala.R;


public class CommonToolbarFragment extends Fragment {

    public static final String ARG_COMMON_TOOLBAR_TITLE = "arg_common_toolbar_title";
    private TextView toolbarTitle;


    public CommonToolbarFragment() {
    }

    public static CommonToolbarFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_COMMON_TOOLBAR_TITLE, title);

        CommonToolbarFragment fragment = new CommonToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbarTitle = view.findViewById(R.id.common_toolbar_fragment__title);

        toolbarTitle.setText(getArguments().getString(ARG_COMMON_TOOLBAR_TITLE));

    }

}
