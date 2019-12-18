package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Model.CardProduct;
import com.mag.digikala.R;

import io.realm.Realm;


public class ProductDetailToolbarFragment extends Fragment {

    private TextView cardNumber;
    private Realm realm;

    public static ProductDetailToolbarFragment newInstance() {

        Bundle args = new Bundle();

        ProductDetailToolbarFragment fragment = new ProductDetailToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailToolbarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardNumber = view.findViewById(R.id.product_detail_toolbar_fragment__card_number);
        cardNumber.setText(String.valueOf(realm.where(CardProduct.class).findAll().size()));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();

    }

}
