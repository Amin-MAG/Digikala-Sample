package com.mag.digikala.Network;

import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductAttributesRepository;

import org.jsoup.select.Evaluator;

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

    //  Products //

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getAllProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products/{id}" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<Product> getProductById(@Path("id") String productId);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Product>> searchProducts(@Query("search") String searchText, @QueryMap Map<String, String> map);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProduct(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@QueryMap Map<String, String> map);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getOrderedProducts(@Query("orderby") String attribute, @Query("per_page") int perPage, @Query("page") int numberOfPage);

    // Attributes

    @GET(BASE_URL + "products/attributes" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<ProductAttributesRepository.Attribute>> getAttributes();

    @GET(BASE_URL + "products/attributes/{id}/terms" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<ProductAttributesRepository.Term>> getTerms(@Path("id") String id);


    // Categories //

    @GET("products" + "/categories" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Category>> getAllCategories();

}
