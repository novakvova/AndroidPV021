package com.example.shop.application;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class HomeApplication extends Application {
    private static HomeApplication instance;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        appContext=getApplicationContext();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public static HomeApplication getInstance() { return instance; }
    public static Context getAppContext() { return appContext; }
}
