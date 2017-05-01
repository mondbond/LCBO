package com.lcbo.model;

import android.util.Log;

import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.model.pojo.Stores.Stores;
import com.lcbo.retrofit.LcboApi;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkData {

    String token = "MDoyOWYwNGEyNi0yNzg4LTExZTctOWMxNS1mZjFkMTJmMzk4ODk6UWFDSWFEQlU3d2p5eEN5WFJqZE9nV0xRSmRJZmZvdzYxTFlH";

    public Observable<Stores> getStoresByCityNameAndFilter(final Retrofit retrofit, String cityName, String filter, int page) throws IOException {
        return Observable.create(new Observable.OnSubscribe<Stores>() {
            @Override
            public void call(Subscriber<? super Stores> subscriber) {
                LcboApi api = retrofit.create(LcboApi.class);
                    api.getStoresByCityNameAndFilters(cityName, filter, page, token).enqueue(new Callback<Stores>() {
                        @Override
                        public void onResponse(Call<Stores> call, Response<Stores> response) {
                            if(response.isSuccessful()){
                                subscriber.onNext(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<Stores> call, Throwable t) {
                            Log.d("ERROR", t.getMessage());
                        }
                    });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StoreProducts> getProductsDataByStore(final Retrofit retrofit, String cityId, int page) throws IOException {
        return Observable.create(new Observable.OnSubscribe<StoreProducts>() {
            @Override
            public void call(Subscriber<? super StoreProducts> subscriber) {
                LcboApi api = retrofit.create(LcboApi.class);
                api.getProductsByStore(cityId, page, token).enqueue(new Callback<StoreProducts>() {
                    @Override
                    public void onResponse(Call<StoreProducts> call, Response<StoreProducts> response) {
                        if(response.isSuccessful()){
                            subscriber.onNext(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<StoreProducts> call, Throwable t) {
                        Log.d("ERROR", t.getMessage());
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StoreProducts> getProductsDataByNameAndFilters(Retrofit retrofit, String productName, String filter, int page) {
        return Observable.create(new Observable.OnSubscribe<StoreProducts>() {
            @Override
            public void call(Subscriber<? super StoreProducts> subscriber) {
                LcboApi api = retrofit.create(LcboApi.class);
                api.getProductsByNameAndFilter(productName, filter, page, token).enqueue(new Callback<StoreProducts>() {
                    @Override
                    public void onResponse(Call<StoreProducts> call, Response<StoreProducts> response) {
                        if(response.isSuccessful()){
                            subscriber.onNext(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<StoreProducts> call, Throwable t) {
                        Log.d("ERROR", t.getMessage());
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
