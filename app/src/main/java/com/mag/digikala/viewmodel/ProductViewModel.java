package com.mag.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductImage;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.Var.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<Product> product = new MutableLiveData<>();
    private MutableLiveData<List<String>> imageUrls = new MutableLiveData<>();


    public ProductViewModel(@NonNull Application application) {
        super(application);
    }


    public void requestToSetProductById(String productId) {
        RetrofitApi retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        retrofitApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {

                    product.setValue(response.body());

                    ArrayList<String> urls = new ArrayList<>();
                    for (ProductImage image : product.getValue().getImages())
                        urls.add(image.getSrc());
                    imageUrls.postValue(urls);

                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    public String getId() {
        return product.getValue().getId();
    }

    public String getTitle() {
        return product.getValue().getName();
    }

    public String getFirstImageSrc() {
        if (product.getValue().getImages().length != 0)
            return product.getValue().getImages()[0].getSrc();
        return null;
    }

    public String getRegularPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return !product.getValue().isOnSale() ? product.getValue().getRegularPrice() + MONEY_STRING : product.getValue().getSalePrice() + MONEY_STRING;
    }

    public String getSalesPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return product.getValue().isOnSale() ? product.getValue().getRegularPrice() + MONEY_STRING : "";
    }

    public String getShortDescription() {
        return Jsoup.parse(product.getValue().getShortDescription()).body().text();
    }


    public String getDescription() {
        Element pTag;
        if ((pTag = Jsoup.parse(product.getValue().getDescription()).body().select("p").first()) != null) {
            return pTag.text();
        }
        return "";
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }

    public MutableLiveData<List<String>> getImagesSrc() {
        return imageUrls;
    }


}
