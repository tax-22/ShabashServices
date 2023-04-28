package com.example.shabashservices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shabashservices.Models.currentservicesmodel;
import com.example.shabashservices.adapters.Currentservice;

import java.util.ArrayList;


public class CurrentBookingFragment extends Fragment {

    ArrayList<currentservicesmodel> model=new ArrayList<>();
    RecyclerView currentrecycler;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrentBookingFragment() {
        // Required empty public constructor
    }

    public static CurrentBookingFragment newInstance(String param1, String param2) {
        CurrentBookingFragment fragment = new CurrentBookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_current_booking, container, false);
        currentrecycler=view.findViewById(R.id.recyclercurrentservice);
        model.clear();
        currentrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        model.add(new currentservicesmodel(R.drawable.carpenter,"Carpenter"));
        model.add(new currentservicesmodel(R.drawable.carpenter,"Carpenter"));
        model.add(new currentservicesmodel(R.drawable.carpenter,"Carpenter"));
        model.add(new currentservicesmodel(R.drawable.carpenter,"Carpenter"));
        model.add(new currentservicesmodel(R.drawable.carpenter,"Carpenter"));

        Currentservice adapter=new Currentservice(getContext(),model);
        currentrecycler.setAdapter(adapter);
        return view;
    }
}