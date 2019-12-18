package com.mag.digikala.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Repository.ToolbarRepository;

public class ProductDetailToolbarViewModel extends AndroidViewModel {

    private ToolbarRepository repository;
    private MutableLiveData<Integer> numberOfCardProducts;

    public ProductDetailToolbarViewModel(@NonNull Application application) {
        super(application);

        repository = ToolbarRepository.getInstance();
        numberOfCardProducts = repository.getNumberOfCardProducts();

    }


    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

    public void loadData() {
        repository.loadData();
    }

}
