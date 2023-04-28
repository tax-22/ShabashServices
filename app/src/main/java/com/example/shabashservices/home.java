package com.example.shabashservices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class home extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView services;
    ArrayList<serviceModel> service=new ArrayList<>();

    public home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        services= view.findViewById(R.id.services);
        service.clear();
        services.setLayoutManager(new GridLayoutManager(getContext(),3));
        service.add(new serviceModel(R.drawable.electrician2,"Electrician"));
        service.add(new serviceModel(R.drawable.plumber,"Plumber"));
        service.add(new serviceModel(R.drawable.carpenter,"Carpenter"));
        service.add(new serviceModel(R.drawable.electrician2,"Electrician"));
        service.add(new serviceModel(R.drawable.plumber,"Plumber"));
        service.add(new serviceModel(R.drawable.carpenter,"Carpenter"));


        Service_adapter service_adapter=new Service_adapter(getContext(),service);
        services.setAdapter(service_adapter);

        return view;
    }
}