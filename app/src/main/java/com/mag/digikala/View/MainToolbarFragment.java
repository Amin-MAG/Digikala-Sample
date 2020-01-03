package com.mag.digikala.View;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.CardActivity;
import com.mag.digikala.Controller.Activities.MainActivity;
import com.mag.digikala.Controller.Activities.SearchActivity;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.viewmodel.toolbar.MainToolbarViewModel;

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
            if (numberOfCardProducts == 0) {
                cardNumber.setBackgroundColor(getResources().getColor(R.color.nothing));
                cardNumber.setText(Constants.EMPTY_CHAR);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    cardNumber.setBackground(getResources().getDrawable(R.drawable.cart_counter));
                }
                cardNumber.setText(String.valueOf(numberOfCardProducts));
            }
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

        menuBtn.setOnClickListener(view -> {
            if (getActivity() instanceof MainActivity)
                ((MainActivity) getActivity()).openNavigationView();
        });

        cartBtn.setOnClickListener(view -> {
            startActivity(CardActivity.newIntent(getContext()));
        });

        searchBtn.setOnClickListener(view -> startActivity(SearchActivity.newIntent(getContext())));
    }

    private void findComponents(@NonNull View view) {
        searchBtn = view.findViewById(R.id.toolbar_fragment__search_btn);
        cartBtn = view.findViewById(R.id.toolbar_fragment__cart_btn);
        menuBtn = view.findViewById(R.id.toolbar_fragment__menu_btn);
        cardNumber = view.findViewById(R.id.toolbar_fragment__card_number);
    }

}
