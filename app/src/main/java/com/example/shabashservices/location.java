package com.example.shabashservices;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.shabashservices.Models.locationmodel;
import com.example.shabashservices.adapters.locationadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class location extends AppCompatActivity {
    SearchView searchView;
    locationadapter adapter;

    ArrayList<locationmodel> model = new ArrayList<>();
    RecyclerView locationrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        locationrecycler = findViewById(R.id.locationrecycler);
        locationrecycler.setLayoutManager(new LinearLayoutManager(this));
        String url = "https://api.countrystatecity.in/v1/countries/IN/cities";
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .addHeaders("X-CSCAPI-KEY", "NDJEM3pWdHg5ekg4N1p5bFRBeXZheHJPWU5nUEc4MW1abkowMzg5Zw==")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("RES", response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                String name = object.getString("name");
                                model.add(new locationmodel(name));
                                adapter = new locationadapter(model, location.this);
                                locationrecycler.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(location.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    public void filter(String text) {
        ArrayList<locationmodel> filteredList = new ArrayList<locationmodel>();
        for (locationmodel item : model) {
            if (item.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredList);
        }
    }
}