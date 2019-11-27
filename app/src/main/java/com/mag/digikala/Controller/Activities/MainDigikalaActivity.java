package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDigikalaActivity extends AppCompatActivity {

    private ToolbarFragment toolbarFragment;
    private MainDigikalaFragment mainDigikalaFragment;

    private DrawerLayout drawerLayout;
    private ProgressBar progressBar;

    private RecyclerView navigationRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    private DigikalaApi digikalaApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_digikala);

        retrofitConncetion();

        // Find Items

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        navigationRecycler = findViewById(R.id.digikala_activity__navigation_recycler);
        progressBar = findViewById(R.id.digikala_activity__progress_bar);


        // Toolbar

        toolbarFragment = ToolbarFragment.newInstance();
        UiUtil.changeFragment(getSupportFragmentManager(), toolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_main_toolbar");


        // Navigation

        navigationRecyclerAdapter = new NavigationRecyclerAdapter(new ArrayList<DigikalaMenuItem>() {{
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
        navigationRecycler.setAdapter(navigationRecyclerAdapter);


        // Main Page

        mainDigikalaFragment = MainDigikalaFragment.newInstance();
        UiUtil.changeFragment(getSupportFragmentManager(), mainDigikalaFragment, R.id.digikala_activity__scroll_view, false, "fragment_main_digikala");

    }

    private void retrofitConncetion() {

        digikalaApi = RetrofitInstance.getInstance().create(DigikalaApi.class);
        digikalaApi.getProducts().enqueue(new Callback<List<Merchandise>>() {
            @Override
            public void onResponse(Call<List<Merchandise>> call, Response<List<Merchandise>> response) {

                if (response.isSuccessful()) {

                    DigikalaRepository.getInstance().setAllProducts(response.body());
                    progressBar.setVisibility(View.GONE);
                    mainDigikalaFragment.updateView();

                }

            }

            @Override
            public void onFailure(Call<List<Merchandise>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Internet Connection !", Toast.LENGTH_LONG).show();
            }

        });

    }

    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
