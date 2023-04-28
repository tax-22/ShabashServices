package com.example.shabashservices;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class consumerhome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigation;
    Toolbar tool;
    int LOCATION_PERMISSION_CODE = 1;
    final static String PACKAGE_NAME = "com.example.shabashservices";

    //    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumerhome);
        drawer = findViewById(R.id.drawer);

//        firebaseAuth=FirebaseAuth.getInstance();
//        firebaseUser=firebaseAuth.getCurrentUser();
        navigation = findViewById(R.id.navigation);
        tool = findViewById(R.id.tool);
        navigation.setNavigationItemSelectedListener(this);
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tool, R.string.OpenDrawer, R.string.CloseDrawer);
        drawer.addDrawerListener(toggle);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login", true);
        editor.apply();

        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.newhome, new home()).commit();
            navigation.setCheckedItem(R.id.home);
        }
        if (ContextCompat.checkSelfPermission(consumerhome.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        }
    }

    private void requestLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            onDeniedPermission();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else {
                onDeniedPermission();
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            loadFragment(new profileFragment(), 1);
        } else if (id == R.id.currbooking) {
            loadFragment(new CurrentBookingFragment(), 1);
        } else if (id == R.id.bookinghist) {
            loadFragment(new BookedServiceFragment(), 1);
        } else if (id == R.id.contactus) {
            loadFragment(new ContactUsFragment(), 1);
        } else if (id == R.id.faq) {
            loadFragment(new FaqFragment(), 1);
        } else if (id == R.id.rate) {

        } else if (id == R.id.logout) {
            AlertDialog.Builder logoutdialo = new AlertDialog.Builder(this);
            logoutdialo.setTitle("Logout");
            logoutdialo.setIcon(R.drawable.baseline_logout_24);
            logoutdialo.setMessage("Are you sure want to logout");
//            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);
            logoutdialo.setPositiveButton("YES", (dialogInterface, i) -> {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("flag", false);
                editor.apply();
//                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            firebaseAuth.signOut();
                Intent logout = new Intent(consumerhome.this, login.class);
                startActivity(logout);
                finish();
//                        }
//                    }
//                });
            });
            logoutdialo.setNegativeButton("NO", (dialogInterface, i) -> {

            });
            logoutdialo.show();
        } else if (id == R.id.home) {
            loadFragment(new home(), 1);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void loadFragment(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0) {
            ft.add(R.id.newhome, fragment);
            ft.commit();
        } else {
            ft.replace(R.id.newhome, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.location, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.location) {
            Intent location = new Intent(consumerhome.this, location.class);
            startActivity(location);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDeniedPermission(){
        new AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("Allow Permission")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + PACKAGE_NAME));
                        startActivity(intent);
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        navigation.setCheckedItem(R.id.home);

    }
}
