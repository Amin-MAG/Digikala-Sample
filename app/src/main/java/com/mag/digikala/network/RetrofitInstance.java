package com.mag.digikala.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";

    public static final String USER_NAME = "ghasvari313@gmail.com";
    public static final String PASSWORD = "pvwh1qUkOX)HXrsX#4m7*Dw4";

    public static final  String CONSUMER_KEY = "ck_dda03e6eb717396dd14d511a94137c15e3c7365d";
    public static final String CONSUMER_SECRET = "cs_065abe66a9a354981648904bb7bcdc247cb5209a";

    public static final String WOOCOMMERCE_REST_AUTHENTICATION_KEY = "?consumer_key=" + RetrofitInstance.CONSUMER_KEY + "&consumer_secret=" + RetrofitInstance.CONSUMER_SECRET ;


    // Singleton

    private static Retrofit retrofitInstance;

    public static Retrofit getInstance() {

        if (retrofitInstance == null) {

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }


        return retrofitInstance;
    }

}