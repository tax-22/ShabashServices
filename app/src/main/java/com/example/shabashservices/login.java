package com.example.shabashservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class login extends AppCompatActivity {

    EditText uname, password;
    Button login, signup, forgotpass;
    ImageButton google;
    private FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        progressDialog = new ProgressDialog(login.this);
        progressDialog.setTitle("Log in");
        progressDialog.setMessage("We are logging you in");
        signup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forgotpass);
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.pass);
        google = findViewById(R.id.loging);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = uname.getText().toString();
                String pass = password.getText().toString();
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(login.this, "Enter Valid E-Mail address", Toast.LENGTH_SHORT).show();
                    uname.requestFocus();
                } else if (pass.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }
                if (!email.isEmpty() && !pass.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putBoolean("flag", true);
                                        editor.apply();
                                        Intent login = new Intent(login.this, consumerhome.class);
                                        startActivity(login);
                                        finish();
                                    } else {
                                        progressDialog.dismiss();
                                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                                        switch (errorCode) {
                                            case "ERROR_INVALID_CREDENTIAL":
                                                Toast.makeText(login.this, "Invalid E-Mail address or Password.", Toast.LENGTH_LONG).show();
                                                break;

                                            case "ERROR_WRONG_PASSWORD":
                                                Toast.makeText(login.this, "The password is invalid", Toast.LENGTH_LONG).show();
                                                password.requestFocus();
                                                password.setText("");
                                                break;

                                            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                                Toast.makeText(login.this, "An account already exists with the same email address", Toast.LENGTH_LONG).show();
                                                break;


                                            case "ERROR_USER_NOT_FOUND":
                                                Toast.makeText(login.this, "User Not Found", Toast.LENGTH_LONG).show();
                                                break;

                                            case "ERROR_WEAK_PASSWORD":
                                                Toast.makeText(login.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
                                                password.requestFocus();
                                                break;

                                        }
                                    }
                                }

                            });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(login.this, signup.class);
                startActivity(signup);
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgot = new Intent(login.this, forgotpass.class);
                startActivity(forgot);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                signIn();
            }
        });
    }

    int RC_SIGN_IN = 40;

    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
//                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("flag", true);
                            editor.apply();
                            Intent intent = new Intent(login.this, consumerhome.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

