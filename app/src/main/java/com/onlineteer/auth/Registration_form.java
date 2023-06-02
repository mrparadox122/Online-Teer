package com.onlineteer.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.onlineteer.Api.RegistrationApi;
import com.onlineteer.R;

public class Registration_form extends AppCompatActivity implements RegistrationApi.OnRegistrationApiHitListner {


    EditText edittext_full_name,edittext_user_name,edittext_number,edittext_pass_word,Edittext_confirm_password,Edittext_referral_code;
    CheckBox checkbox;
    AppCompatButton send_OTP_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

    }

    public void SetViews()
    {
        edittext_full_name = findViewById(R.id.edittext_full_name);
        edittext_user_name = findViewById(R.id.edittext_user_name);
        edittext_number = findViewById(R.id.edittext_number);
        edittext_pass_word = findViewById(R.id.edittext_pass_word);
        Edittext_confirm_password = findViewById(R.id.Edittext_confirm_password);
        Edittext_referral_code = findViewById(R.id.Edittext_referral_code);
        checkbox = findViewById(R.id.checkbox);
        send_OTP_btn = findViewById(R.id.send_OTP_btn);
    }
    public void ClickListners()
    {
        send_OTP_btn.setOnClickListener(view -> {
            String fullname = edittext_full_name.getText().toString();
            String username = edittext_user_name.getText().toString();
            String number = edittext_number.getText().toString();
            String password = edittext_pass_word.getText().toString();
            String confirmPassword = Edittext_confirm_password.getText().toString();
            String RefCode = Edittext_referral_code.getText().toString();

        });
    }

    @Override
    public void OnRegistrationSuccessful(String Message) {

    }

    @Override
    public void OnRegistrationFailure(String Error) {

    }
}