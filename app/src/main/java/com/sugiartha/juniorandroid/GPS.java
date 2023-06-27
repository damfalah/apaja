package com.sugiartha.juniorandroid;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GPS extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private LottieAnimationView gpsAnimation;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
        gpsAnimation = findViewById(R.id.gpsAnimation);

        Button button = findViewById(R.id.getLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadingAnimation();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ActivityCompat.checkSelfPermission(GPS.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            System.out.println("Cek Permission");
                            stopLoadingAnimation();
                            return;
                        }
                        client.getLastLocation().addOnSuccessListener(GPS.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                stopLoadingAnimation();
                                if (location != null) {
                                    showLocationPopup(location);
                                }
                            }
                        });
                    }
                }, 3000);
            }
        });
    }

        private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
        System.out.println("Cek Request Permission");
    }

    private void startLoadingAnimation() {
        gpsAnimation.setVisibility(View.VISIBLE);
        gpsAnimation.playAnimation();
        gpsAnimation.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopLoadingAnimation();
            }
        }, 3000);
    }

    private void stopLoadingAnimation() {
        gpsAnimation.cancelAnimation();
        gpsAnimation.setVisibility(View.INVISIBLE);
    }

    private void showLocationPopup(Location location) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        TextView txtPopup = popupView.findViewById(R.id.txtPopup);
        Button btnDismiss = popupView.findViewById(R.id.btnDismiss);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String city = address.getLocality();
                String country = address.getCountryName();
                String formattedLocation = String.format("Latitude: %.6f\nLongitude: %.6f\nCity: %s\nCountry: %s", latitude, longitude, city, country);
                txtPopup.setText(formattedLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setView(popupView);

        final androidx.appcompat.app.AlertDialog gpsDialog = builder.create();

        gpsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                gpsDialog.getWindow();
            }
        });

        gpsDialog.show();

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsDialog.dismiss();
            }
        });
    }
}