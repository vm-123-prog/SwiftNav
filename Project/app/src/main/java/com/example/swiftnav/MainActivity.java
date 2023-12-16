package com.example.swiftnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

// Splash Screen Page: This page is visible to user for only 3s

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hiding title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make App in Full Screen
        getSupportActionBar().hide(); // Hiding action bar
        setContentView(R.layout.activity_main);

        Handler handler = new Handler(); // Handler for updating UI for controlling threads
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent start = new Intent(MainActivity.this, MainActivity2.class); // Intent activity to change the activity page
                startActivity(start); // Triggers the "start" intent
                finish(); // Finishes the task
            }}, 3000); // 3000ms means 3s delay
    }
}