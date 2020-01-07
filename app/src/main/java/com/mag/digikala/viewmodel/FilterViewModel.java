package com.mag.digikala.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.data.model.Product;
import com.mag.digikala.network.RetrofitApi;
import com.mag.digikala.network.RetrofitInstance;
import com.mag.digikala.data.repository.FilterRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterViewModel extends AndroidViewModel {

    // Should be change
    private RetrofitApi retrofitApi;
    private MutableLiveData<List<Product>> filteredProducts;
    private MutableLiveData<FilterRepository.Attribute> filterAttribute;
    private ObservableField<String> sortText;
    public String searchingText, categoryId;
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


    public FilterViewModel(@NonNull Application application) {
        super(application);

//        this.searchingText = searchingText;
//        this.categoryId = categoryId;
        this.retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        this.filteredProducts = new MutableLiveData<>();
        this.filterAttribute = new MutableLiveData<>();
        this.sortText = new ObservableField<>();

        this.sortMode = SORT_MODE.NO_SORT;
        this.sortText.set("بدون ترتیب");

    }


    public void filter() {

        String orderBaseOn = null;
        String order = "asc";

        switch (sortMode) {
            case SORT_BY_VIEW:
                orderBaseOn = "popularity";
                sortText.set("پربازدیدترین ها");
                break;
            case SORT_BY_SELL:
                orderBaseOn = "rating";
                sortText.set("پرفروشترین ها");
                break;
            case SORT_BY_PRICE_ASCENDING:
                orderBaseOn = "price";
                sortText.set("قیمت از کم به زیاد");
                break;
            case SORT_BY_PRICE_DESCENDING:
                orderBaseOn = "price";
                order = "desc";
                sortText.set("قیمت از کم به زیاد");
                break;
            case SORT_BY_NEWEST:
                orderBaseOn = "date";
                sortText.set("جدیدترین ها");
                break;
            default:
                sortText.set("بدون ترتیب");
                break;
        }


        String attribute = "";
        String terms = "";

        if (filterAttribute.getValue() != null && filterAttribute.getValue().getSelectedTerms().size() > 0) {
            attribute = filterAttribute.getValue().getSlug();
            terms = filterAttribute.getValue().getSelectedTermString();
        }

        Log.d("filter: ", "filter: " + attribute + " " + terms);

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

    public ObservableField<String> getSortText() {
        return sortText;
    }

    public void setSortMode(SORT_MODE sortMode) {
        this.sortMode = sortMode;
        filter();
    }

    public MutableLiveData<List<Product>> getFilteredProducts() {
        return filteredProducts;
    }

    public MutableLiveData<FilterRepository.Attribute> getFilterAttribute() {
        return filterAttribute;
    }

    public void setSearchingText(String searchingText) {
        this.searchingText = searchingText;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}