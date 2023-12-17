package com.example.myhospitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myhospitalapp.databinding.ActivityPatientHomepageBinding;

public class PatientHomepage extends AppCompatActivity {

    ActivityPatientHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("myHospitalApp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent2 = getIntent();
        String username = intent2.getStringExtra("username");
        binding.customerGreeting.setText("Hello " + username + "!");

        binding.bookAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomepage.this, BookAppointment.class);
                intent.putExtra("patientname", username);
                PatientHomepage.this.startActivity(intent);
            }
        });
    }
}