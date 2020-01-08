package com.mag.digikala.network;

import android.database.Observable;

import com.mag.digikala.data.model.Category;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.data.repository.FilterRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.mag.digikala.network.RetrofitInstance.BASE_URL;
import static com.mag.digikala.network.RetrofitInstance.WOOCOMMERCE_REST_AUTHENTICATION_KEY;

public interface RetrofitApi {

    //  Products //

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("search") String searchText, @Query("attribute") String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage, @Query("orderby") String baseOn, @Query("order") String order);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("search") String searchText, @Query("attribute") String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("attribute") String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Product>> searchProducts(@Query("search") String searchText, @Query("per_page") int perPage, @Query("page") int numberOfPage);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage, @Query("orderby") String baseOn);

    ///// With Category

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("search") String searchText, @Query("attribute") String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage, @Query("orderby") String baseOn, @Query("order") String order);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("search") String searchText, @Query("attribute") String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("per_page") int perPage, @Query("page") int numberOfPage);


    //// Special For Products

    @GET(BASE_URL + "products/{id}" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<Product> getProductById(@Path("id") String productId);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProduct(@Query("per_page") int perPage, @Query("page") int numberOfPage);


    // Attributes //

    @GET(BASE_URL + "products/attributes" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<FilterRepository.Attribute>> getAttributes();

    @GET(BASE_URL + "products/attributes/{id}/terms" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<FilterRepository.Term>> getTerms(@Path("id") String id);

    // Categories //

    @GET("products" + "/categories" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Category>> getAllCategories();

}
