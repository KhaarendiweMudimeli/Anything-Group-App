package com.example.anythinggroup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LoadingProjects extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        showGIF();
        // status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loading_screen1);

        // progress bar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nextIntent = new Intent(com.example.anythinggroup.LoadingProjects.this, MainActivity.class);
                startActivity(nextIntent);
                finish();
            }
        }, 5000);



    }

    private void enableEdgeToEdge() {
    }

    // Method to show GIF

    private void showGIF() {


        ImageView imageView = findViewById(R.id.loadingprojects);
        Glide.with(this)
                .asGif()
                .load(R.drawable.black_and_white_elegant_download_our_new_app_instagram_story__3_) // Replace "your_gif" with the name of your GIF file
                .into(imageView);


    }
    }
