package com.lcbo.retrofit;

import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.model.pojo.Stores.Stores;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LcboApi {

    @GET("stores?")
    Call<Stores> getStoresByCityNameAndFilters(@Query("q") String city, @Query("where") String filter,
                                               @Query("page") int page, @Query("access_key") String token);

    @GET("products?")
    Call<StoreProducts> getProductsByStore(@Query("store_id") String storeId, @Query("page") int page,
                                           @Query("access_key") String token);

    @GET("products?")
    Call<StoreProducts> getProductsByNameAndFilter(@Query("q") String productName, @Query("where") String filter,
                                                   @Query("page") int page, @Query("access_key") String token);
}
