package com.alisavran.hospitalappointmentsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alisavran.hospitalappointmentsystem.databinding.ItemDoctorBinding;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Doctor doctor);
    }

    private List<Doctor> doctorList;
    private OnItemClickListener listener;

    public DoctorAdapter(List<Doctor> doctorList, OnItemClickListener listener) {
        this.doctorList = doctorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDoctorBinding binding = ItemDoctorBinding.inflate(layoutInflater, parent, false);
        return new DoctorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.bind(doctor, listener);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        private final ItemDoctorBinding binding;

        public DoctorViewHolder(ItemDoctorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Doctor doctor, final OnItemClickListener listener) {
            binding.doctorNameTextView.setText(doctor.getName());
            binding.doctorSpecialtyTextView.setText(doctor.getSpecialty());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(doctor);
                }
            });
        }
    }
}
