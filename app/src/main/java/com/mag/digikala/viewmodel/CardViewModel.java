package com.mag.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.data.model.Product;
import com.mag.digikala.data.repository.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository repository;

    private MutableLiveData<Double> sumOfCardProducts;
    private MutableLiveData<List<Product>> products;

    public CardViewModel(@NonNull Application application) {
        super(application);

        repository = CardRepository.getInstance();
        sumOfCardProducts = repository.getSumOfCardProducts();
        products = repository.getProductList();

    }


    public MutableLiveData<Double> getSumOfCardProducts() {
        return sumOfCardProducts;
    }

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public void loadData() {
        repository.loadInitialProduct();
    }

}
