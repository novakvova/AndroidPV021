package com.example.shop.interceptors;

import com.example.shop.application.HomeApplication;
import com.example.shop.application.JwtSecurityService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JWTInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        JwtSecurityService jwtService = (JwtSecurityService) HomeApplication.getInstance();
        String token = jwtService.getToken();
        Request.Builder builder = chain.request().newBuilder();
        if(token!=null && !token.isEmpty())
        {
            builder.header("Authorization", "Bearer "+token);
        }
        return chain.proceed(builder.build());
    }
}