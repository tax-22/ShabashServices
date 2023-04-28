package com.example.shabashservices;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ContactUsFragment extends Fragment {
    Button call;
    Button mail;
    String email="taxilvaghani2003@gmail.com";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        call = view.findViewById(R.id.call);
        mail=view.findViewById(R.id.mail);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + "+918238250078"));
                startActivity(call);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail=new Intent(Intent.ACTION_SEND);
                mail.setType("text/html");
                mail.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
                startActivity(Intent.createChooser(mail, "Send Email"));
            }
        });
        return view;
    }
}