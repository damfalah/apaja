package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class KoneksiActivity extends AppCompatActivity {

    private TextView txtKoneksi;
    private LottieAnimationView koneksiAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koneksi);

        txtKoneksi = findViewById(R.id.txtkoneksi);
        koneksiAnimation = findViewById(R.id.koneksiAnimation);
        Button btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetConnection();
            }
        });

        startAnimation();
    }

    private void startAnimation() {
        koneksiAnimation.setAnimation(R.raw.network_animation);
        koneksiAnimation.playAnimation();
    }

    private void checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String connectionType = networkInfo.getTypeName() + " " + networkInfo.getSubtypeName();
            Toast.makeText(getApplicationContext(), "You are connected to " + connectionType, Toast.LENGTH_SHORT).show();
            txtKoneksi.setText("Connected to " + connectionType);
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            alert.setCancelable(false);
            alert.show();
            txtKoneksi.setText("No Internet Connection");
        }
    }
}
