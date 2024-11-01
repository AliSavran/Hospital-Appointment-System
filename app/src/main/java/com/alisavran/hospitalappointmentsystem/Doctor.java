package com.alisavran.hospitalappointmentsystem;

public class Doctor {

    public String name;
    public String specialty;

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }
}


