package com.onlineteer.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onlineteer.Api.LoginApi;
import com.onlineteer.Home_Screen;
import com.onlineteer.R;

public class Login_screen extends AppCompatActivity implements LoginApi.OnLoginApiHitListner {

    AppCompatButton login_btn;
    EditText edittext_login_username,edittext_login_password;

    TextView register_an_account_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        SetViews();
        ClickListners();

    }
    public void SetViews()
    {
        login_btn = findViewById(R.id.login_btn);
        edittext_login_username = findViewById(R.id.edittext_login_username);
        edittext_login_password = findViewById(R.id.edittext_login_password);
        register_an_account_login = findViewById(R.id.register_an_account_login);
    }
    public void ClickListners()
    {
        login_btn.setOnClickListener(view -> {
            String username = edittext_login_username.getText().toString();
            String password = edittext_login_password.getText().toString();

            if (!username.isEmpty() && !password.isEmpty())
            {
                LoginApi loginApi = new LoginApi(username,password,this);
                loginApi.HitApi();
            }
            else
            {
                Toast.makeText(this, "Username And Password Required", Toast.LENGTH_SHORT).show();
            }


        });
        register_an_account_login.setOnClickListener(view -> {
            startActivity(new Intent(Login_screen.this,Registration_form.class));
        });
    }

    @Override
    public void OnLoginSuccessful(String Message) {
        runOnUiThread( () -> {
            startActivity(new Intent(this,Home_Screen.class));
            Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        } );
    }

    @Override
    public void OnLoginFailure(String Error) {
        runOnUiThread( () -> {
            Toast.makeText(this, Error, Toast.LENGTH_SHORT).show();
        } );
    }
}