package com.example.shabashservices;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    EditText mail, password, conpassword;
    Button signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        //fname=findViewById(R.id.fname);
        mail = findViewById(R.id.email);
        signup = findViewById(R.id.signup);
        password = findViewById(R.id.pass);
        conpassword = findViewById(R.id.conpass);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();
                String passw = password.getText().toString();
                String conpass = conpassword.getText().toString();
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(signup.this, "Please enter valid E-Mail address", Toast.LENGTH_SHORT).show();
                    mail.requestFocus(email.length());
                } else if (!passw.equals(conpass) || passw.isEmpty() || conpass.isEmpty()) {
                    Toast.makeText(signup.this, "Please Enter Both Password Same", Toast.LENGTH_SHORT).show();
                    conpassword.requestFocus();
                }
                if (!email.isEmpty() && !conpass.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && passw.equals(conpass) && !passw.isEmpty() && !conpass.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            try {
                                if (task.isSuccessful()) {
                                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putBoolean("flag", true);
                                    editor.apply();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent signup = new Intent(signup.this, consumerhome.class);
                                    startActivity(signup);
                                }
                                throw task.getException();
                            } catch (Exception e) {
                                Toast.makeText(signup.this, "User already  Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}