package com.mag.digikala.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.data.model.CardProduct;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.network.RetrofitApi;
import com.mag.digikala.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardRepository {

    public static CardRepository instance;

    private RetrofitApi retrofitApi;
    private Realm realm;

    public static CardRepository getInstance() {
        if (instance == null)
            instance = new CardRepository();
        return instance;
    }

    private CardRepository() {
        realm = Realm.getDefaultInstance();
        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
    }

    // Toolbar Repository

    private MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private MutableLiveData<Integer> numberOfCardProducts = new MutableLiveData<>();
    private MutableLiveData<Double> sumOfCardProducts = new MutableLiveData<>();

    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

    public MutableLiveData<Double> getSumOfCardProducts() {
        return sumOfCardProducts;
    }

    public MutableLiveData<List<Product>> getProductList() {
        return productList;
    }

    public void loadInitialProduct() {
        productList.postValue(new ArrayList<>());
        for (CardProduct cardProduct : realm.where(CardProduct.class).findAll()) {
            retrofitApi.getProductById(cardProduct.getProductId()).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {

                    if (response.isSuccessful()) {

                        Product responseProduct = response.body();
                        responseProduct.getCardCount().setValue(cardProduct.getCount());
                        List<Product> newProduct = productList.getValue();
                        newProduct.add(responseProduct);
                        productList.postValue(newProduct);
                        sumOfCardProducts.postValue(calculateSumOfCardProducts());

                    }

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }
        sumOfCardProducts.postValue(calculateSumOfCardProducts());
    }

    public void loadInitialData() {
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }


    private double calculateSumOfCardProducts() {

        double sum = 0;
        if (productList.getValue() != null)
            for (Product p : productList.getValue())
                sum += p.getCardCount().getValue() * ((p.isOnSale() ? Double.parseDouble(p.getSalePrice()) : Double.parseDouble(p.getRegularPrice())));
        return sum;

    }


    // Actions //

    public void addToCard(Product product) {
        if (!increaseProductInCard(product)) {
            // Realm
            CardProduct object = realm.createObject(CardProduct.class, product.getId() + "_");
            object.setCount(1);
            object.setProductId(product.getId());
        }
        if (realm.isInTransaction())
            realm.commitTransaction();
        sumOfCardProducts.postValue(calculateSumOfCardProducts());
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }

    public boolean increaseProductInCard(Product product) {
        if (!realm.isInTransaction())
            realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                cp.setCount(cp.getCount() + 1);
                // Change
                List<Product> newProduct = productList.getValue();
                for (Product p : newProduct)
                    if (p.getId().equals(product.getId()))
                        if (product.getCardCount().getValue() != null)
                            product.getCardCount().setValue(product.getCardCount().getValue() + 1);
                        else
                            product.getCardCount().setValue(1);
                productList.postValue(newProduct);
                sumOfCardProducts.postValue(calculateSumOfCardProducts());
                numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
                realm.commitTransaction();
                return true;
            }
        }
        return false;
    }

    public void decreaseProductInCard(Product product) {
        if (!realm.isInTransaction())
            realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                if (cp.getCount() - 1 == 0) {
                    // Realm
                    cp.deleteFromRealm();
                    // Post List
                    List<Product> newProduct = productList.getValue();
                    newProduct.remove(product);
                    productList.postValue(newProduct);
                } else {
                    cp.setCount(cp.getCount() - 1);
                    // Change
                    List<Product> newProduct = productList.getValue();
                    for (Product p : newProduct)
                        if (p.getId().equals(product.getId()))
                            product.getCardCount().setValue(product.getCardCount().getValue() - 1);
                    productList.postValue(newProduct);
                }
                sumOfCardProducts.postValue(calculateSumOfCardProducts());
                numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
            }
        }
        realm.commitTransaction();
    }

    public void clearProductFromCard(Product product) {
        realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                // Realm
                cp.deleteFromRealm();
                // Change
                List<Product> newProduct = productList.getValue();
                for (int i = 0; i < newProduct.size(); i++)
                    if (newProduct.get(i).getId().equals(product.getId())) {
                        newProduct.remove(i);
                        break;
                    }
                productList.postValue(newProduct);
                break;
            }
        }
        sumOfCardProducts.postValue(calculateSumOfCardProducts());
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
        realm.commitTransaction();
    }


}
