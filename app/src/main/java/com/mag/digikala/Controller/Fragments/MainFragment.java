package com.mag.digikala.Controller.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.Model.Adapter.CategoryGreenButtonsRecyclerAdapter;
import com.mag.digikala.Model.Adapter.MainSliderAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("LifeCycle", "onAttach: ");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("LifeCycle", "onActivityCreated: ");


    }

    private SliderView sliderView;
    private MainSliderAdapter mainSliderAdapter;

    private RecyclerView categories;
    private CategoryGreenButtonsRecyclerAdapter categoryAdapter;

    private RecyclerView newestProductRecycler;
    private ProductRecyclerAdapter newestProductAdapter;

    private RecyclerView bestSellerProductRecycler;
    private ProductRecyclerAdapter bestSellerProductAdapter;

    private RecyclerView mostViewedProductRecycler;
    private ProductRecyclerAdapter mostViewedProductAdapter;

    private RecyclerView offeredProductRecycler;
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
        Log.i("LifeCycle", "onCreateView: ");

        return inflater.inflate(R.layout.fragment_main_digikala, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("LifeCycle", "onViewCreated: ");


        // Find Items

        bestSellerProductRecycler = view.findViewById(R.id.digikala_main_activity__best);
        newestProductRecycler = view.findViewById(R.id.digikala_main_activity__newest);
        mostViewedProductRecycler = view.findViewById(R.id.digikala_main_activity__most_view);
        categories = view.findViewById(R.id.digikala_main_activity__categoty);
        sliderView = view.findViewById(R.id.digikala_main_activity__main_image_slider);
        offeredProductRecycler = view.findViewById(R.id.digikala_main_activity__offered);


        // Adapters

        bestSellerProductAdapter = new ProductRecyclerAdapter(new ArrayList<Product>());
        mostViewedProductAdapter = new ProductRecyclerAdapter(new ArrayList<Product>());
        newestProductAdapter = new ProductRecyclerAdapter(new ArrayList<Product>());
        categoryAdapter = new CategoryGreenButtonsRecyclerAdapter(new ArrayList<CategoryGroup>() {
        });

//        List<String> list = new ArrayList<>();
//        for (Product product : ProductsRepository.getInstance().getOfferedProducts())
//            list.add(product.getImages()[0].getSrc());
        mainSliderAdapter = new MainSliderAdapter(new ArrayList<>());

//        mainSliderAdapter = new MainSliderAdapter(new ArrayList<String>() {{
//            add(getURLForResource(R.drawable.main_slider_image01));
//            add(getURLForResource(R.drawable.main_slider_image02));
//            add(getURLForResource(R.drawable.main_slider_image03));
//            add(getURLForResource(R.drawable.main_slider_image04));
//        }});

        offeredProductAdapter = new ProductRecyclerAdapter(new ArrayList<Product>());

        // Set Adapters

        bestSellerProductRecycler.setAdapter(bestSellerProductAdapter);
        newestProductRecycler.setAdapter(newestProductAdapter);
        mostViewedProductRecycler.setAdapter(mostViewedProductAdapter);
        categories.setAdapter(categoryAdapter);
        offeredProductRecycler.setAdapter(offeredProductAdapter);

        sliderView.setSliderAdapter(mainSliderAdapter);
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

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


        List<String> list = new ArrayList<>();
        for (Product product : ProductsRepository.getInstance().getOfferedProducts())
            list.add(product.getImages()[0].getSrc());
        mainSliderAdapter.setImages(list);
        mainSliderAdapter.notifyDataSetChanged();



    }

    public String getURLForResource(int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }

}
