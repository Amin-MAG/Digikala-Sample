package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Fragments.MainDigikalaFragment;
import com.mag.digikala.Controller.Fragments.ToolbarFragment;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.Model.DigikalaMenuItem;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.Network.DigikalaApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;
import com.mag.digikala.Var.Constants;
import com.pollux.widget.DualProgressView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDigikalaActivity extends AppCompatActivity {

    private ScrollView mainFrame;
    private FrameLayout toolbarFrame;
    private FrameLayout loadingFrame;
    private LinearLayout noInternetConnectionFrame;

    private ToolbarFragment toolbarFragment;
    private MainDigikalaFragment mainDigikalaFragment;

    private DrawerLayout drawerLayout;

    private RecyclerView navigationRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    private MaterialButton retryConnectionBtn;

    private DigikalaApi digikalaApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_digikala);


        DigikalaRepository.getInstance().setNavigationItems(new ArrayList<DigikalaMenuItem>() {{
            add(new DigikalaMenuItem(getString(R.string.home_page), R.drawable.ic_home));
            add(new DigikalaMenuItem(getString(R.string.category), R.drawable.ic_categoty));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED));
            add(new DigikalaMenuItem(getString(R.string.cart), R.drawable.ic_shopping_cart_dark));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED));
            add(new DigikalaMenuItem(getString(R.string.offers), R.drawable.ic_star));
            add(new DigikalaMenuItem(getString(R.string.best_sellers), R.drawable.ic_star));
            add(new DigikalaMenuItem(getString(R.string.most_views), R.drawable.ic_star));
            add(new DigikalaMenuItem(getString(R.string.newests), R.drawable.ic_star));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED));
            add(new DigikalaMenuItem(getString(R.string.setting), R.drawable.ic_setting));
            add(new DigikalaMenuItem(getString(R.string.faq), R.drawable.ic_help));
            add(new DigikalaMenuItem(getString(R.string.about_us), R.drawable.ic_phone));
        }});

        retrofitConncetion();

        // Find Items

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        navigationRecycler = findViewById(R.id.digikala__navigation_recycler);
        mainFrame = findViewById(R.id.digikala_activity__scroll_view);
        toolbarFrame = findViewById(R.id.digikala_activity__toolbar_frame);
        loadingFrame = findViewById(R.id.digikala_activity__loading_frame);
        noInternetConnectionFrame = findViewById(R.id.digikala_activity__no_internet_frame);
        retryConnectionBtn = findViewById(R.id.digikala_activity__retry_connection);

        // Toolbar

        if (toolbarFragment == null) {
            toolbarFragment = ToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), toolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_main_toolbar");
        }

        // Navigation

        navigationRecyclerAdapter = new NavigationRecyclerAdapter(DigikalaRepository.getInstance().getNavigationItems());
        navigationRecycler.setAdapter(navigationRecyclerAdapter);


        // Main Page

        if (mainDigikalaFragment == null) {
            mainDigikalaFragment = MainDigikalaFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), mainDigikalaFragment, R.id.digikala_activity__scroll_view, true, "fragment_main_digikala");
        }

    }

    private void retrofitConncetion() {

        digikalaApi = RetrofitInstance.getInstance().create(DigikalaApi.class);
        digikalaApi.getProducts().enqueue(new Callback<List<Merchandise>>() {
            @Override
            public void onResponse(Call<List<Merchandise>> call, Response<List<Merchandise>> response) {

                if (response.isSuccessful()) {

                    DigikalaRepository.getInstance().setAllProducts(response.body());
                    loadingFrame.setVisibility(View.GONE);
                    toolbarFrame.setVisibility(View.VISIBLE);
                    mainFrame.setVisibility(View.VISIBLE);

                    mainDigikalaFragment.updateView();

                }

            }

            @Override
            public void onFailure(Call<List<Merchandise>> call, Throwable t) {

                loadingFrame.setVisibility(View.GONE);
                noInternetConnectionFrame.setVisibility(View.VISIBLE);
                retryConnectionBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        retrofitConncetion();
                        loadingFrame.setVisibility(View.VISIBLE);
                        noInternetConnectionFrame.setVisibility(View.GONE   );

                    }
                });

            }

        });

    }

    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}