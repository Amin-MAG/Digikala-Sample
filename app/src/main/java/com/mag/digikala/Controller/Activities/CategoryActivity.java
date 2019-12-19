package com.mag.digikala.Controller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mag.digikala.Controller.Fragments.CategoryViewPagerFragment;
import com.mag.digikala.Controller.Fragments.CommonToolbarFragment;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    public static final String FRAGMENT_CATEGORY_VIEW_PAGER = "fragment_category_view_pager";
    public static final String FRAGMENT_CATEGORY_COMMON_TOOLBAR = "fragment_category_common_toolbar";
    private CommonToolbarFragment commonToolbarFragment;
    private CategoryViewPagerFragment categoryViewPagerFragment;


    public static final String EXTRA_CATEGORY_ID = "extra_category_id";

    public static Intent newIntent(Context context, String categoryId) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        if (commonToolbarFragment == null) {
            commonToolbarFragment = CommonToolbarFragment.newInstance(getResources().getString(R.string.category), () -> {
            });
            UiUtil.changeFragment(getSupportFragmentManager(), commonToolbarFragment, R.id.digikala_category__toolbar_frame, true, FRAGMENT_CATEGORY_COMMON_TOOLBAR);
        }

        if (categoryViewPagerFragment == null) {
            categoryViewPagerFragment = CategoryViewPagerFragment.newInstance(getIntent().getExtras().getString(EXTRA_CATEGORY_ID));
            UiUtil.changeFragment(getSupportFragmentManager(), categoryViewPagerFragment, R.id.digikala_category__viewpager, true, FRAGMENT_CATEGORY_VIEW_PAGER);
        }

    }

}
