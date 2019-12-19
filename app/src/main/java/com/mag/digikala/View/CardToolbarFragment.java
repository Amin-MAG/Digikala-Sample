package com.mag.digikala.View;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.ViewModel.CardToolbarViewModel;
import com.mag.digikala.ViewModel.FilteToolbarViewModel;


public class CardToolbarFragment extends Fragment {

    private CardToolbarViewModel viewModel;

    private TextView cardNumber, toolbarText;

    public static CardToolbarFragment newInstance() {

        Bundle args = new Bundle();

        CardToolbarFragment fragment = new CardToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    
    public CardToolbarFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(CardToolbarViewModel.class);
        viewModel.loadData();
        viewModel.getNumberOfCardProducts().observe(this, numberOfCardProducts -> {
            if (numberOfCardProducts == 0) {
                cardNumber.setBackgroundColor(getResources().getColor(R.color.nothing));
                cardNumber.setText(Constants.EMPTY_CHAR);
            } else {
                cardNumber.setText(String.valueOf(numberOfCardProducts));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents(view);

        toolbarText.setText(getActivity().getResources().getString(R.string.cart));

    }

    private void findComponents(@NonNull View view) {
        cardNumber = view.findViewById(R.id.card_toolbar_fragment__card_number);
        toolbarText = view.findViewById(R.id.card_toolbar_fragment__title);
    }


}
