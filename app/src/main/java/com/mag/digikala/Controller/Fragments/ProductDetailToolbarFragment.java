package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mag.digikala.R;


public class ProductDetailToolbarFragment extends Fragment {


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
