package com.example.swiftnav;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> namelist; // Defining Array namelist
    ArrayList<String> numberlist; // Defining Array numberlist
    ArrayList<String> addresslist; // Defining Array addresslist
    ArrayList<String> statuslist; // Defining Array statuslist
    Context ctx; // Defining context ctx

    public CustomAdapter(Context ctx,ArrayList<String> namelist, ArrayList<String> numberlist, ArrayList<String> addresslist, ArrayList<String> statuslist) {
        this.namelist = namelist;
        this.numberlist = numberlist;
        this.addresslist = addresslist;
        this.statuslist = statuslist;
        this.ctx = ctx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_ = LayoutInflater.from(parent.getContext()).inflate(R.layout.reclayout, parent, false);
        MyViewHolder view_holder = new MyViewHolder(view_);
        return view_holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(namelist.get(position)); // Setting the name
        holder.number.setText(numberlist.get(position)); // Setting the phone number
        holder.address.setText(addresslist.get(position)); // Setting the address
        holder.status.setText(statuslist.get(position)); // Setting the opened/closed status
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // On clicking the item
                try {
                    // Making google map url
                    String mapurl = "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode((addresslist.get(position)).toString(), "UTF-8");
                    Uri uri = Uri.parse(mapurl);
                    // Creating new Intent to open Google Maps
                    Intent googlemap = new Intent(Intent.ACTION_VIEW, uri);
                    ctx.startActivity(googlemap); // Starting the intent

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        if (namelist != null)
            return namelist.size(); // Returning number of items
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, number, address, status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name); // Linking name with name TextView
            number = itemView.findViewById(R.id.phone); // Linking number with number TextView
            address = itemView.findViewById(R.id.address); // Linking address with address TextView
            status = itemView.findViewById(R.id.isopened); // Linking status with status TextView
        }
    }
}