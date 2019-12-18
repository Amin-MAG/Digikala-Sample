package com.mag.digikala.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Repository.CardRepository;

public class MainToolbarViewModel extends AndroidViewModel {

    private CardRepository repository;
    private MutableLiveData<Integer> numberOfCardProducts;

    public MainToolbarViewModel(@NonNull Application application) {
        super(application);

        repository = CardRepository.getInstance();
        numberOfCardProducts = repository.getNumberOfCardProducts();

    }

    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

    public void loadData() {
        repository.loadData();
    }

}
