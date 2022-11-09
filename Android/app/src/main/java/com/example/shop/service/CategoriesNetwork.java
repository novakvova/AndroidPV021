package com.example.shop.service;

import com.example.shop.constants.Urls;
import com.example.shop.interceptors.JWTInterceptor;
import com.example.shop.network.CategoriesApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesNetwork {
    private static CategoriesNetwork mInstance;
    private static final String BASE_URL= Urls.BASE;
    private Retrofit mRetrofit;

    private CategoriesNetwork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new JWTInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static CategoriesNetwork getInstance() {
        if(mInstance == null)
            mInstance = new CategoriesNetwork();
        return mInstance;
    }
    public CategoriesApi getJSONApi() {
        return mRetrofit.create(CategoriesApi.class);
    }
}
