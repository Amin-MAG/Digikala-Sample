package com.mag.digikala.Network;

import com.mag.digikala.Model.Merchandise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DigikalaApi {

    @GET("products")
    Call<List<Merchandise>> getProducts();

}
