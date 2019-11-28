package com.mag.digikala.Controller.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MERCHANDISE = "extra_merchandise";
    private Merchandise merchandise;

    private TextView product_name;
    private ViewPager slider;
    private SliderViewPagerAdapter sliderAdapter;

    public static Intent newIntent(Context context, String merchandiseId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_MERCHANDISE, merchandiseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ArrayList<String> urls = new ArrayList<>();
        for (Merchandise merchandise : DigikalaRepository.getInstance().getAllProducts()) {
            urls.add(merchandise.getImages()[0].getSrc());
        }

        merchandise = DigikalaRepository.getInstance().getProductById(getIntent().getExtras().getString(EXTRA_MERCHANDISE));

        product_name = findViewById(R.id.product_detail_activity__product_name);
        product_name.setText(getString(R.string.product_name) + " " + merchandise.getName());
        slider = findViewById(R.id.product_detail_activity__view_pager);
        sliderAdapter = new SliderViewPagerAdapter(getSupportFragmentManager(), urls);
        slider.setAdapter(sliderAdapter);

    }

}
