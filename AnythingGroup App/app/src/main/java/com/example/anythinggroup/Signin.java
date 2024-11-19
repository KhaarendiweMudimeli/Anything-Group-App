package com.example.anythinggroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.example.anythinggroup.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    private ActivitySigninBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.activity_signin);

        // status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.textView.setOnClickListener(view -> {
            Intent intent = new Intent(Signin.this, Register.class);
            startActivity(intent);
        });

        binding.button.setOnClickListener(view -> {
            String email = binding.emailEt.getText().toString();
            String pass = binding.passET.getText().toString();

            if (!email.isEmpty() && !pass.isEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(Signin.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Signin.this, LoadingScreen1.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Signin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(Signin.this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to enable edge-to-edge display
    private void enableEdgeToEdge() {
        // Implementation goes here
    }
}