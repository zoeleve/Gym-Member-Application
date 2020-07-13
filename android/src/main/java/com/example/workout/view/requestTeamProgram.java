package com.example.workout.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.R;

public class requestTeamProgram extends AppCompatActivity {

    Button applyButton;
    EditText givenProgram;
    TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_team_program);
        init();
        checkForApprovedPrograms();

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoggedInAthlete().validTeamProgram(givenProgram.getText().toString().trim())) {
                    getLoggedInAthlete().RequestTeamProgram(givenProgram.getText().toString().trim());
                    Toast.makeText(requestTeamProgram.this, "Successfully applied for, " + givenProgram.getText().toString().trim(),
                            Toast.LENGTH_LONG).show();
                } else {
                    givenProgram.setText("");
                    Toast.makeText(requestTeamProgram.this, "Invalid Team Program Name",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void init() {
        applyButton = findViewById(R.id.applyButton);
        givenProgram = findViewById(R.id.programNameEditText);
        statusTextView = findViewById(R.id.statusText);
    }

    private void checkForApprovedPrograms() {
        if (getLoggedInAthlete().displayApproved() != null) {
            statusTextView.setText("Already approved for " + getLoggedInAthlete().displayApproved() + " ");
        } else {
            statusTextView.setText("No programms approved yet");
        }
    }

    private Athlete getLoggedInAthlete() {
        Intent i = getIntent();
        return (Athlete) i.getSerializableExtra("athleteObj");
    }
}
