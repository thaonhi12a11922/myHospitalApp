package com.example.myhospitalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myhospitalapp.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    com.example.myhospitalapp.Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: fix crashed back nurse
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("myHospitalApp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new com.example.myhospitalapp.Database(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.username2.getText().toString();
                String password = binding.password2.getText().toString();
                String repassword = binding.repassword.getText().toString();

                if (username.equals("") || (password.equals("")) || (repassword.equals(""))){
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(repassword)){
                        Boolean checkUserExist = db.checkUserExist(username);
                        if (!checkUserExist){
                            Boolean insert = db.insertData(username, password);
                            if (insert){
                                Toast.makeText(Signup.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Signup.this, "Registered failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(Signup.this, "Patient already existed.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup.this, "Password not match.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}