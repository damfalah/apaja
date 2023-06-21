package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class NamaActivity extends AppCompatActivity {

    Button btnOk;
    EditText editNama, editEmail, editNoHP;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama);

        btnOk = findViewById(R.id.btnOk);
        editNama = findViewById(R.id.editNama);
        editEmail = findViewById(R.id.editEmail);
        editNoHP = findViewById(R.id.editNoHP);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
    }

    private void showPopup() {
        String nama = editNama.getText().toString();
        String email = editEmail.getText().toString();
        String noHP = editNoHP.getText().toString();

        // Memeriksa input yang kosong atau kesalahan input
        if (nama.isEmpty()) {
            editNama.setError("Masukkan Nama Anda");
            editNama.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Masukkan Email Anda");
            editEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Email tidak valid");
            editEmail.requestFocus();
            return;
        }

        if (noHP.isEmpty()) {
            editNoHP.setError("Masukkan No HP Anda");
            editNoHP.requestFocus();
            return;
        }

        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView txtPopup = popupView.findViewById(R.id.txtPopup);
        txtPopup.setText("Halo " + nama + "!\nAnda telah terdaftar sebagai peserta VSGA.\nEmail: " + email + "\nNo HP: " + noHP);

        Button btnDismiss = popupView.findViewById(R.id.btnDismiss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

}
