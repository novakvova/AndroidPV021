package com.example.shop.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.shop.BaseActivity;
import com.example.shop.R;

public class CategoryCreateActivity extends BaseActivity {

    int SELECT_CROPPER = 300;
    Uri uri;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);
        IVPreviewImage=findViewById(R.id.IVPreviewImage);
    }

    public void handleSelectImageClick(View view)
    {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == SELECT_CROPPER) {
            //String base64 = data.getStringExtra("base64");
            uri = (Uri) data.getParcelableExtra("croppedUri");
            IVPreviewImage.setImageURI(uri);
            int a = 12;
            a = 16;
        }
    }

}