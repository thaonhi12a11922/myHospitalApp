package com.example.myhospitalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAppointmentAdapter extends RecyclerView.Adapter<ViewAppointmentViewHolder> {

    Context context;
    List<Appointment> appointmentList;

    public ViewAppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public ViewAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.view_appointment_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAppointmentViewHolder holder, int position) {
        holder.department.setText(appointmentList.get(position).getDepartment());
        holder.username.setText(appointmentList.get(position).getPatientName());
        holder.date.setText(appointmentList.get(position).getDate());
        holder.time.setText(appointmentList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}
