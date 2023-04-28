package com.example.shabashservices;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpass extends AppCompatActivity {

    EditText email;
    Button reset;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);
        email = findViewById(R.id.emailreset);
        reset = findViewById(R.id.reset);

        mAuth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resemail = email.getText().toString();
                if (resemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(resemail).matches()) {
                    Toast.makeText(forgotpass.this, "Enter Valid E-MAIL Address", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }
                if (!resemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(resemail).matches()) {
                    mAuth.sendPasswordResetEmail(resemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            try {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgotpass.this, "Reset Link Sent Succesfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                throw task.getException();
                            } catch (Exception e) {
                                Toast.makeText(forgotpass.this, "User not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}