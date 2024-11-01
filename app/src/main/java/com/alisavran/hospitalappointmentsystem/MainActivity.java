package com.alisavran.hospitalappointmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alisavran.hospitalappointmentsystem.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private ActivityMainBinding binding;

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null){
            Intent intent = new Intent(MainActivity.this,HospitalAppointmentList.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUpClicked(View view){
        email = binding.emailText.getText().toString();
        password = binding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")){
            Toast.makeText(this, "Please enter e-mail and password", Toast.LENGTH_LONG).show();
        }else {
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,HospitalAppointmentList.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void signInClicked(View view){
        email = binding.emailText.getText().toString();
        password = binding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")){
            Toast.makeText(this, "Please enter e-mail and password", Toast.LENGTH_LONG).show();
        }else {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,HospitalAppointmentList.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
