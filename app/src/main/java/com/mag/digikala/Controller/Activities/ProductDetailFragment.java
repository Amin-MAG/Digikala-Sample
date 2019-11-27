package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_MERCHANDICE = "arg_merchandice";
    private Merchandise merchandise;

    private TextView product_name;
    private ViewPager slider;
    private SliderViewPagerAdapter sliderAdapter;

    public static ProductDetailFragment newInstance(String merchandiceId) {

        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MERCHANDICE, merchandiceId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<String> urls = new ArrayList<>();
        for (Merchandise merchandise : DigikalaRepository.getInstance().getAllProducts()) {
            urls.add(merchandise.getImages()[0].getSrc());
        }

        merchandise = DigikalaRepository.getInstance().getProductById(getArguments().getString(ARG_MERCHANDICE));

        product_name = view.findViewById(R.id.product_detail_fragment__product_name);
        product_name.setText(getString(R.string.product_name) + merchandise.getName());
        slider = view.findViewById(R.id.product_detail_fragment__view_pager);
        sliderAdapter = new SliderViewPagerAdapter(getFragmentManager(), urls);
        slider.setAdapter(sliderAdapter);

    }


}
