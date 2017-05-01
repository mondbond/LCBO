package com.lcbo.di;

import android.content.Context;

import com.lcbo.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    Context providesContext() {
        return app.getApplicationContext();
    }
}
