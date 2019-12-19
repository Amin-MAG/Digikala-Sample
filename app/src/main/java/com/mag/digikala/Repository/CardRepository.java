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
        if (!increaseProductInCard(product)) {
            CardProduct object = realm.createObject(CardProduct.class, product.getId() + "_");
            object.setCount(1);
            object.setProductId(product.getId());
        }
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
        realm.commitTransaction();
    }

    public boolean increaseProductInCard(Product product) {
        if (!realm.isInTransaction())
            realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                cp.setCount(cp.getCount() + 1);
                numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
                realm.commitTransaction();
                return true;
            }
        }
        return false;
    }


    public void decreaseProductInCard(Product product) {
        realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                if (cp.getCount() - 1 == 0) {
                    cp.deleteFromRealm();
                } else {
                    cp.setCount(cp.getCount() - 1);
                }
                numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
            }
        }
        realm.commitTransaction();
    }

    public void clearProductFromCard(Product product) {
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                cp.deleteFromRealm();
                break;
            }
        }
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }


}
