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

//    private RecyclerView navigationRecycler;
//    private NavigationRecyclerAdapter navigationRecyclerAdapter;
//    private TextView validPrice, invaidPrice;

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

//        navigationRecycler = findViewById(R.id.digikala__navigation_recycler);

        UiUtil.changeFragment(getSupportFragmentManager(), ProductDetailToolbarFragment.newInstance(), R.id.digikala_product_detail_activity__toolbar_frame, false, FRAGMENT_PRODUCT_DETAIL_TOOLBAR);
        UiUtil.changeFragment(getSupportFragmentManager(), ProductDetailFragment.newInstance(getIntent().getExtras().getString(EXTRA_MERCHANDISE)), R.id.digikala_product_detail_activity__main_frame, false, FRAGMENT_PRODUCT_DETAIL);

        // Navigation

//        navigationRecyclerAdapter = new NavigationRecyclerAdapter(ProductsRepository.getInstance().getNavigationItems());
//        navigationRecycler.setAdapter(navigationRecyclerAdapter);


    }
//
//    private void setPrices(String regular_price, String sale_price) {
//
//        String MONEY_STRING = Constants.SPACE_CHAR + activity.getResources().getString(R.string.tomans);
//        String priceString;
//        String priceInvalidString = Constants.EMPTY_CHAR;
//
//        if (sale_price.equals(Constants.EMPTY_CHAR))
//            priceString = regular_price + MONEY_STRING;
//        else {
//            priceString = sale_price + MONEY_STRING;
//            priceInvalidString = regular_price + MONEY_STRING;
//        }
//
//        priceInvalid.setText(priceInvalidString);
//        priceInvalid.setPaintFlags(priceInvalid.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        price.setText(priceString);
//
//    }

}
