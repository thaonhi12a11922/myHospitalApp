package com.example.myhospitalapp;

public class Appointment {

    private String patientName;
    private String department;
    private String date;
    private String time;
    private String note;

    public Appointment(String patientName, String department, String date, String time, String note) {
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.note = note;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patientName='" + patientName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
