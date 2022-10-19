package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.ivGirl);

        Glide.with(this)
                .load("http://10.0.2.2:5224/images/1.jpg")
                .apply((new RequestOptions().override(600)))
                .into(imageView);
    }

    public void myClickImage(View view)
    {
        Toast toast = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
        toast.show();
    }
}