package com.example.swiftnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URLEncoder;

// GeoCoding Page: This page helps convert text to locational coordinates(i.e. Latitudes and Longitudes)

public class MainActivity3 extends AppCompatActivity {
    // Defining Input Components
    TextInputLayout location_input;
    Button search_button_geocode;
    Button know_more_button;
    Button copy_button;

    // Defining Output Components
    TextView latitude_textview;
    TextView longitude_textview;
    TextView city_textview;
    TextView postal_code_textview;
    TextView state_textview;
    TextView country_textview;
    TextView address_textview;

    // Defining public variables
    public String map_url; // Declaring public String map_url, so that its accessible in all functions
    public String api_key = "ZcHl1pO0bcD37KZnEL9LmPxEO9BB0FEbywPUDUFnEpo"; // Declaring public String api_key, so that its accessible in all functions
    public double latitude;
    public double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hiding title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make the app Full Screen
        getSupportActionBar().hide(); // Hide the action bar
        setContentView(R.layout.activity_main3);

        AndroidNetworking.initialize(this); // Initialize Android Networking Dependency

        search_button_geocode = (Button) findViewById(R.id.buttonsrch); // Linking "search_button_geocode" with the Button
        location_input = (TextInputLayout) findViewById(R.id.locationsrch); // Linking "location_input" with the TextInputLayout

        latitude_textview = (TextView) findViewById(R.id.latitude); // Linking "latitude_textview" with the TextView
        longitude_textview = (TextView) findViewById(R.id.longitude); // Linking "longitude_textview" with the TextView
        city_textview = (TextView) findViewById(R.id.city); // Linking "city_textview" with the TextView
        postal_code_textview = (TextView) findViewById(R.id.postalcode); // Linking "postal_code_textview" with the TextView
        state_textview = (TextView) findViewById(R.id.state); // Linking "state_textview" with the TextView
        country_textview = (TextView) findViewById(R.id.country); // Linking "country_textview" with the TextView
        address_textview = (TextView) findViewById(R.id.address); // Linking "address_textview" with the TextView
        know_more_button = (Button) findViewById(R.id.knowmore); // Linking "know_more_button" with the Button
        copy_button = (Button) findViewById(R.id.copy_button_cordinates); // Linking "copy_button" with the Button

        // Setting output components visibility to Invisible
        latitude_textview.setVisibility(View.INVISIBLE);
        longitude_textview.setVisibility(View.INVISIBLE);
        city_textview.setVisibility(View.INVISIBLE);
        postal_code_textview.setVisibility(View.INVISIBLE);
        state_textview.setVisibility(View.INVISIBLE);
        country_textview.setVisibility(View.INVISIBLE);
        address_textview.setVisibility(View.INVISIBLE);
        know_more_button.setVisibility(View.INVISIBLE);
        copy_button.setVisibility(View.INVISIBLE);

        copy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard_Manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip_data = ClipData.newPlainText("Coordinates",latitude + "," + longitude);
                clipboard_Manager.setPrimaryClip(clip_data);
            }
        });

        know_more_button.setOnClickListener(new View.OnClickListener() { // Setting onClickListener to "Know More" button to open Google Maps
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(map_url);
                Intent open_google_maps = new Intent(Intent.ACTION_VIEW, uri); // Intent to open the URL
                startActivity(open_google_maps); // Start the intent
            }
        });
        search_button_geocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Combining everything to make the GeoCode URL
                String geocode_url = "https://geocode.search.hereapi.com/v1/geocode?q=" + (location_input.getEditText().getText().toString()).replace(" ","+") + "&apiKey=" + api_key;

                // To get the response from URL
                AndroidNetworking.get(geocode_url)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() { // Get the JSON Object from the JSON
                            @Override
                            public void onResponse(JSONObject response) { // On getting the response from URL
                                try {
                                    JSONArray items_array = response.getJSONArray("items"); // Defining items_array
                                    JSONObject index_0 = items_array.getJSONObject(0); // Getting child object of items_array
                                    JSONObject position_object = index_0.getJSONObject("position"); // Defining position
                                    JSONObject address_object = index_0.getJSONObject("address"); // Defining address


                                    latitude = position_object.getDouble("lat"); // Getting latitude from position_object
                                    longitude = position_object.getDouble("lng"); // Getting longitude from position_object
                                    String city = address_object.getString("city"); // Getting city from address_object
                                    String postal_code = address_object.getString("postalCode"); // Getting Postal Code from address_object
                                    String state = address_object.getString("state"); // Getting state from address_object
                                    String Country = address_object.getString("countryName"); // Getting country from address_object
                                    String address = address_object.getString("label"); // Getting full address from address_object

                                    // Setting output components visibility to Visible
                                    latitude_textview.setVisibility(View.VISIBLE);
                                    longitude_textview.setVisibility(View.VISIBLE);
                                    city_textview.setVisibility(View.VISIBLE);
                                    postal_code_textview.setVisibility(View.VISIBLE);
                                    state_textview.setVisibility(View.VISIBLE);
                                    country_textview.setVisibility(View.VISIBLE);
                                    address_textview.setVisibility(View.VISIBLE);
                                    know_more_button.setVisibility(View.VISIBLE);
                                    copy_button.setVisibility(View.VISIBLE);


                                    // Setting the text of respective textview to its corresponding values
                                    latitude_textview.setText("Latitude: " + latitude);
                                    longitude_textview.setText("Longitude: " + longitude);
                                    city_textview.setText("City: " + city);
                                    postal_code_textview.setText("Postal Code: " + postal_code);
                                    state_textview.setText("State: " + state);
                                    country_textview.setText("Country: " + Country);
                                    address_textview.setText("Address: " + address);

                                    // Combining Google Map API with the address
                                    map_url = "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode(address, "UTF-8");
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError anError) { // On getting error from URL
                                Toast.makeText(MainActivity3.this, "Unexpected Error Occured. Make sure internet connectivity is there and input is in proper format", Toast.LENGTH_SHORT).show(); // Display the error as a Toast
                            }
                        });
            }
        });
    }
}