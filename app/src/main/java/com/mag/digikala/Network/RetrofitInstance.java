package com.mag.digikala.Network;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class RetrofitInstance {
//
//    private static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
//
//    private static Retrofit instance;
//
//    private RetrofitInstance() {
//
//    }
//
//    public static Retrofit getInstance() {
//
//        if (instance == null) {
//
//            instance = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//        }
//
//        return instance;
//    }
//
//}

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
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(new BasicAuthInterceptor(USER_NAME, PASSWORD))
//                    .build();

            Log.d("retrooofit", "getInstance: " + BASE_URL+"products"+WOOCOMMERCE_REST_AUTHENTICATION_KEY);

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
                    .build();

        }


        return retrofitInstance;
    }


    private static class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }

    }

}