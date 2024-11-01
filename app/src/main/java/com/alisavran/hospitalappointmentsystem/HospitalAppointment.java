package com.alisavran.hospitalappointmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alisavran.hospitalappointmentsystem.databinding.ActivityHospitalAppointmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class HospitalAppointment extends AppCompatActivity {

    private ActivityHospitalAppointmentBinding binding;
    private String doctorName;
    private String doctorSpecialty;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalAppointmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        doctorName = getIntent().getStringExtra("doctorName");
        doctorSpecialty = getIntent().getStringExtra("doctorSpecialty");

        binding.doctorNameTextView.setText(doctorName);
        binding.doctorSpecialtyTextView.setText(doctorSpecialty);

        binding.bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookAppointment();
            }
        });
    }

    private void bookAppointment() {
        int day = binding.datePicker.getDayOfMonth();
        int month = binding.datePicker.getMonth() + 1;
        int year = binding.datePicker.getYear();
        int hour = binding.timePicker.getHour();
        int minute = binding.timePicker.getMinute();

        String date = day + "/" + month + "/" + year;
        String time = hour + ":" + String.format("%02d", minute);

        String userId = auth.getCurrentUser().getUid();

        // Aynı zamanda aynı doktordan randevu alınıp alınmadığını kontrol et
        db.collection("appointments")
                .whereEqualTo("doctorName", doctorName)
                .whereEqualTo("date", date)
                .whereEqualTo("time", time)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        boolean isAvailable = true;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            isAvailable = false;
                            break;
                        }

                        if (isAvailable) {
                            // Randevu alınabiliyor, randevuyu kaydet
                            Appointment appointment = new Appointment(userId, doctorName, doctorSpecialty, date, time);
                            db.collection("appointments").add(appointment)
                                    .addOnSuccessListener(documentReference -> {
                                        Toast.makeText(HospitalAppointment.this, "Appointment booked for " + doctorName + " on " + date + " at " + time, Toast.LENGTH_LONG).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(HospitalAppointment.this, "Failed to book appointment: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    });
                        } else {
                            // Aynı zamana randevu alınmış
                            Toast.makeText(HospitalAppointment.this, "This time slot is already booked for " + doctorName, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(HospitalAppointment.this, "Failed to check availability", Toast.LENGTH_LONG).show();
                    }
                });
    }
}