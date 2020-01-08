package com.mag.digikala.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.mag.digikala.R;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.view.fragments.ProductDetailFragment;
import com.mag.digikala.view.fragments.ToolbarFragments.ProductDetailToolbarFragment;
import com.mag.digikala.viewmodel.ProductViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MERCHANDISE = "extra_merchandise";
    public static final String FRAGMENT_PRODUCT_DETAIL_TOOLBAR = "fragment_product_detail_toolbar";
    public static final String FRAGMENT_PRODUCT_DETAIL = "fragment_product_detail";

    private ProductViewModel viewModel;

    private ProductDetailFragment productDetailFragment;
    private ProductDetailToolbarFragment productDetailToolbarFragment;


    public static Intent newIntent(Context context, String merchandiseId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_MERCHANDISE, merchandiseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        viewModel.requestToSetProductById(getIntent().getExtras().getString(EXTRA_MERCHANDISE));

        setupFragments();

    }

    private void setupFragments() {

        if (productDetailToolbarFragment == null) {
            productDetailToolbarFragment = ProductDetailToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), productDetailToolbarFragment, R.id.digikala_product_detail_activity__toolbar_frame, false, FRAGMENT_PRODUCT_DETAIL_TOOLBAR);
        }

        if (productDetailFragment == null) {
            productDetailFragment = ProductDetailFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), productDetailFragment, R.id.digikala_product_detail_activity__main_frame, false, FRAGMENT_PRODUCT_DETAIL);
        }

    }

}
