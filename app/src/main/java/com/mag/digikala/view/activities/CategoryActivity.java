package com.mag.digikala.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mag.digikala.view.fragments.CategoryViewPagerFragment;
import com.mag.digikala.view.fragments.CommonToolbarFragment;
import com.mag.digikala.R;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.viewmodel.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

    public static final String FRAGMENT_CATEGORY_VIEW_PAGER = "fragment_category_view_pager";
    public static final String FRAGMENT_CATEGORY_COMMON_TOOLBAR = "fragment_category_common_toolbar";

    private CategoryViewModel viewModel;

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

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);


        if (commonToolbarFragment == null) {
            commonToolbarFragment = CommonToolbarFragment.newInstance(getResources().getString(R.string.category), () -> {
                finish();
            });
            UiUtil.changeFragment(getSupportFragmentManager(), commonToolbarFragment, R.id.digikala_category__toolbar_frame, true, FRAGMENT_CATEGORY_COMMON_TOOLBAR);
        }

        if (categoryViewPagerFragment == null) {
            categoryViewPagerFragment = CategoryViewPagerFragment.newInstance(getIntent().getExtras().getString(EXTRA_CATEGORY_ID));
            UiUtil.changeFragment(getSupportFragmentManager(), categoryViewPagerFragment, R.id.digikala_category__viewpager, true, FRAGMENT_CATEGORY_VIEW_PAGER);
        }

    }

}
