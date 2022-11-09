package com.example.shop.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.BaseActivity;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.service.CategoriesNetwork;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCreateActivity extends BaseActivity {

    int SELECT_CROPPER = 300;
    Uri uri;
    ImageView IVPreviewImage;
    private TextInputEditText txtCategoryName;
    private TextInputLayout textFieldCategoryName;
    //private TextView imageError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);
        IVPreviewImage=findViewById(R.id.IVPreviewImage);
        txtCategoryName=findViewById(R.id.txtCategoryName);
        textFieldCategoryName = findViewById(R.id.textFieldCategoryName);
        //imageError = findViewById(R.id.textImageError);
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

    public void handleCreateCategoryClick(View view)
    {

        CategoryCreateDTO categoryCreateDTO=new CategoryCreateDTO();
        categoryCreateDTO.setName(txtCategoryName.getText().toString());
        categoryCreateDTO.setImage(uriGetBase64(uri));
        if(validationFields(categoryCreateDTO)) {
            //CommonUtils.setContext(this);
            //CommonUtils.showLoading();
            CategoriesNetwork
                    .getInstance()
                    .getJSONApi()
                    .create(categoryCreateDTO)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            //CommonUtils.hideLoading();
                            if(response.isSuccessful()) {
                                Intent intent = new Intent(CategoryCreateActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                int code = response.code();
                                Toast.makeText(CategoryCreateActivity.this,"Problem "+ code, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            int z = 4;
                            z=12;
                            //CommonUtils.hideLoading();
                        }
                    });
        }
    }

    private String uriGetBase64(Uri uri)
    {
        try{
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // initialize byte stream
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            // compress Bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            // Initialize byte array
            byte[] bytes=stream.toByteArray();
            // get base64 encoded string
            String sImage= Base64.encodeToString(bytes, Base64.DEFAULT);
            return sImage;
        }
        catch (Exception ex) {
            return null;
        }

    }

    private boolean validationFields(CategoryCreateDTO createCategoryDTO) {
        textFieldCategoryName.setError("");
        if (createCategoryDTO.getName().equals("")) {
            textFieldCategoryName.setError("Вкажіть Назву категорії");
            return false;
        }
        if (createCategoryDTO.getImage() == null) {
            //imageError.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

}