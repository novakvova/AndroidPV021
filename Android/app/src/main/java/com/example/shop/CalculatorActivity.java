package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorActivity extends BaseActivity {

    private EditText txtInput=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        txtInput = (EditText)findViewById(R.id.txtMyInput);
    }

    public void myClickDigital(View view)
    {
        Button btn = (Button)view;
        String text = txtInput.getText().toString();
        txtInput.setText(text+btn.getText());
    }
}