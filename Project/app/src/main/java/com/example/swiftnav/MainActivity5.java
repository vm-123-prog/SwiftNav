package com.example.swiftnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import java.util.ArrayList;

// Business Search Page: This page helps to find businesses near to a particular location

public class MainActivity5 extends AppCompatActivity {
    // Defining Input Components
    TextInputLayout business_name;
    TextInputLayout location_name;
    Button search_button;

    // Defining Output Components
    RecyclerView recycler_view;

    // Defining public variables
    public String phonenumber;
    public String status;
    public String address;
    public String title;
    public Boolean statusbool;
    public double latitude;
    public double longitude;
    public String apiKey = "ZcHl1pO0bcD37KZnEL9LmPxEO9BB0FEbywPUDUFnEpo";


    // Defining arrays for storing JSON data
    ArrayList<String> namelist = new ArrayList<>();
    ArrayList<String> numberlist = new ArrayList<>();
    ArrayList<String> addresslist = new ArrayList<>();
    ArrayList<String> statuslist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hiding title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make the app Full Screen
        getSupportActionBar().hide(); // Hide the action bar
        setContentView(R.layout.activity_main5);

        AndroidNetworking.initialize(MainActivity5.this); // Initialize Android Networking Dependency

        business_name = (TextInputLayout) findViewById(R.id.businessname); // Linking "business_name" with the TextInputLayout
        location_name = (TextInputLayout) findViewById(R.id.locationnam); // Linking "location_name" with the TextInputLayout
        search_button = (Button) findViewById(R.id.search_buttonb_bs); // Linking "search_button" with the Button
        recycler_view = (RecyclerView) findViewById(R.id.recyclerView); // Linking "recycler_view" with the RecyclerView

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext())); // Setting the layout manager of Recycler View

        search_button.setOnClickListener(new View.OnClickListener() { // Setting onClickListener for "Search" button
            @Override
            public void onClick(View v) {
                try {
                    // Combining everything to form geocode_url
                    String geocode_url = "https://geocode.search.hereapi.com/v1/geocode?q=" + (location_name.getEditText().getText().toString()).replace(" ","+") + "&apiKey=" + apiKey;

                    // Getting the response from the geocode_url
                    AndroidNetworking.get(geocode_url)
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() { // Get the JSON Object from the JSON
                                @Override
                                public void onResponse(JSONObject response) { // On getting the response from URL
                                    try {
                                        JSONArray items_array = response.getJSONArray("items"); // Defining items_array
                                        JSONObject index_0 = items_array.getJSONObject(0); // Getting child object of items_array
                                        JSONObject position_object = index_0.getJSONObject("position"); // Defining position_object

                                        latitude = position_object.getDouble("lat"); // Getting latitude from position_object
                                        longitude = position_object.getDouble("lng"); // Getting longitude from position_object

                                        // Making the search_business_url
                                        String search_business_url = "https://discover.search.hereapi.com/v1/discover?at="+ latitude + "," + longitude +"&q=" +  URLEncoder.encode((business_name.getEditText().getText().toString()), "UTF-8") + "&apiKey=ZcHl1pO0bcD37KZnEL9LmPxEO9BB0FEbywPUDUFnEpo";

                                        // Getting the response from search_business_url
                                        AndroidNetworking.get(search_business_url)
                                                .setPriority(Priority.HIGH)
                                                .build()
                                                .getAsJSONObject(new JSONObjectRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {
                                                            JSONArray items_array = response.getJSONArray("items"); // Defining items_array
                                                            for(int i=0; i<items_array.length(); i++){ // For loop to store address, title, phone number, open or not status
                                                                JSONObject main_index = items_array.getJSONObject(i);
                                                                JSONObject address_object = main_index.getJSONObject("address");

                                                                title = main_index.getString("title"); // Title means the name of location
                                                                namelist.add(title); // Adding it to the array


                                                                address = address_object.getString("label"); // Address displays the full address
                                                                addresslist.add(address); // Adding it to the array


                                                                try{
                                                                    JSONArray contactsarr = main_index.getJSONArray("contacts");
                                                                    JSONObject subindex = contactsarr.getJSONObject(0);
                                                                    JSONArray phonearr = subindex.getJSONArray("phone");
                                                                    JSONObject primaryphone = phonearr.getJSONObject(0);
                                                                    phonenumber = primaryphone.getString("value"); // Phone Number displays the first phone number in the JSON
                                                                }
                                                                catch (Exception e){
                                                                    phonenumber = "Not Available"; // If phone number not given in JSON
                                                                }
                                                                numberlist.add(phonenumber); // Adding it to the array

                                                                try{
                                                                    JSONArray open_hour_array = main_index.getJSONArray("openingHours");
                                                                    JSONObject sub_index = open_hour_array.getJSONObject(0);
                                                                    statusbool = sub_index.getBoolean("isOpen");
                                                                    if(statusbool = true){
                                                                        status = "Open";
                                                                    }
                                                                    else {
                                                                        status = "Close";
                                                                    }
                                                                }
                                                                catch (Exception e){
                                                                    String status = "Not Available"; // If status is not given
                                                                }
                                                                statuslist.add(status); // Add it to array
                                                            }
                                                        }
                                                        catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                        CustomAdapter customAdapter = new CustomAdapter(MainActivity5.this,namelist,numberlist,addresslist,statuslist); // Creating a custom adapter class for Recycler Views. See CustomAdapter.java class
                                                        recycler_view.setAdapter(customAdapter); // Setting the adapter to recycler view

                                                    }
                                                    @Override
                                                    public void onError(ANError anError) {
                                                        Toast.makeText(MainActivity5.this, "Unexpected Error Occured. Make sure internet connectivity is there and input is in proper format", Toast.LENGTH_SHORT).show(); // Display the error as a toast
                                                    }
                                                });

                                    } catch (JSONException | UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(MainActivity5.this, "Unexpected Error Occured. Make sure internet connectivity is there and input is in proper format", Toast.LENGTH_SHORT).show(); // Display the error as a toast
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}