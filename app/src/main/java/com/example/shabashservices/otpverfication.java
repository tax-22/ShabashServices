package com.example.shabashservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chaos.view.PinView;

public class otpverfication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverfication);
        PinView pin=findViewById(R.id.pin);
        Button verify=findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newpass=new Intent(otpverfication.this,newpassword.class);
                startActivity(newpass);
            }
        });
    }
}