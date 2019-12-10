package com.mag.digikala.Network;

import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.mag.digikala.Network.RetrofitInstance.BASE_URL;
import static com.mag.digikala.Network.RetrofitInstance.WOOCOMMERCE_REST_AUTHENTICATION_KEY;

public interface RetrofitApi {

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getAllProducts();

    @GET("products" + "/categories" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Category>> getCategories();

}
