package com.mag.digikala.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Model.CardProduct;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.ViewModel.CardViewModel;

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
//    private MutableLiveData<Double> sumOfCardProducts = new MutableLiveData<>();

    public MutableLiveData<Integer> getNumberOfCardProducts() {
        return numberOfCardProducts;
    }

//    public MutableLiveData<Double> getSumOfCardProducts() {
//        return sumOfCardProducts;
//    }

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
                        responseProduct.setCardCount(cardProduct.getCount());
                        if (!productList.getValue().contains(responseProduct)) {
                            List<Product> newProduct = productList.getValue();
                            newProduct.add(responseProduct);
                            productList.postValue(newProduct);
                        }
//                        addToCard(responseProduct);
//                        sumOfCardProducts.postValue(calculateSumOfCardProducts());
                        Log.d("debug_for_products", "Retorit Reuqest: " + responseProduct.getName());
                        Log.d("debug_for_products", "Realm Data: " + realm.where(CardProduct.class).findAll());


                    }

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }
    }

    public void loadInitialData() {
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
//        sumOfCardProducts.postValue(calculateSumOfCardProducts());
    }


//    private double calculateSumOfCardProducts() {
//
//        double sum = 0;
//        if (productList.getValue() != null)
//            for (Product p : productList.getValue())
//                sum += p.getCardCount() * ((p.isOnSale() ? Double.parseDouble(p.getSalePrice()) : Double.parseDouble(p.getRegularPrice())));
//        return sum;
//
//    }


    // Actions //

    public void addToCard(Product product) {
        if (!increaseProductInCard(product)) {
            CardProduct object = realm.createObject(CardProduct.class, product.getId() + "_");
            object.setCount(1);
            object.setProductId(product.getId());
//            productList.getValue().add(product);
//            productList.postValue(productList.getValue());
        }
//        sumOfCardProducts.postValue(calculateSumOfCardProducts());
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }

    public boolean increaseProductInCard(Product product) {
        if (!realm.isInTransaction())
            realm.beginTransaction();
        for (CardProduct cp : realm.where(CardProduct.class).findAll()) {
            if (cp.getProductId().equals(product.getId())) {
                cp.setCount(cp.getCount() + 1);
//                sumOfCardProducts.postValue(calculateSumOfCardProducts());
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
                    cp.deleteFromRealm();
                } else {
                    cp.setCount(cp.getCount() - 1);
                }
//                productList.getValue().remove(product);
//                productList.postValue(productList.getValue());
//                sumOfCardProducts.postValue(calculateSumOfCardProducts());
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
//        sumOfCardProducts.postValue(calculateSumOfCardProducts());
        numberOfCardProducts.postValue(realm.where(CardProduct.class).findAll().size());
    }


}
