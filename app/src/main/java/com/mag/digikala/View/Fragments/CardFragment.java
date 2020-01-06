package com.mag.digikala.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mag.digikala.Model.Adapter.CardListRecyclerAdapter;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.mag.digikala.viewmodel.CardViewModel;

import java.util.ArrayList;
import java.util.List;


public class CardFragment extends Fragment {

    private CardViewModel viewModel;

    private RecyclerView recyclerView;
    private CardListRecyclerAdapter recyclerAdapter;
    private TextView sumOfCardProductText;

    public static CardFragment newInstance() {

        Bundle args = new Bundle();

        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CardFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        viewModel.loadData();
        viewModel.getSumOfCardProducts().observe(this, sum -> {
            sumOfCardProductText.setText(String.valueOf(sum));
        });
        viewModel.getProducts().observe(this, productList -> {
            setupAdapter(productList);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents(view);

        recyclerAdapter = new CardListRecyclerAdapter(new ArrayList<>());
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void findComponents(@NonNull View view) {
        recyclerView = view.findViewById(R.id.card_fragment__recycler);
        sumOfCardProductText = view.findViewById(R.id.card_fragment__sum);
    }

    private void setupAdapter(List<Product> products) {

        recyclerAdapter.setProducts(products);
        recyclerAdapter.notifyDataSetChanged();

    }



}
