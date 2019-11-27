package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.Model.Adapter.CategoryRecyclerAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.DigikalaCategory;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;

import java.util.ArrayList;
import java.util.List;

public class MainDigikalaFragment extends Fragment {

    private RecyclerView categories;
    private CategoryRecyclerAdapter categoryAdapter;

    private RecyclerView newestProductRecycler;
    private ProductRecyclerAdapter newestProductAdapter;

    private RecyclerView bestProductRecycler;
    private ProductRecyclerAdapter bestProductAdapter;

    private RecyclerView mostViewedProductRecycler;
    private ProductRecyclerAdapter mostViewedProductAdapter;

    public MainDigikalaFragment() {
    }

    public static MainDigikalaFragment newInstance() {

        Bundle args = new Bundle();

        MainDigikalaFragment fragment = new MainDigikalaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_digikala, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Find Items

        bestProductRecycler = view.findViewById(R.id.digikala_main_activity__best);
        newestProductRecycler = view.findViewById(R.id.digikala_main_activity__newest);
        mostViewedProductRecycler = view.findViewById(R.id.digikala_main_activity__most_view);
        categories = view.findViewById(R.id.digikala_main_activity__categoty);


        // Adapters

        bestProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());
        mostViewedProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());
        newestProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());
        categoryAdapter = new CategoryRecyclerAdapter(new ArrayList<DigikalaCategory>() {{
            add(new DigikalaCategory("غذا"));
            add(new DigikalaCategory("پوشاک"));
            add(new DigikalaCategory("دیجیتال"));
            add(new DigikalaCategory("موبایل"));
            add(new DigikalaCategory("غذا"));
            add(new DigikalaCategory("پوشاک"));
            add(new DigikalaCategory("دیجیتال"));
            add(new DigikalaCategory("موبایل"));
            add(new DigikalaCategory("غذا"));
            add(new DigikalaCategory("پوشاک"));
            add(new DigikalaCategory("دیجیتال"));
            add(new DigikalaCategory("موبایل"));
        }});

        // Set Adapters

        bestProductRecycler.setAdapter(bestProductAdapter);
        newestProductRecycler.setAdapter(newestProductAdapter);
        mostViewedProductRecycler.setAdapter(mostViewedProductAdapter);
        categories.setAdapter(categoryAdapter);

    }

    public void updateView() {

        bestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        bestProductAdapter.notifyDataSetChanged();
        mostViewedProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        mostViewedProductAdapter.notifyDataSetChanged();
        newestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        newestProductAdapter.notifyDataSetChanged();

    }

}
