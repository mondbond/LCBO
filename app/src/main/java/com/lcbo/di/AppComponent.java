package com.lcbo.di;

import android.content.Context;

import com.lcbo.App;

import dagger.Component;


@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    Context getContext();
}
