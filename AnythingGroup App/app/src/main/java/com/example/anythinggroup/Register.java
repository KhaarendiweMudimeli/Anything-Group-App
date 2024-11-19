package com.example.anythinggroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.anythinggroup.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        View decor = getWindow().getDecorView();
        if (true) { // Change to your condition
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(0);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this));
            setContentView(binding.getRoot());

            firebaseAuth = FirebaseAuth.getInstance();

            binding.textView.setOnClickListener(view -> {
                Intent intent = new Intent(Register.this, Signin.class);
                startActivity(intent);
            });

            binding.button.setOnClickListener(view -> {
                String email = binding.emailEt.getText().toString();
                String pass = binding.passET.getText().toString();
                String confirmPass = binding.confirmPassEt.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty() && !confirmPass.isEmpty()) {
                    if (pass.equals(confirmPass)) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(Register.this, new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Register.this, Signin.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }}