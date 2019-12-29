package com.mag.digikala.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
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

public class ProductViewModel extends BaseObservable {

    private Product product;
    private MutableLiveData<List<String>> imageUrls = new MutableLiveData<>();

    public ProductViewModel() {
    }

    public ProductViewModel(String productId) {
        RetrofitApi retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
        retrofitApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {

                    setProduct(response.body());

                    ArrayList<String> urls = new ArrayList<>();
                    for (ProductImage image : product.getImages())
                        urls.add(image.getSrc());
                    imageUrls.postValue(urls);

                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

    @Bindable
    public String getTitle() {
        return product.getName();
    }

    @Bindable
    public String getFirstImageSrc() {
        if (product.getImages().length != 0)
            return product.getImages()[0].getSrc();
        return null;
    }

    @Bindable
    public String getRegularPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return !product.isOnSale() ? product.getRegularPrice() + MONEY_STRING : product.getSalePrice() + MONEY_STRING;
    }

    @Bindable
    public String getSalerPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return product.isOnSale() ? product.getRegularPrice() + MONEY_STRING : "";
    }

    @Bindable
    public String getShortDescription() {
        return Jsoup.parse(product.getShortDescription()).body().text();
    }


    @Bindable
    public String getDescription() {
        Element pTag;
        if ((pTag = Jsoup.parse(product.getDescription()).body().select("p").first()) != null) {
            return pTag.text();
        }
        return "";
    }

    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }

    public MutableLiveData<List<String>> getImagesSrc() {
        return imageUrls;
    }


}
