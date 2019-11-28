package com.mag.digikala.Controller.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.Model.Adapter.CategoryRecyclerAdapter;
import com.mag.digikala.Model.Adapter.MainSliderAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.DigikalaCategory;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainDigikalaFragment extends Fragment {

    private SliderView sliderView;
    private MainSliderAdapter mainSliderAdapter;

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
        sliderView = view.findViewById(R.id.digikala_main_activity__main_image_slider);


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
        mainSliderAdapter = new MainSliderAdapter(new ArrayList<String>() {{
            add(getURLForResource(R.drawable.main_slider_image01));
            add(getURLForResource(R.drawable.main_slider_image02));
            add(getURLForResource(R.drawable.main_slider_image03));
            add(getURLForResource(R.drawable.main_slider_image04));
        }});

        // Set Adapters

        bestProductRecycler.setAdapter(bestProductAdapter);
        newestProductRecycler.setAdapter(newestProductAdapter);
        mostViewedProductRecycler.setAdapter(mostViewedProductAdapter);
        categories.setAdapter(categoryAdapter);

        sliderView.setSliderAdapter(mainSliderAdapter);
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

    }

    public void updateView() {

        bestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        bestProductAdapter.notifyDataSetChanged();
        mostViewedProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        mostViewedProductAdapter.notifyDataSetChanged();
        newestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
        newestProductAdapter.notifyDataSetChanged();

    }

    public String getURLForResource(int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }

}
