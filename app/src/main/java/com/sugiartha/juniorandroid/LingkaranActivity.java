package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LingkaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingkaran);

        TextInputEditText jari2EditText = findViewById(R.id.jari2Layout).findViewById(R.id.jari2);
        Button hitungButton = findViewById(R.id.hitung);
        TextView kelilingTextView = findViewById(R.id.keliling);
        TextView luasTextView = findViewById(R.id.luas);

        hitungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double jari = Double.parseDouble(jari2EditText.getText().toString());
                double phi = 3.14;
                double dblKeliling = 2 * phi * jari;
                double dblLuas = phi * jari * jari;

                kelilingTextView.setText(Double.toString(dblKeliling));
                luasTextView.setText(Double.toString(dblLuas));
            }
        });
    }
}
