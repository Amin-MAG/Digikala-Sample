package com.mag.digikala.Controller.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.mag.digikala.Controller.Fragments.MainFragment;
import com.mag.digikala.Controller.Fragments.MainToolbarFragment;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ScrollView mainFrame;
    private FrameLayout toolbarFrame;
    private FrameLayout loadingFrame;
    private LinearLayout noInternetConnectionFrame;

    private MainToolbarFragment mainToolbarFragment;
    private MainFragment mainFragment;

    private DrawerLayout drawerLayout;

    private RecyclerView navigationRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    private MaterialButton retryConnectionBtn;

    private NavigationView navigationView;

    private RetrofitApi retrofitApi;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("LifeCycle", "onCreate: ");


        ProductsRepository.getInstance().setNavigationItems(this);

        // Find Items

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
//        navigationRecycler = findViewById(R.id.digikala__navigation_recycler);
        mainFrame = findViewById(R.id.digikala_activity__scroll_view);
        toolbarFrame = findViewById(R.id.digikala_activity__toolbar_frame);
        loadingFrame = findViewById(R.id.digikala_activity__loading_frame);
        noInternetConnectionFrame = findViewById(R.id.digikala_activity__no_internet_frame);
        retryConnectionBtn = findViewById(R.id.digikala_activity__retry_connection);
        navigationView = findViewById(R.id.digikala__navigation_view);


        retrofitConncetion();

        // Toolbar

        if (mainToolbarFragment == null) {
            mainToolbarFragment = MainToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), mainToolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_main_toolbar");
        }


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        View view = getLayoutInflater().inflate(R.layout.fragment_main_toolbar, null);
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        actionBar.setCustomView(R.layout.fragment_main_toolbar);


        // Navigation

//        navigationRecyclerAdapter = new NavigationRecyclerAdapter(ProductsRepository.getInstance().getNavigationItems());
//        navigationRecycler.setAdapter(navigationRecyclerAdapter);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                return true;
//            }
//        });

        // Main Page

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), mainFragment, R.id.digikala_activity__scroll_view, true, "fragment_main_digikala");
        }


    }

    @SuppressLint("CheckResult")
    private void retrofitConncetion() {

        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        requestToGetProducts();

    }

    private void requestToGetCategories() {
        retrofitApi.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if (response.isSuccessful()) {

                    List<Category> responseList = response.body();
                    Map<String, CategoryGroup> categoryGroups = new HashMap<>();

                    /*

                    Analyzing Categories

                     */


                    Category thisLoopCategory;
                    // Get Root Categories //
                    for (int i = 0; i < responseList.size(); i++)
                        if ((thisLoopCategory = responseList.get(i)).getParentId().equals("0"))
                            categoryGroups.put(thisLoopCategory.getId(), new CategoryGroup(thisLoopCategory.getId(), thisLoopCategory.getName()));


                    CategoryGroup thisLoopCategoryGroup;
                    // Get Sub Categories And Set Them //
                    /*
                    Letter SubCategory Fro SybCategory Should Be handled Here
                     */
                    for (int i = 0; i < responseList.size(); i++) {
                        if (!(thisLoopCategory = responseList.get(i)).getParentId().equals("0")) {
                            Log.e("AAADDD", thisLoopCategory.getParentId() + "");
                            if ((thisLoopCategoryGroup = categoryGroups.get(thisLoopCategory.getParentId())) != null)
                                thisLoopCategoryGroup.addCategory(thisLoopCategory);
                        }
                    }

                    ProductsRepository.getInstance().setCategoryMap(categoryGroups);

                    dropLoadingSlide();

                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                loadConncetionErrorSlide();
            }

        });
    }

    private void requestToGetProducts() {
        retrofitApi.getAllProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    ProductsRepository.getInstance().setAllProducts(response.body());
                    requestToGetCategories();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadConncetionErrorSlide();
            }

        });
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
        retryConnectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitConncetion();
                loadingFrame.setVisibility(View.VISIBLE);
                noInternetConnectionFrame.setVisibility(View.GONE);

            }
        });

    }

}
