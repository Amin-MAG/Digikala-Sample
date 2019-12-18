package com.mag.digikala.Repository;

import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Model.CardProduct;
import com.mag.digikala.Model.Product;

import io.realm.Realm;

public class CardRepository {

    public static CardRepository instance;

    private Realm realm;

    public static CardRepository getInstance() {
        if (instance == null)
            instance = new CardRepository();
        return instance;
    }

    private CardRepository() {
        realm = Realm.getDefaultInstance();
    }

    // Toolbar Repository

    private MutableLiveData<Integer> numberOfCardProducts = new MutableLiveData<>();

    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

    public void loadData() {
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }

    public void addToCard(Product product) {

        boolean productExistance = false;
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                cp.setCount(cp.getCount() + 1);
                productExistance = true;
                break;
            }
        }

        if (!productExistance) {
            CardProduct object = realm.createObject(CardProduct.class, product.getId() + "_");
            object.setCount(1);
            object.setProductId(product.getId());
        }

        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
//        getActivity().startActivity(CardActivity.newIntent(getContext()));

    }

}
