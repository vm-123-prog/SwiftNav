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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Reverse GeoCoding: This page helps convert locational coordinates(ie. Latitude,Longitude) to Locational Name

public class MainActivity4 extends AppCompatActivity {

    // Defining input components
    TextInputLayout coordinates_input;
    Button know_more_button;
    Button search_button;
    Button copy_button;

    // Defining output components
    TextView location_name_textview;
    TextView city_textview;
    TextView postal_code_textview;
    TextView state_textview;
    TextView country_textview;

    // Defining public variables
    public String mapurl; // Declaring public String map_url, so that its accessible in all functions
    public String apiKey = "ZcHl1pO0bcD37KZnEL9LmPxEO9BB0FEbywPUDUFnEpo"; // Declaring public String api_key, so that its accessible in all functions
    public String location_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hiding title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make the app Full Screen
        getSupportActionBar().hide(); // Hide the action bar
        setContentView(R.layout.activity_main4);

        AndroidNetworking.initialize(MainActivity4.this); // Initialize Android Networking Dependency

        coordinates_input = (TextInputLayout) findViewById(R.id.coordinates_input); // Linking "coordinates_input" with the TextInputLayout
        search_button = (Button) findViewById(R.id.searchbutton); // Linking "search_button" with the Button
        location_name_textview = (TextView)findViewById(R.id.locationname); // Linking "location_name_textview" with the TextView
        city_textview = (TextView) findViewById(R.id.city); // Linking "city_textview" with the TextView
        postal_code_textview = (TextView) findViewById(R.id.postalcode); // Linking "postal_code_textview" with the TextView
        state_textview = (TextView) findViewById(R.id.state); // Linking "state_textview" with the TextView
        country_textview = (TextView) findViewById(R.id.country); // Linking "country_textview" with the TextView
        know_more_button = (Button) findViewById(R.id.knowmore); // Linking "know_more_button" with the Button
        copy_button = (Button) findViewById(R.id.copy_button_address); // Linking "copy_button" with the Button

        // Setting output components visibility to Invisible
        location_name_textview.setVisibility(View.INVISIBLE);
        city_textview.setVisibility(View.INVISIBLE);
        postal_code_textview.setVisibility(View.INVISIBLE);
        state_textview.setVisibility(View.INVISIBLE);
        country_textview.setVisibility(View.INVISIBLE);
        know_more_button.setVisibility(View.INVISIBLE);
        copy_button.setVisibility(View.INVISIBLE);

        copy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard_Manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip_data = ClipData.newPlainText("Address", location_name);
                clipboard_Manager.setPrimaryClip(clip_data);
            }
        });

        know_more_button.setOnClickListener(new View.OnClickListener() { // Setting onClickListener to "Know More" button to open Google Maps
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(mapurl);
                Intent open_google_maps = new Intent(Intent.ACTION_VIEW, uri); // Intent to open the URL
                startActivity(open_google_maps); // Start the intent
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Combining everything to make the Reverse GeoCode URL
                    String url_reverse_geocode = "https://revgeocode.search.hereapi.com/v1/revgeocode?at="+ URLEncoder.encode((coordinates_input.getEditText().getText().toString().replace(" ","")), "UTF-8") +"&lang=en-US&apiKey=" + apiKey;

                    // To get the response from URL
                    AndroidNetworking.get(url_reverse_geocode)
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() { // Get the JSON Object from the JSON
                                @Override
                                public void onResponse(JSONObject response) { // On getting the response from URL
                                    try {
                                        JSONArray items_array = response.getJSONArray("items"); // Defining items_array
                                        JSONObject index_0 = items_array.getJSONObject(0); // Getting child object of items_array
                                        JSONObject address_object = index_0.getJSONObject("address"); // Defining address

                                        location_name = index_0.getString("title"); // Getting full address from index_0
                                        String city = address_object.getString("city"); // Getting city from address_object
                                        String postal_code = address_object.getString("postalCode"); // Getting Postal Code from address_object
                                        String state = address_object.getString("state"); // Getting state from address_object
                                        String country = address_object.getString("countryName"); // Getting country from address_object

                                        // Setting output components visibility to Visible
                                        location_name_textview.setVisibility(View.VISIBLE);
                                        city_textview.setVisibility(View.VISIBLE);
                                        postal_code_textview.setVisibility(View.VISIBLE);
                                        state_textview.setVisibility(View.VISIBLE);
                                        country_textview.setVisibility(View.VISIBLE);
                                        know_more_button.setVisibility(View.VISIBLE);
                                        copy_button.setVisibility(View.VISIBLE);

                                        // Setting the text of respective textview to its corresponding values
                                        location_name_textview.setText("Location Name: " + location_name);
                                        city_textview.setText("City: " + city);
                                        postal_code_textview.setText("Postal Code: " + postal_code);
                                        state_textview.setText("State: " + state);
                                        country_textview.setText("Country: " + country);

                                        // Combining Google Map API with the address
                                        mapurl = "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode(location_name, "UTF-8");
                                    } catch (JSONException | UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(MainActivity4.this, "Unexpected Error Occured. Make sure internet connectivity is there and input is in proper format", Toast.LENGTH_SHORT).show(); // Display the error as a Toast
                                }
                            });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}