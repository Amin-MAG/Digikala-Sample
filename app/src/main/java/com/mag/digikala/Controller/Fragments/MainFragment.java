package com.mag.digikala.Controller.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Model.Adapter.CategoryGreenButtonsRecyclerAdapter;
import com.mag.digikala.Model.Adapter.MainSliderAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentMainBinding;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainSliderAdapter mainSliderAdapter;
    private CategoryGreenButtonsRecyclerAdapter categoryAdapter;
    private ProductRecyclerAdapter newestProductAdapter;
    private ProductRecyclerAdapter bestSellerProductAdapter;
    private ProductRecyclerAdapter mostViewedProductAdapter;
    private ProductRecyclerAdapter offeredProductAdapter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adapterInitilozation();

        adapterSetting();

        mainSliderInitilazation();

    }

    private void mainSliderInitilazation() {
        SliderView sliderView = binding.digikalaMainActivityMainImageSlider;
        sliderView.setSliderAdapter(mainSliderAdapter);
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void adapterSetting() {
        binding.digikalaMainActivityBest.setAdapter(bestSellerProductAdapter);
        binding.digikalaMainActivityNewest.setAdapter(newestProductAdapter);
        binding.digikalaMainActivityMostView.setAdapter(mostViewedProductAdapter);
        binding.digikalaMainActivityCategoty.setAdapter(categoryAdapter);
        binding.digikalaMainActivityOffered.setAdapter(offeredProductAdapter);
    }

    private void adapterInitilozation() {
        bestSellerProductAdapter = new ProductRecyclerAdapter();
        mostViewedProductAdapter = new ProductRecyclerAdapter();
        newestProductAdapter = new ProductRecyclerAdapter();
        categoryAdapter = new CategoryGreenButtonsRecyclerAdapter();
        offeredProductAdapter = new ProductRecyclerAdapter(new ArrayList<Product>());
        mainSliderAdapter = new MainSliderAdapter(new ArrayList<String>() {{
            add(getURLForResource(R.drawable.main_slider_image01));
            add(getURLForResource(R.drawable.main_slider_image02));
            add(getURLForResource(R.drawable.main_slider_image03));
            add(getURLForResource(R.drawable.main_slider_image04));
        }});
    }

    public void
    updateView() {

        bestSellerProductAdapter.setProductItems(ProductsRepository.getInstance().getTopRatingProducts());
        bestSellerProductAdapter.notifyDataSetChanged();
        mostViewedProductAdapter.setProductItems(ProductsRepository.getInstance().getPopularProducts());
        mostViewedProductAdapter.notifyDataSetChanged();
        newestProductAdapter.setProductItems(ProductsRepository.getInstance().getAllProducts());
        newestProductAdapter.notifyDataSetChanged();
        offeredProductAdapter.setProductItems(ProductsRepository.getInstance().getOfferedProducts());
        offeredProductAdapter.notifyDataSetChanged();
        categoryAdapter.setCategoriesItems(ProductsRepository.getInstance().getParentCategory());
        categoryAdapter.notifyDataSetChanged();

    }

    public String getURLForResource(int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }

}
