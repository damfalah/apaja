package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BilanganActivity extends AppCompatActivity {

    Button proses;
    EditText angka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilangan);

        proses = findViewById(R.id.proses);
        angka = findViewById(R.id.angka);

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = angka.getText().toString();
                if (input.matches("\\d+")) {
                    int nilai = Integer.parseInt(input);
                    String jenisBilangan = (nilai % 2 == 0) ? "Genap" : "Ganjil";
                    showPopup(jenisBilangan);
                } else {
                    showPopup("Tolong masukkan angka");
                }
            }
        });
    }

    private void showPopup(String message) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        TextView txtPopup = popupView.findViewById(R.id.txtPopup);
        Button btnDismiss = popupView.findViewById(R.id.btnDismiss);

        txtPopup.setText(message);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
