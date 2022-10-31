package com.example.shop.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.shop.BaseActivity;
import com.example.shop.R;
import com.oginotihiro.cropview.CropView;

public class ChangeImageActivity extends BaseActivity {

    private static int RESULT_LOAD_IMAGE=1;
    private CropView cropView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);

        cropView = findViewById(R.id.cropView);

        Intent modalSelectImage = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(modalSelectImage, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE &&
                resultCode == RESULT_OK && data !=null)
        {
            Uri selecteImage = data.getData();

            cropView.of(selecteImage).asSquare().initialize(ChangeImageActivity.this);
        }
    }
}