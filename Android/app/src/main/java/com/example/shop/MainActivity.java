package com.example.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shop.dto.categories.CategoryItemDTO;
import com.example.shop.service.CategoriesNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.ivGirl);

        Glide.with(this)
                .load("https://pv021.novakvova.com/images/girl.jpg")
                .apply((new RequestOptions().override(600)))
                .into(imageView);
    }

    public void myClickImage(View view)
    {
//        Toast toast = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
//        toast.show();
        requestServer();
    }
    private void requestServer() {
        MainActivity instance = this;
        CategoriesNetwork
                .getInstance()
                .getJSONApi()
                .list()
                .enqueue(new Callback<List<CategoryItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                        List<CategoryItemDTO> data = response.body();
                        Toast toast = Toast.makeText(instance, data.get(0).getName(), Toast.LENGTH_LONG);
                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {

                    }
                });
    }
}