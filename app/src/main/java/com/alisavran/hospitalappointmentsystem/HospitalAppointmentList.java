package com.alisavran.hospitalappointmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alisavran.hospitalappointmentsystem.databinding.ActivityHospitalAppointmentListBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HospitalAppointmentList extends AppCompatActivity {
    FirebaseAuth auth;

    private ActivityHospitalAppointmentListBinding binding;
    private List<Doctor> doctorList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalAppointmentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Dr. Ahmet Demir","Kardiyoloji"));
        doctorList.add(new Doctor("Dr. Asya Öz","Sinir Hastalıkları"));
        doctorList.add(new Doctor("Dr. Mehmet Dursun","Fizik Tedavi"));
        doctorList.add(new Doctor("Dr. Ayşe Gümüş","Dahiliye"));
        doctorList.add(new Doctor("Dr. Funda Er","Kadın Hastalıkları"));
        doctorList.add(new Doctor("Dr. Ali Er","Beslenme ve Diyet"));
        doctorList.add(new Doctor("Dr. Burak Güneş","Ortopedi"));
        doctorList.add(new Doctor("Dr. Berna Kara","Kulak Burun Boğaz"));

        DoctorAdapter adapter = new DoctorAdapter(doctorList, new DoctorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Doctor doctor) {
                Intent intent = new Intent(HospitalAppointmentList.this, HospitalAppointment.class);
                intent.putExtra("doctorName", doctor.getName());
                intent.putExtra("doctorSpecialty", doctor.getSpecialty());
                startActivity(intent);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            auth.signOut();

            Intent intentToMain = new Intent(HospitalAppointmentList.this, MainActivity.class);
            startActivity(intentToMain);
            finish();
        } else if (item.getItemId() == R.id.view_appointments) {
            Intent intentToAppointments = new Intent(HospitalAppointmentList.this, ViewAppointmentActivity.class);
            startActivity(intentToAppointments);
        }
        return super.onOptionsItemSelected(item);
    }
}
