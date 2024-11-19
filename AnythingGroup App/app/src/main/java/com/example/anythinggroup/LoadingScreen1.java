package com.example.anythinggroup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();

        // status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loading_screen1);

        // progress bar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nextIntent = new Intent(LoadingScreen1.this, MainActivity.class);
                startActivity(nextIntent);
                finish();
            }
        }, 5000);


    }

    private void enableEdgeToEdge() {
    }

    // Method to show GIF
    /*
    private void showGIF() {
        ImageView imageView = findViewById(R.id.imageView3);
        Glide.with(this).load(R.drawable.op).into(imageView);
  */  }
