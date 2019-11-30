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
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.Network.Api;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DigikalaRepository.getInstance().setNavigationItems(this);

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

        api = RetrofitInstance.getInstance().create(Api.class);
        api.getProducts().enqueue(new Callback<List<Merchandise>>() {
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
                        noInternetConnectionFrame.setVisibility(View.GONE);

                    }
                });

            }

        });

    }

    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
