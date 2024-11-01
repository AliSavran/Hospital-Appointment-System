package com.alisavran.hospitalappointmentsystem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alisavran.hospitalappointmentsystem.databinding.ActivityViewAppointmentBinding;
import com.alisavran.hospitalappointmentsystem.databinding.ActivityViewAppointmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {

    private ActivityViewAppointmentBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private List<Appointment> appointmentList;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        appointmentList = new ArrayList<>();
        adapter = new AppointmentAdapter(appointmentList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        loadAppointments();
    }

    private void loadAppointments() {
        String userId = auth.getCurrentUser().getUid();

        db.collection("appointments")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        appointmentList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Appointment appointment = document.toObject(Appointment.class);
                            appointmentList.add(appointment);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
