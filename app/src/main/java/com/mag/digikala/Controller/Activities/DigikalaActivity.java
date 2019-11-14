package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Fragments.ToolbarFragment;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.DigikalaRepository;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.Network.DigikalaApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DigikalaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ToolbarFragment toolbarFragment;
    private ProgressBar progressBar;

    private RecyclerView navigationRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    private RecyclerView newestProductRecycler;
    private ProductRecyclerAdapter newestProductAdapter;

    private RecyclerView bestProductRecycler;
    private ProductRecyclerAdapter bestProductAdapter;

    private RecyclerView mostViewedProductRecycler;
    private ProductRecyclerAdapter mostViewedProductAdapter;

    private DigikalaApi digikalaApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digikala);


        retrofitConncetion();


        // Find Items

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        navigationRecycler = findViewById(R.id.digikala_activity__navigation_recycler);
        bestProductRecycler = findViewById(R.id.digikala_activity__best);
        newestProductRecycler = findViewById(R.id.digikala_activity__newest);
        mostViewedProductRecycler = findViewById(R.id.digikala_activity__most_view);
        progressBar = findViewById(R.id.digikala_activity__progress_bar);

        // Progress Bar


        // Toolbar

        toolbarFragment = ToolbarFragment.newInstance();
        UiUtil.changeFragment(getSupportFragmentManager(), toolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_toolbar");


        // Adapters

        navigationRecyclerAdapter = new NavigationRecyclerAdapter(new ArrayList<String>() {{
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
        }});
        bestProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());
        mostViewedProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());
        newestProductAdapter = new ProductRecyclerAdapter(new ArrayList<Merchandise>());


        // Set Adapters

        navigationRecycler.setAdapter(navigationRecyclerAdapter);
        bestProductRecycler.setAdapter(bestProductAdapter);
        newestProductRecycler.setAdapter(newestProductAdapter);
        mostViewedProductRecycler.setAdapter(mostViewedProductAdapter);


    }

    private void retrofitConncetion() {

        digikalaApi = RetrofitInstance.getInstance().create(DigikalaApi.class);
        digikalaApi.getProducts().enqueue(new Callback<List<Merchandise>>() {
            @Override
            public void onResponse(Call<List<Merchandise>> call, Response<List<Merchandise>> response) {

                if (response.isSuccessful()) {

                    DigikalaRepository.getInstance().setAllProducts(response.body());
                    progressBar.setVisibility(View.GONE);
                    bestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
                    bestProductAdapter.notifyDataSetChanged();
                    mostViewedProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
                    mostViewedProductAdapter.notifyDataSetChanged();
                    newestProductAdapter.setProductItems(DigikalaRepository.getInstance().getAllProducts());
                    newestProductAdapter.notifyDataSetChanged();

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
