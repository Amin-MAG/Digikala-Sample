package com.mag.digikala.view.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.R;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.view.fragments.MainFragment;
import com.mag.digikala.view.fragments.ToolbarFragments.MainToolbarFragment;
import com.mag.digikala.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.digikala_activity__drawer_layout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.digikala_activity__retry_connection)
    protected MaterialButton retryConnectionBtn;

    // Frames

    @BindView(R.id.digikala_activity__scroll_view)
    protected ScrollView mainFrame;

    @BindView(R.id.digikala_activity__toolbar_frame)
    protected FrameLayout toolbarFrame;

    @BindView(R.id.digikala_activity__loading_frame)
    protected FrameLayout loadingFrame;

    @BindView(R.id.digikala_activity__no_internet_frame)
    protected LinearLayout noInternetConnectionFrame;


    private MainViewModel viewModel;

    private MainToolbarFragment mainToolbarFragment;
    private MainFragment mainFragment;


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setBackIsPressed(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.requestToGetInitialMainDatas();
        viewModel.getHasError().observe(this, aBoolean -> {
            if (aBoolean) loadConncetionErrorSlide();
        });
        viewModel.getIsLoading().observe(this, aBoolean -> {
            if (!aBoolean) dropLoadingSlide();
        });

        setupFragments();

    }

    private void setupFragments() {

        // Toolbar
        if (mainToolbarFragment == null) {
            mainToolbarFragment = MainToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), mainToolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_main_toolbar");
        }

        // Main Page
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), mainFragment, R.id.digikala_activity__scroll_view, true, "fragment_main");
        }

    }

    @Override
    public void finish() {
        if (viewModel.onBackPressed()) finish();
    }


    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    public void dropLoadingSlide() {

        loadingFrame.setVisibility(View.GONE);
        toolbarFrame.setVisibility(View.VISIBLE);
        mainFrame.setVisibility(View.VISIBLE);
        mainFragment.updateView();

    }

    public void loadConncetionErrorSlide() {

        loadingFrame.setVisibility(View.GONE);
        noInternetConnectionFrame.setVisibility(View.VISIBLE);
        retryConnectionBtn.setOnClickListener(view -> {

            viewModel.requestToGetInitialMainDatas();
            loadingFrame.setVisibility(View.VISIBLE);
            noInternetConnectionFrame.setVisibility(View.GONE);

        });

    }

}
