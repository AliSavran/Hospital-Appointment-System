package com.alisavran.hospitalappointmentsystem;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alisavran.hospitalappointmentsystem.databinding.ItemAppointmentBinding;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAppointmentBinding binding = ItemAppointmentBinding.inflate(layoutInflater, parent, false);
        return new AppointmentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.bind(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private final ItemAppointmentBinding binding;

        public AppointmentViewHolder(ItemAppointmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Appointment appointment) {
            binding.doctorNameTextView.setText(appointment.getDoctorName());
            binding.doctorSpecialtyTextView.setText(appointment.getDoctorSpecialty());
            binding.dateTextView.setText(appointment.getDate());
            binding.timeTextView.setText(appointment.getTime());
        }
    }
}
