package com.example.myhospitalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myhospitalapp.databinding.ActivityLoginBinding;
import com.example.myhospitalsystem.Database;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    com.example.myhospitalsystem.Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Database(this);
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String password = binding.password.getText().toString();

                if (username.equals("") || (password.equals(""))){
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkUsernamePassword = db.checkUsernamePassword(username, password);
                    if (checkUsernamePassword){
                        Toast.makeText(LoginActivity.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, PatientHomepage.class);
                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}