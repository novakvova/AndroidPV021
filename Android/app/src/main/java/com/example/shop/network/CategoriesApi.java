package com.example.shop.network;


import com.example.shop.common.CategoryCreateDTO;
import com.example.shop.dto.categories.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriesApi {
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> list();
    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO categoryCreateDTO);
}
