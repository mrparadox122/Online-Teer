package com.onlineteer.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.onlineteer.Home_Screen;
import com.onlineteer.R;

public class Login_screen extends AppCompatActivity {

    AppCompatButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(view -> {
            startActivity(new Intent(this,Home_Screen.class));
        });
    }
}