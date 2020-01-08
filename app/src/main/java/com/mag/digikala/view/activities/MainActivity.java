package com.mag.digikala.view.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.mag.digikala.R;
import com.mag.digikala.data.model.Category;
import com.mag.digikala.data.model.CategoryGroup;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.data.repository.CardRepository;
import com.mag.digikala.data.repository.FilterRepository;
import com.mag.digikala.data.repository.ProductsRepository;
import com.mag.digikala.network.RetrofitApi;
import com.mag.digikala.network.RetrofitInstance;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.view.adapters.nomvvm.NavigationRecyclerAdapter;
import com.mag.digikala.view.fragments.MainFragment;
import com.mag.digikala.view.fragments.ToolbarFragments.MainToolbarFragment;
import com.mag.digikala.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;


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

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setBackIsPressed(false);;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // Find Items

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        mainFrame = findViewById(R.id.digikala_activity__scroll_view);
        toolbarFrame = findViewById(R.id.digikala_activity__toolbar_frame);
        loadingFrame = findViewById(R.id.digikala_activity__loading_frame);
        noInternetConnectionFrame = findViewById(R.id.digikala_activity__no_internet_frame);
        retryConnectionBtn = findViewById(R.id.digikala_activity__retry_connection);
        navigationView = findViewById(R.id.digikala__navigation_view);


        retrofitConncetion();

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

    @SuppressLint("CheckResult")
    private void retrofitConncetion() {

        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        requestToGetProducts();
        requestToGetInitialAttributeData();
        CardRepository.getInstance().loadInitialProduct();

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

                    for (int k = 0; k < responseList.size(); k++) {
                        Log.d("CATEGORIES", "onResponse: " + responseList.get(k).getName());
                    }

                    Category thisLoopCategory;
                    // Get Root Categories //
                    for (int i = 0; i < responseList.size(); i++) {
                        if ((thisLoopCategory = responseList.get(i)).getParentId().equals("0")) {
                            categoryGroups.put(thisLoopCategory.getId(), new CategoryGroup(thisLoopCategory.getId(), thisLoopCategory.getName()));
                        }
                    }


                    CategoryGroup thisLoopCategoryGroup;
                    // Get Sub Categories And Set Them //
                    /*
                    Letter SubCategory For SubCategory Should Be handled Here
                     */
                    for (int i = 0; i < responseList.size(); i++) {
                        if (!(thisLoopCategory = responseList.get(i)).getParentId().equals("0")) {
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
        retrofitApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    ProductsRepository.getInstance().setAllProducts(response.body());
                    requestToGetOfferedProducts();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadConncetionErrorSlide();

            }

        });
    }

    private void requestToGetOfferedProducts() {
        retrofitApi.getSaleProduct(8, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    ProductsRepository.getInstance().setOfferedProducts(response.body());
                    requestToGetTopRatingProducts();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadConncetionErrorSlide();
            }

        });
    }

    private void requestToGetTopRatingProducts() {
        retrofitApi.getProducts(8, 1, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    ProductsRepository.getInstance().setTopRatingProducts(response.body());
                    requestToGetPopularProducts();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void requestToGetPopularProducts() {
        retrofitApi.getProducts(8, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                ProductsRepository.getInstance().setPopularProducts(response.body());
                requestToGetCategories();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void requestToGetInitialAttributeData() {
        retrofitApi.getAttributes().enqueue(new Callback<List<FilterRepository.Attribute>>() {
            @Override
            public void onResponse(Call<List<FilterRepository.Attribute>> call, Response<List<FilterRepository.Attribute>> response) {

                if (response.isSuccessful()) {

                    List<FilterRepository.Attribute> defaultAttributes = new ArrayList<>();

                    for (FilterRepository.Attribute attribute : response.body()) {
                        FilterRepository.Attribute newAttribute = attribute;
                        retrofitApi.getTerms(newAttribute.getId()).enqueue(new Callback<List<FilterRepository.Term>>() {
                            @Override
                            public void onResponse(Call<List<FilterRepository.Term>> call, Response<List<FilterRepository.Term>> response) {

                                if (response.isSuccessful()) {

                                    newAttribute.setTerms(response.body());
                                    defaultAttributes.add(newAttribute);
                                    FilterRepository.getInstance().setAttributes(defaultAttributes);

                                }

                            }

                            @Override
                            public void onFailure(Call<List<FilterRepository.Term>> call, Throwable t) {

                            }
                        });

                    }

                }

            }

            @Override
            public void onFailure(Call<List<FilterRepository.Attribute>> call, Throwable t) {

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
        retryConnectionBtn.setOnClickListener(view -> {

            retrofitConncetion();
            loadingFrame.setVisibility(View.VISIBLE);
            noInternetConnectionFrame.setVisibility(View.GONE);

        });

    }

}
