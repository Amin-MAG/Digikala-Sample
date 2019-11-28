package com.mag.digikala.Controller.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mag.digikala.Controller.Fragments.ProductDetailFragment;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.Controller.Fragments.ProductDetailToolbarFragment;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MERCHANDISE = "extra_merchandise";
    public static final String FRAGMENT_PRODUCT_DETAIL_TOOLBAR = "fragment_product_detail_toolbar";
    public static final String FRAGMENT_PRODUCT_DETAIL = "fragment_product_detail";

    private RecyclerView navigationRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    public static Intent newIntent(Context context, String merchandiseId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_MERCHANDISE, merchandiseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        navigationRecycler = findViewById(R.id.digikala__navigation_recycler);

        UiUtil.changeFragment(getSupportFragmentManager(), ProductDetailToolbarFragment.newInstance(),R.id.digikala_product_detail_activity__toolbar_frame, false, FRAGMENT_PRODUCT_DETAIL_TOOLBAR);
        UiUtil.changeFragment(getSupportFragmentManager(), ProductDetailFragment.newInstance(getIntent().getExtras().getString(EXTRA_MERCHANDISE)),R.id.digikala_product_detail_activity__main_frame,false, FRAGMENT_PRODUCT_DETAIL);

        // Navigation

        navigationRecyclerAdapter = new NavigationRecyclerAdapter(DigikalaRepository.getInstance().getNavigationItems());
        navigationRecycler.setAdapter(navigationRecyclerAdapter);


    }

}
