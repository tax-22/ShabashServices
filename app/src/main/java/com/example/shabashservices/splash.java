package com.example.shabashservices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                Boolean check=pref.getBoolean("flag",false);
                Intent iNext;
                if(check){
                    iNext=new Intent(splash.this,consumerhome.class);
                    startActivity(iNext);
                    finish();
                }
                else{
                    iNext=new Intent(splash.this,login.class);
                    startActivity(iNext);
                    finish();
                }
            }
        },3000);

    }
}