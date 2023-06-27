package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText editNama, editUsername, editPassword;
    Spinner spinnerGender;
    Button signUpButton;

    String[] gender = { "Laki-Laki", "Perempuan" };
    String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editNama = findViewById(R.id.editNama);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        spinnerGender = findViewById(R.id.spinnerGender);
        signUpButton = findViewById(R.id.signUpButton);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        spinnerGender.setAdapter(spinnerAdapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = gender[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedGender = null;
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editNama.getText().toString().trim();
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (TextUtils.isEmpty(nama)) {
                    editNama.setError("Nama tidak boleh kosong");
                    editNama.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    editUsername.setError("Username tidak boleh kosong");
                    editUsername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    editPassword.setError("Password tidak boleh kosong");
                    editPassword.requestFocus();
                    return;
                }

                if (selectedGender == null) {
                    Toast.makeText(SignupActivity.this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show();
                    return;
                }

                LayoutInflater inflater = LayoutInflater.from(SignupActivity.this);
                View popupView = inflater.inflate(R.layout.popup_layout, null);

                TextView txtPopup = popupView.findViewById(R.id.txtPopup);
                Button btnDismiss = popupView.findViewById(R.id.btnDismiss);

                txtPopup.setText("Sign Up Berhasil!");

                final AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
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
        });
    }
}
