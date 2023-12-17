package com.example.myhospitalapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAppointmentViewHolder extends RecyclerView.ViewHolder {

    ImageView icon;
    TextView username, department, date, time, note;
    public ViewAppointmentViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.iconIV);
        username = itemView.findViewById(R.id.usernameTV);
        date = itemView.findViewById(R.id.dateTV);
        time = itemView.findViewById(R.id.timeTV);
        department = itemView.findViewById(R.id.departmentTV);

    }
}
