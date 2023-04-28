package com.example.shabashservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shabashservices.Models.serviceprovidermodel;
import com.example.shabashservices.adapters.serviceprovideradapter;

import java.util.ArrayList;

public class serviceproviderlist extends AppCompatActivity {
    RecyclerView serviceprovider_recycle;
    ArrayList<serviceprovidermodel> model=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceproviderlist);
        serviceprovider_recycle=findViewById(R.id.serviceprovider_recycler);
        serviceprovider_recycle.setLayoutManager(new LinearLayoutManager(this));
        model.add(new serviceprovidermodel(R.drawable.profileavatar,"Shivam Javiya",4));
        model.add(new serviceprovidermodel(R.drawable.profileavatar,"Shivam Javiya", 4.5F));
        model.add(new serviceprovidermodel(R.drawable.profileavatar,"Shivam Javiya",3.5F));
        model.add(new serviceprovidermodel(R.drawable.profileavatar,"Shivam Javiya",3));

        serviceprovideradapter adapter=new serviceprovideradapter(model,this);
        serviceprovider_recycle.setAdapter(adapter);
    }
}