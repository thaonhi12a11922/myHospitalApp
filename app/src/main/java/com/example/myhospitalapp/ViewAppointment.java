package com.example.myhospitalapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhospitalapp.databinding.ActivityViewAppointmentBinding;

import java.util.List;

public class ViewAppointment extends AppCompatActivity {

    ActivityViewAppointmentBinding binding;
    AppointmentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new AppointmentDatabase(this);

        List<Appointment> appointments = db.getAllAppointment();

        RecyclerView recyclerView = binding.viewAppointmentRV;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ViewAppointmentAdapter(getApplicationContext(), appointments));
    }
}