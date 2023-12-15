package com.example.myhospitalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppointmentDatabase extends SQLiteOpenHelper {

    public static final String DBNAME = "Appointment.db";

    public AppointmentDatabase(Context context) {
        super(context, "Appointment.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE APPOINTMENT (department text, date text, time text, note text PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insertData(String department, String date, String time, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dpartment", department);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("note", note);
        long result = db.insert("APPOINTMENT", null, contentValues);
        if (result == -1){
            return false;
        } else return true;
    }
}
