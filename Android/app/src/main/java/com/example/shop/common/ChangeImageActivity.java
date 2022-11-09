package com.example.shop.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.shop.BaseActivity;
import com.example.shop.R;
import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;

import java.io.File;

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
        else
        {
            this.finish();
        }
    }

    public void handleCropImage(View view) {

        String fileTemp = java.util.UUID.randomUUID().toString();
        Bitmap croppedBitmap = cropView.getOutput();
        Matrix matrix = new Matrix();
        matrix.postRotate(cropView.getRotation());
        Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);

        Uri destination = Uri.fromFile(new File(getCacheDir(), fileTemp));
        CropUtil.saveOutput(this, destination, rotatedBitmap, 90);

        Intent intent = new Intent();
        intent.putExtra("croppedUri", destination);
        setResult(300, intent);
        finish();
    }

}