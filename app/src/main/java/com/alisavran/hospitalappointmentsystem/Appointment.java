package com.alisavran.hospitalappointmentsystem;

public class Appointment {
    public String userId;
    public String doctorName;
    public String doctorSpecialty;
    public String date;
    public String time;

    public Appointment() {
        // Firestore için boş kurucu
    }

    public Appointment(String userId, String doctorName, String doctorSpecialty, String date, String time) {
        this.userId = userId;
        this.doctorName = doctorName;
        this.doctorSpecialty = doctorSpecialty;
        this.date = date;
        this.time = time;
    }

    // Getter ve Setter metodları

    public String getUserId() {
        return userId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}