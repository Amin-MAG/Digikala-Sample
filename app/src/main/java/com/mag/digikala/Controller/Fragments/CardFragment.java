package com.mag.digikala.Controller.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mag.digikala.Model.Adapter.CardListRecyclerAdapter;
import com.mag.digikala.Model.CardProduct;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardFragment extends Fragment {

    private RetrofitApi retrofitApi;
    private Realm realm;
    private List<Product> productList = new ArrayList<>();

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

        realm = Realm.getDefaultInstance();
        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        for (CardProduct cardProduct : realm.where(CardProduct.class).findAll()) {
            retrofitApi.getProductById(cardProduct.getProductId()).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {

                    if (response.isSuccessful()) {

                        Product responseProduct = response.body();
                        responseProduct.setCardCount(cardProduct.getCount());
                        productList.add(responseProduct);
                        setupAdapter();
                        calculateSumOfCardProducts();

                    }

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents(view);

        recyclerAdapter = new CardListRecyclerAdapter(productList);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void findComponents(@NonNull View view) {
        recyclerView = view.findViewById(R.id.card_fragment__recycler);
        sumOfCardProductText = view.findViewById(R.id.card_fragment__sum);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();

    }

    private void setupAdapter() {

        recyclerAdapter.setProducts(productList);
        recyclerAdapter.notifyDataSetChanged();

    }

    private void calculateSumOfCardProducts() {

        double sum = 0;
        for (Product p : productList)
            sum += p.getCardCount() * ((p.isOnSale() ? Double.parseDouble(p.getSalePrice()) : Double.parseDouble(p.getRegularPrice())));
        sumOfCardProductText.setText(String.valueOf(sum));

    }

}
