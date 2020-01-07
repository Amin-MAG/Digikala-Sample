package com.mag.digikala.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mag.digikala.view.fragments.CommonToolbarFragment;
import com.mag.digikala.view.fragments.FilterFragment;
import com.mag.digikala.view.fragments.FilterSelectionFragment;
import com.mag.digikala.view.fragments.ToolbarFragments.FilterToolbarFragment;
import com.mag.digikala.R;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.viewmodel.FilterSelectionViewModel;
import com.mag.digikala.viewmodel.FilterViewModel;

public class FilterActivity extends AppCompatActivity implements FilterFragment.FilterSelectionCallBack {

    public static final String EXTRA_SEARCH_STRING = "extra_search_string";
    public static final String EXTRA_CATEGORY_ID = "extra_category_id";

    private FilterToolbarFragment filterToolbarFragment;
    private FilterFragment filterFragment;
    private CommonToolbarFragment filterSelectionFragmentCommonToolbar;
    private FilterSelectionFragment filterSelectionFragment;

    private FilterSelectionViewModel filterSelectionViewModel;
    private FilterViewModel filterViewModel;

    public static Intent newIntent(Context context, String searchString, String categoryId) {
        Intent intent = new Intent(context, FilterActivity.class);
        intent.putExtra(EXTRA_SEARCH_STRING, searchString);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterSelectionViewModel = ViewModelProviders.of(this).get(FilterSelectionViewModel.class);
        filterViewModel = ViewModelProviders.of(this).get(FilterViewModel.class);
        filterViewModel.setSearchingText(getIntent().getExtras().getString(EXTRA_SEARCH_STRING));
        filterViewModel.setCategoryId(getIntent().getExtras().getString(EXTRA_CATEGORY_ID));

        showFilterPage();

    }

    public void showFilterPage() {

        if (filterToolbarFragment == null)
            filterToolbarFragment = FilterToolbarFragment.newInstance();
        UiUtil.changeFragment(getSupportFragmentManager(), filterToolbarFragment, R.id.filter_activity__toolbar_frame, true, EXTRA_SEARCH_STRING);


        if (filterFragment == null)
            filterFragment = FilterFragment.newInstance();
        UiUtil.changeFragment(getSupportFragmentManager(), filterFragment, R.id.filter_activity__main_frame, true, EXTRA_SEARCH_STRING);

    }

    @Override
    public void showFitlerSelectionPage() {

        if (filterSelectionFragmentCommonToolbar == null)
            filterSelectionFragmentCommonToolbar = CommonToolbarFragment.newInstance(getResources().getString(R.string.fiter_product), this::showFilterPage);
        UiUtil.changeFragment(getSupportFragmentManager(), filterSelectionFragmentCommonToolbar, R.id.filter_activity__toolbar_frame, true, EXTRA_SEARCH_STRING);


        if (filterSelectionFragment == null)
            filterSelectionFragment = FilterSelectionFragment.newInstance(() -> {
                showFilterPage();
//                filterFragment.filter();
            });
        UiUtil.changeFragment(getSupportFragmentManager(), filterSelectionFragment, R.id.filter_activity__main_frame, true, EXTRA_SEARCH_STRING);

    }

}
