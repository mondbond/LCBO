package com.lcbo.di;

import com.lcbo.App;
import com.lcbo.model.DbModel;
import com.lcbo.model.NetworkData;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {

    @Provides
    @Named("lcboAPI")
    Retrofit providesLcboApiRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://lcboapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    NetworkData providesNetworkUtil() {
        return new NetworkData();
    }

    @Provides
    DbModel providesDbModel() {
        return new DbModel(App.getAppComponent().getContext());
    }

}
