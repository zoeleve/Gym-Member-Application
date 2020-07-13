package com.example.workout.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout.LogIn;
import com.example.workout.R;

import java.util.Objects;

public class loginActivity<MainActivity> extends Activity {

    private Button login;
    private EditText username;
    private EditText password;
    private TextView register;
    private String user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String pass;
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("")) {
                    Toast.makeText(loginActivity.this, "Username Required", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(loginActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    //Authentication
                    if (LogIn.findUser(user) != null) {
                        if (LogIn.authenticate(Objects.requireNonNull(LogIn.findUser(user)), pass)) {
                            Toast.makeText(loginActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                            barcodeActivity();
                        } else {
                            Toast.makeText(loginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(loginActivity.this, "Incorrect username", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this, registerActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void barcodeActivity() {
        Intent intent = new Intent(this, barcodeActivity.class);
        intent.putExtra("loggedInUser", LogIn.findUser(user));
        startActivity(intent);
    }
}

