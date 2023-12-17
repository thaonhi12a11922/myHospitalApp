package com.example.myhospitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myhospitalapp.databinding.ActivityBookAppointmentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    ActivityBookAppointmentBinding binding;
    AppointmentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new AppointmentDatabase(this);
        String[] departments = {"Select Area", "Cardiology", "Respiratory diseases", "Dermatology",
                "Gynecology", "Psychiatry", "Neurology", "Elderly Services", "Ophthalmology", "Rheumatology "};

        Appointment appointment = new Appointment(null, null, null, null, null);

        Spinner spinner = (Spinner) binding.departmentSpinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                appointment.setDepartment(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);

        EditText selectDate = (EditText) binding.selectDateEV;
        EditText selectTime = (EditText) binding.selectTimeEV;
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String date = materialDatePicker.getHeaderText();
                selectDate.setText(date);
            }
        });
        binding.selectDateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "Tag_date_picker");
            }
        });
        Calendar calendar = Calendar.getInstance();
        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(calendar.get(Calendar.HOUR_OF_DAY))
                .setMinute(calendar.get(Calendar.MINUTE))
                .build();
        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = materialTimePicker.getHour();
                int minute = materialTimePicker.getMinute();
                selectTime.setText(hour + ":" + minute);
            }
        });

        binding.selectTimeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialTimePicker.show(getSupportFragmentManager(), "Tag_time_picker");
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department = appointment.getDepartment();
                String date = binding.selectDateEV.getText().toString();
                String time = binding.selectTimeEV.getText().toString();
                String note = binding.noteEV.getText().toString();


                Intent intent = getIntent();
                String patientName = intent.getStringExtra("patientname");
                appointment.setPatientName(patientName);

                if (department.equals("")
                        || date.equals("")
                        || time.equals("")
                        || note.equals("")){
                    Toast.makeText(BookAppointment.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insert = db.insertData(patientName, department, date, time, note);
                    if (insert){
                        String message = "Booking successfully!";
                        Toast.makeText(BookAppointment.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BookAppointment.this, "Booking failed. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}