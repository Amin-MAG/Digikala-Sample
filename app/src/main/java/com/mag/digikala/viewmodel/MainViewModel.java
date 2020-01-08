package com.mag.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.data.model.Category;
import com.mag.digikala.data.model.CategoryGroup;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.data.repository.CardRepository;
import com.mag.digikala.data.repository.FilterRepository;
import com.mag.digikala.data.repository.ProductsRepository;
import com.mag.digikala.network.RetrofitApi;
import com.mag.digikala.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private boolean backIsPressed = false;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> hasError = new MutableLiveData<>();

    private CardRepository cardRepository;
    private ProductsRepository productsRepository;
    private FilterRepository filterRepository;

    private RetrofitApi retrofitApi;


    public MainViewModel(@NonNull Application application) {
        super(application);
        cardRepository = CardRepository.getInstance();
        productsRepository = ProductsRepository.getInstance();
        filterRepository = FilterRepository.getInstance();
    }

    public void requestToGetInitialMainDatas() {

        this.retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        this.isLoading.setValue(true);
        this.hasError.setValue(false);

        requestToGetProducts();
        requestToGetInitialAttributeData();

        cardRepository.loadInitialProduct();

    }


    private void requestToGetProducts() {
        retrofitApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    productsRepository.setAllProducts(response.body());
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
                    productsRepository.setOfferedProducts(response.body());
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
                    productsRepository.setTopRatingProducts(response.body());
                    requestToGetPopularProducts();
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadConncetionErrorSlide();
            }
        });
    }


    private void requestToGetPopularProducts() {
        retrofitApi.getProducts(8, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productsRepository.setPopularProducts(response.body());
                requestToGetCategories();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadConncetionErrorSlide();
            }
        });
    }

    private void requestToGetCategories() {

        retrofitApi.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if (response.isSuccessful()) {

                    List<Category> responseList = response.body();
                    Map<String, CategoryGroup> categoryGroups = new HashMap<>();

                    // Analyzing Categories
                    Category thisLoopCategory;
                    // Get Root Categories //
                    for (int i = 0; i < responseList.size(); i++) {
                        if ((thisLoopCategory = responseList.get(i)).getParentId().equals("0")) {
                            categoryGroups.put(thisLoopCategory.getId(), new CategoryGroup(thisLoopCategory.getId(), thisLoopCategory.getName()));
                        }
                    }


                    CategoryGroup thisLoopCategoryGroup;
                    // Get Sub Categories And Set Them //
                    // Letter SubCategory For SubCategory Should Be handled Here
                    for (int i = 0; i < responseList.size(); i++) {
                        if (!(thisLoopCategory = responseList.get(i)).getParentId().equals("0")) {
                            if ((thisLoopCategoryGroup = categoryGroups.get(thisLoopCategory.getParentId())) != null)
                                thisLoopCategoryGroup.addCategory(thisLoopCategory);
                        }
                    }

                    productsRepository.setCategoryMap(categoryGroups);

                    dropLoadingSlide();

                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                loadConncetionErrorSlide();
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
                                    filterRepository.setAttributes(defaultAttributes);
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


    private void loadConncetionErrorSlide() {
        this.hasError.setValue(true);
    }

    private void dropLoadingSlide() {
        this.isLoading.setValue(false);
    }

    public boolean onBackPressed() {
        if (backIsPressed) return true;
        else backIsPressed = true;
        return false;
    }

    public void setBackIsPressed(boolean backIsPressed) {
        this.backIsPressed = backIsPressed;
    }

    public MutableLiveData<Boolean> getHasError() {
        return hasError;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }


}
