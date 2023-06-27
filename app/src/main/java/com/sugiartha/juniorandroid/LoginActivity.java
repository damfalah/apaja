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

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    edtUsername.setError("Username tidak boleh kosong");
                    edtUsername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    edtPassword.setError("Password tidak boleh kosong");
                    edtPassword.requestFocus();
                    return;
                }

                LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
                View popupView = inflater.inflate(R.layout.popup_layout, null);

                TextView txtPopup = popupView.findViewById(R.id.txtPopup);
                Button btnDismiss = popupView.findViewById(R.id.btnDismiss);

                txtPopup.setText("Login Berhasil!");

                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
