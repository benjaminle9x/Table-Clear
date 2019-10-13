package com.n01216688.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setTitle("Register Page");
    }
}
