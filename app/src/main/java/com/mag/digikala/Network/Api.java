package com.mag.digikala.Network;

import com.mag.digikala.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("products")
    Call<List<Product>> getProducts();

}
