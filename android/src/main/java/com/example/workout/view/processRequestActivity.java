package com.example.workout.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.LogIn;
import com.example.workout.R;
import com.example.workout.Trainer;

/**
 * With processRequestActivity a trainer will be able to review a pending
 * request from an athlete to join a Team Program.He will need to add the name
 * of the athlete and his desired Team Program and will be given the options to accept it or deny it.
 */

public class processRequestActivity extends AppCompatActivity {


    EditText athleteToApprove;
    EditText programToApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_request);

        athleteToApprove = findViewById(R.id.athleteName);
        programToApprove = findViewById(R.id.teamProgram);

        //approve button implementation
        Button approveButton = findViewById(R.id.approveButton);
        approveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LogIn.findUser(athleteToApprove.getText().toString()) != null) {
                    getLoggedInTrainer().approveTeamProgram(programToApprove.getText().toString(), ((Athlete) LogIn.findUser(athleteToApprove.getText().toString())).TeamSchedule);
                    athleteToApprove.setText("");
                    programToApprove.setText("");
                    Toast.makeText(processRequestActivity.this, "Approved!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(processRequestActivity.this, "Invalid user name",
                            Toast.LENGTH_LONG).show();
                    athleteToApprove.setText("");
                }
            }
        });

        //Decline button implementation
        Button declineButton = findViewById(R.id.declineButton);
        declineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LogIn.findUser(athleteToApprove.getText().toString()) != null) {
                    getLoggedInTrainer().declineTeamProgram(programToApprove.getText().toString(), ((Athlete) LogIn.findUser(athleteToApprove.getText().toString())).TeamSchedule);
                    athleteToApprove.setText("");
                    programToApprove.setText("");
                    Toast.makeText(processRequestActivity.this, "Declined!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(processRequestActivity.this, "Invalid user name",
                            Toast.LENGTH_LONG).show();
                    athleteToApprove.setText("");
                }
            }

        });
    }

    public Trainer getLoggedInTrainer() {
        Intent i = getIntent();
        return (Trainer) i.getSerializableExtra("trainerObj");
    }
}