package com.example.swiftnav;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

// Main Page: All the navigation takes place, from here we can go to: GeoCoding Page, Reverse GeoCoding Page or Business Search

public class MainActivity2 extends AppCompatActivity {
    Button geocode_button; // Defining geocode_button as a button
    Button reverse_geocode_button; // Defining reverse_geocode_button as a button
    Button business_button; // Defining business_button as a button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hiding title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make the app Full Screen
        getSupportActionBar().hide(); // Hide the action bar
        setContentView(R.layout.activity_main2);
        geocode_button= (Button) findViewById(R.id.geocodebtn); // Linking "geocode_button" with the button
        reverse_geocode_button = (Button) findViewById(R.id.revgeocode); // Linking "reverse_geocode_button" with the button
        business_button = (Button) findViewById(R.id.businessbtn); // Linking "business_button" with the button
        // Setting onClickListener for all buttons (i.e Set actions on button click)
        geocode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geocode = new Intent(MainActivity2.this, MainActivity3.class); // Intent Activity for changing page to GeoCoding Page
                startActivity(geocode); // Starting the Intent activity
            }
        });
        reverse_geocode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reverse_geocode = new Intent(MainActivity2.this, MainActivity4.class); // Intent Activity for changing page to Reverse GeoCoding Page
                startActivity(reverse_geocode); // Starting the intent activity
            }
        });
        business_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent business_search = new Intent(MainActivity2.this, MainActivity5.class); // Intent Activity for changing page to Business Search
                startActivity(business_search); // Starting the intent activity
            }
        });
    }
}