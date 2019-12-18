package com.mag.digikala.View;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.MainActivity;
import com.mag.digikala.Controller.Activities.SearchActivity;
import com.mag.digikala.R;
import com.mag.digikala.ViewModel.MainToolbarViewModel;
import com.mag.digikala.ViewModel.ProductDetailToolbarViewModel;

public class MainToolbarFragment extends Fragment {

    private MainToolbarViewModel viewModel;

    private MaterialButton searchBtn, cartBtn, menuBtn;
    private TextView cardNumber;


    public static MainToolbarFragment newInstance() {

        Bundle args = new Bundle();

        MainToolbarFragment fragment = new MainToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainToolbarFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MainToolbarViewModel.class);
        viewModel.loadData();
        viewModel.getNumberOfCardProducts().observe(this, numberOfCardProducts -> {
            cardNumber.setText(numberOfCardProducts.toString());
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents(view);

        setEvents();

    }

    private void setEvents() {
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

    private void findComponents(@NonNull View view) {
        searchBtn = view.findViewById(R.id.toolbar_fragment__search_btn);
        cartBtn = view.findViewById(R.id.toolbar_fragment__cart_btn);
        menuBtn = view.findViewById(R.id.toolbar_fragment__menu_btn);
        cardNumber = view.findViewById(R.id.toolbar_fragment__card_number);
    }

}
