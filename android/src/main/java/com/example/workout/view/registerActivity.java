package com.example.workout.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.LogIn;
import com.example.workout.R;
import com.example.workout.SignUp;

public class registerActivity extends AppCompatActivity {

    private Button confirm;
    private Switch userSwitch;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText phone;
    private EditText age;
    private EditText weight;
    private TextView membershipTextView;
    private int givenMembershipDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        password = findViewById(R.id.password);
        membershipTextView = findViewById(R.id.membershipTextView);

        final SeekBar membershipSeekBar = findViewById(R.id.seekBar);
        membershipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                givenMembershipDuration = progress;
                TextView membText = findViewById(R.id.membershipTextView);
                membText.setText("Membership Duration: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        userSwitch = findViewById(R.id.userswitch);
        userSwitch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (userSwitch.isChecked()) {
                    userSwitch.setText("Trainer");
                    age.setVisibility(View.INVISIBLE);
                    weight.setVisibility(View.INVISIBLE);
                    membershipTextView.setVisibility(View.INVISIBLE);
                    membershipSeekBar.setVisibility(View.INVISIBLE);
                } else {
                    userSwitch.setText("Athlete");
                    age.setVisibility(View.VISIBLE);
                    weight.setVisibility(View.VISIBLE);
                }
            }
        });

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userSwitch.isChecked()) {
                    //trainer

                    //check email validation
                    boolean emailVal = emailValidation();
                    //check password validation
                    boolean passVal = passwordValidation();
                    //check phone validation
                    boolean phoneVal = phoneValidation();
                    if(emailVal==true && passVal==true && phoneVal==true){
                        SignUp.createNewTrainer(email.getText().toString(), name.getText().toString(), password.getText().toString(), phone.getText().toString());
                        loginActivity();
                    }

                } else {
                    //athlete

                    //check email validation
                    boolean emailVal = emailValidation();
                    //check password validation
                    boolean passVal = passwordValidation();
                    //check phone validation
                    boolean phoneVal = phoneValidation();
                    if(emailVal==true && passVal==true && phoneVal==true) {
                        SignUp.createNewAthlete(email.getText().toString(), name.getText().toString(), password.getText().toString(), phone.getText().toString(), Integer.parseInt(weight.getText().toString()), Integer.parseInt(age.getText().toString()));
                        Athlete athleteForCasting = (Athlete) LogIn.findUser(name.getText().toString());
                        athleteForCasting.buyMembership(givenMembershipDuration);
                        loginActivity();
                    }
                }

            }
        });
    }
    public boolean emailValidation(){
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return true;
        }else{
            Toast.makeText(registerActivity.this, "Incorrect e-mail", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean passwordValidation(){
        String passwordInput = password.getText().toString();
        if (passwordInput.length() < 6 ) {
            Toast.makeText(registerActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
    public boolean phoneValidation(){
        String phoneInput = phone.getText().toString();
        if (phoneInput.length() != 10 ) {
            Toast.makeText(registerActivity.this, "Incorrect phone", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
    public void loginActivity(){
        Intent i = new Intent(registerActivity.this, loginActivity.class);
        startActivity(i);
    }
}
