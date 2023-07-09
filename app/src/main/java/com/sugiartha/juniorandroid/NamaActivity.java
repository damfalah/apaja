package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NamaActivity extends AppCompatActivity {

    Button btnOk;
    EditText editNama, editEmail, editNoHP;

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

        LayoutInflater inflater = LayoutInflater.from(this);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        TextView txtPopup = popupView.findViewById(R.id.txtPopup);
        Button btnDismiss = popupView.findViewById(R.id.btnDismiss);

        txtPopup.setText("Halo " + nama + "!\nAnda telah terdaftar sebagai peserta VSGA.\nEmail: " + email + "\nNo HP: " + noHP);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getWindow();
            }
        });

        alertDialog.show();

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
