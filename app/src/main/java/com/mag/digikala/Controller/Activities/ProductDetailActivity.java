package com.mag.digikala.Controller.Activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ProductDetailActivity extends SingleFragmentActivityDrawer {

    public static final String EXTRA_MERCHANDISE = "extra_merchandise";
    public static final String FRAGMENT_PRODUCT_DETAIL = "fragment_product_detail";

    public static Intent newIntent(Context context, String merchandiseId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_MERCHANDISE, merchandiseId);
        return intent;
    }

    @Override
    public Fragment getFragment() {
        String mechandiceId = getIntent().getExtras().getString(EXTRA_MERCHANDISE);
        return ProductDetailFragment.newInstance(mechandiceId);
    }

    @Override
    public String getTagName() {
        return FRAGMENT_PRODUCT_DETAIL;
    }

}
