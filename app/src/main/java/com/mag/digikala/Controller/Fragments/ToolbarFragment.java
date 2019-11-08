package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.DigikalaActivity;
import com.mag.digikala.R;

public class ToolbarFragment extends Fragment {

    private MaterialButton searchBtn, cartBtn, menuBtn;

    public static ToolbarFragment newInstance() {

        Bundle args = new Bundle();

        ToolbarFragment fragment = new ToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ToolbarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar, container, false);
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
                if (getActivity() instanceof DigikalaActivity)
                    ((DigikalaActivity) getActivity()).openNavigationView();
            }
        });

    }

}
