package com.example.workout.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.R;

/**
 * exerciseLogActivity's main purpose is to make easy for each athlete
 * to keep track of any exercise he does in the gym in order to monitor his
 * progress afterwards with statistics.
 */
public class exerciseLogActivity extends AppCompatActivity {

    private int loggedReps;
    private int loggedWeight;
    private String loggedExerciseName;
    private Athlete loggedInAthlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_log);

        loggedInAthlete = getLoggedInAthlete();

        //Reps seek bar implementation
        final TextView reptext = findViewById(R.id.repsText);
        final SeekBar seekReps = findViewById(R.id.seekReps);
        seekReps.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loggedReps = progress;
                reptext.setText("Reps done: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //Weight seek bar implementation
        final TextView weightText = findViewById(R.id.weightText);
        final SeekBar seekWeight = findViewById(R.id.seekWeight);
        seekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loggedWeight = progress;
                weightText.setText("Weight used: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Exercise Name logging implementation
        final EditText loggedExerciseEditText = findViewById(R.id.ExerciseName);
        loggedExerciseName = loggedExerciseEditText.getText().toString();

        //add button implementation
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //currentAthlete.UpdateExerciseLogs(loggedWeight, loggedReps, loggedExerciseName);
                seekReps.setProgress(0);
                seekWeight.setProgress(0);
                loggedExerciseEditText.setText("");
                //    void updateExercisesLogs(String exerciseDesc, String exerciseName, int exerciseReps, int exerciseWeight, String exerciseCategory) {
                //loggedInAthlete.updateExercisesLogs();

                Toast.makeText(exerciseLogActivity.this, "Exercise Added",
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    public Athlete getLoggedInAthlete() {
        Intent i = getIntent();
        return (Athlete) i.getSerializableExtra("athleteObj");
    }
}
