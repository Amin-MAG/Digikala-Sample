package com.mag.digikala.Network;

import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.mag.digikala.Network.RetrofitInstance.BASE_URL;
import static com.mag.digikala.Network.RetrofitInstance.WOOCOMMERCE_REST_AUTHENTICATION_KEY;

public interface RetrofitApi {

//    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
//    Call<List<Product>> getTenLastProducts();

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY )
    Call<List<Product>> getAllProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products/{id}" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProductById(@Path("id") String productId, @QueryMap Map<String, String> queryMap);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> searchProducts(@Query("search") String searchText);

    @GET("products" + "/categories" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Category>> getAllCategories();

}
