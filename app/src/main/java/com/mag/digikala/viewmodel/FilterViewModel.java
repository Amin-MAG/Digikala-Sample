package com.mag.digikala.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Repository.FilterRepository;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterViewModel extends BaseObservable {

    // Should be change
    private String sortModeString = "بدون ترتیب";
    private RetrofitApi retrofitApi;
    private MutableLiveData<List<Product>> filteredProducts;
    private MutableLiveData<FilterRepository.Attribute> filterAttribute;
    private String searchingText, categoryId, sortText;
    private SORT_MODE sortMode;


    public enum SORT_MODE {
        SORT_BY_VIEW(0), SORT_BY_SELL(1), SORT_BY_PRICE_ASCENDING(2), SORT_BY_PRICE_DESCENDING(3), SORT_BY_NEWEST(4), NO_SORT(5);

        private int code;

        SORT_MODE(int code) {
            this.code = code;
        }

        public int getNumVal() {
            return code;
        }

    }


    public FilterViewModel(String searchingText, String categoryId) {

        this.searchingText = searchingText;
        this.categoryId = categoryId;
        this.retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        this.filteredProducts = new MutableLiveData<>();
        this.filterAttribute = new MutableLiveData<>();
        this.sortMode = SORT_MODE.NO_SORT;


    }

    public void filter() {

        String orderBaseOn = null;
        String order = "asc";

        switch (sortMode) {
            case SORT_BY_VIEW:
                orderBaseOn = "popularity";
                sortText = "پربازدیدترین ها";
                break;
            case SORT_BY_SELL:
                orderBaseOn = "rating";
                sortText = "پرفروشترین ها";
                break;
            case SORT_BY_PRICE_ASCENDING:
                orderBaseOn = "price";
                sortText = "قیمت از کم به زیاد";
                break;
            case SORT_BY_PRICE_DESCENDING:
                orderBaseOn = "price";
                order = "desc";
                sortText = "قیمت از کم به زیاد";
                break;
            case SORT_BY_NEWEST:
                orderBaseOn = "date";
                sortText = "جدیدترین ها";
                break;
            default:
                sortText = "بدون ترتیب";
                break;
        }


        String attribute = "";
        String terms = "";

        if (filterAttribute.getValue() != null) {
            attribute = filterAttribute.getValue().getName();
            terms = filterAttribute.getValue().getSelectedTermString();
        }


        if (categoryId != null) {

            if (orderBaseOn == null) {
                retrofitApi.getProducts(searchingText, attribute, terms, 100, 1).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if (response.isSuccessful()) {

                            filteredProducts.postValue(response.body());

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }

                });
            } else {
                retrofitApi.getProducts(searchingText, attribute, terms, 100, 1, orderBaseOn, order).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if (response.isSuccessful()) {

                            filteredProducts.postValue(response.body());

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }

                });
            }

        } else {

            if (orderBaseOn == null) {

                retrofitApi.getProducts(categoryId, searchingText, attribute, terms, 100, 1).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if (response.isSuccessful()) {

                            filteredProducts.postValue(response.body());

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }

                });
            } else {
                retrofitApi.getProducts(categoryId, searchingText, attribute, terms, 100, 1, orderBaseOn, order).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if (response.isSuccessful()) {

                            filteredProducts.postValue(response.body());

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }

                });
            }

        }


    }

    @Bindable
    public String getSortModeString() {
        return sortModeString;
    }

    @Bindable
    public String getSortText() {
        return sortText;
    }

    public void setSortMode(SORT_MODE sortMode) {
        this.sortMode = sortMode;
        filter();
    }

    @Bindable
    public MutableLiveData<List<Product>> getFilteredProducts() {
        return filteredProducts;
    }

    public MutableLiveData<FilterRepository.Attribute> getFilterAttribute() {
        return filterAttribute;
    }

}