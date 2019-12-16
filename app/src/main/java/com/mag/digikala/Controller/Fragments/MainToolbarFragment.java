package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.MainActivity;
import com.mag.digikala.Controller.Activities.SearchActivity;
import com.mag.digikala.R;

public class MainToolbarFragment extends Fragment {

    private MaterialButton searchBtn, cartBtn, menuBtn;

    public static MainToolbarFragment newInstance() {

        Bundle args = new Bundle();

        MainToolbarFragment fragment = new MainToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainToolbarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBtn = view.findViewById(R.id.toolbar_fragment__search_btn);
        cartBtn = view.findViewById(R.id.toolbar_fragment__cart_btn);
        menuBtn = view.findViewById(R.id.toolbar_fragment__menu_btn);


        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity)
                    ((MainActivity) getActivity()).openNavigationView();
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.newIntent(getContext()));
            }
        });

    }

}
