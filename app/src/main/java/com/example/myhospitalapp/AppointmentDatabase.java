package com.example.myhospitalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class AppointmentDatabase extends SQLiteOpenHelper {

    public static final String DBNAME = "APMDatabase.db";

    public AppointmentDatabase(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE APPOINTMENTS (patientName text, department text, date text, time text, note text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS APPOINTMENTS");

    }

    public Boolean insertData(String name, String department, String date, String time, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientName", name);
        contentValues.put("department", department);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("note", note);
        long result = db.insert("APPOINTMENTS", null, contentValues);
        if (result == -1){
            return false;
        } else return true;
    }

    public ArrayList<Appointment> getAllAppointment(){
        ArrayList<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor reader = db.rawQuery("SELECT patientName, department, date, time, note FROM APPOINTMENTS", null);

        if(reader.moveToFirst()) {
            do {
                String name = reader.getString(0);
                String department = reader.getString(1);
                String date = reader.getString(2);
                String time = reader.getString(3);
                String note = reader.getString(4);
                Appointment appointment = new Appointment(name, department, date, time, note);
                appointments.add(appointment);
                Log.d("patientlist", appointment.toString());
            } while (reader.moveToNext());
        }

        reader.close();
        db.close();

        return appointments;
    }
}
