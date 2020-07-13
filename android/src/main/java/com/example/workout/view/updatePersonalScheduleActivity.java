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
import com.example.workout.LogIn;
import com.example.workout.R;
import com.example.workout.Trainer;

/**
 * With this activity a Trainer will be able to add an exercise to an
 * athlete's personal schedule.After every information needed for the exercise
 * is filled ,the trainer can add the exercise to a schedule.
 */

public class updatePersonalScheduleActivity extends AppCompatActivity {
    String thisAthlete;
    String thisExerciseName;
    String thisCategory;
    String thisExerciseDescription;
    private int thisReps;
    private int thisWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_schedule);


        //Reps seek bar implementation
        final TextView reptext = findViewById(R.id.repsText);
        final SeekBar seekReps = findViewById(R.id.seekReps);

        seekReps.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                thisReps = progress;
                reptext.setText("Reps to do: " + progress);
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
                thisWeight = progress;
                weightText.setText("Weight to use: " + progress);
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
        thisExerciseName = loggedExerciseEditText.getText().toString();



        //Exercise Category logging implementation
        final EditText loggedCategory = findViewById(R.id.acat);
        thisCategory = loggedCategory.getText().toString();

        //Exercise Description logging implementation
        final EditText loggedDescription = findViewById(R.id.adesc);
        thisExerciseDescription = loggedDescription.getText().toString();


        //add button implementation
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Athlete Name logging implementation
                final EditText loggedAthleteName = findViewById(R.id.aname);
                thisAthlete = loggedAthleteName.getText().toString();
                if (LogIn.findUser(thisAthlete) != null) {
                    getLoggedInTrainer().updatePersonalSchedule((Athlete) LogIn.findUser(thisAthlete), thisExerciseDescription, thisExerciseName,
                            thisReps, thisWeight, thisCategory);
                    seekReps.setProgress(0);
                    seekWeight.setProgress(0);
                    loggedExerciseEditText.setText("");
                    loggedAthleteName.setText("");
                    loggedCategory.setText("");
                    loggedDescription.setText("");
                    Toast.makeText(updatePersonalScheduleActivity.this, "Exercise Added",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(updatePersonalScheduleActivity.this, "Invalid user name",
                            Toast.LENGTH_LONG).show();
                    loggedAthleteName.setText("");
                }


            }
        });
    }

    public Trainer getLoggedInTrainer() {
        Intent i = getIntent();
        return (Trainer) i.getSerializableExtra("trainerObj");
    }

}
