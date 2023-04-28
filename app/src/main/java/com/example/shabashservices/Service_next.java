package com.example.shabashservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shabashservices.Models.servicenextmodel;
import com.example.shabashservices.adapters.servicenextadapter;

import java.util.ArrayList;

public class Service_next extends AppCompatActivity {
    TextView txt1;
    RecyclerView service_next;
    ArrayList<servicenextmodel> model=new ArrayList<>();
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_next);
        txt1=findViewById(R.id.txt1);
        btn=findViewById(R.id.btn);
        service_next=findViewById(R.id.servicenextrecycler);
//        Intent next=getIntent();
//        String str=next.getStringExtra("Name");
//        txt1.setText(str);
        service_next.setLayoutManager(new LinearLayoutManager(this));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));
        model.add(new servicenextmodel("Wiring"));


        servicenextadapter adapter=new servicenextadapter(model,this);
        service_next.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(Service_next.this,serviceproviderlist.class);
                startActivity(next);
            }
        });

    }
}