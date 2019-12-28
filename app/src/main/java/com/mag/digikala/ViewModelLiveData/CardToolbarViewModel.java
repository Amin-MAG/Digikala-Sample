package com.mag.digikala.ViewModelLiveData;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Repository.CardRepository;

public class CardToolbarViewModel extends AndroidViewModel {


    private CardRepository repository;
    private MutableLiveData<Integer> numberOfCardProducts;

    public CardToolbarViewModel(@NonNull Application application) {
        super(application);

        repository = CardRepository.getInstance();
        numberOfCardProducts = repository.getNumberOfCardProducts();

    }

    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

    public void loadData() {
        repository.loadInitialData();
    }

}
