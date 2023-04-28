package com.example.shabashservices;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shabashservices.Models.bookedservicesmodel;
import com.example.shabashservices.adapters.Bookedservice;

import java.util.ArrayList;

public class BookedServiceFragment extends Fragment {
    ArrayList<bookedservicesmodel> model=new ArrayList<>();
    RecyclerView bookedservices;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BookedServiceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookedServiceFragment newInstance(String param1, String param2) {
        BookedServiceFragment fragment = new BookedServiceFragment();
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
        View view=inflater.inflate(R.layout.fragment_booked_service,container,false);
        bookedservices=view.findViewById(R.id.bookedrecycler);
        model.clear();
        bookedservices.setLayoutManager(new LinearLayoutManager(getContext()));
        model.add(new bookedservicesmodel("Electrician","20/12/2020",R.drawable.electrician2));
        model.add(new bookedservicesmodel("Electrician","20/12/2020",R.drawable.carpenter));
        model.add(new bookedservicesmodel("Electrician","20/12/2020",R.drawable.plumber));

        Bookedservice adapter=new Bookedservice(getContext(),model);
        bookedservices.setAdapter(adapter);
        return view;
    }
}