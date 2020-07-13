package com.example.workout.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.LogIn;
import com.example.workout.R;
import com.example.workout.Statistics;
import com.example.workout.User;

/**
 * This activity is used to display statistics to either trainer or athlete,
 * (if you are log in as a trainer you can  search and monitor the
 * progress of any athlete via the search bar. Every athlete has access
 * only to his statistics.
 */
public class statsActivity extends AppCompatActivity {
    Athlete currentAthlete;
    Button searchButton;
    EditText athleteName;
    TextView result;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        init();

        if (getLoggedInUser() instanceof Athlete) {
            searchButton.setVisibility(View.INVISIBLE);
            athleteName.setVisibility(View.INVISIBLE);
            currentAthlete = (Athlete) getLoggedInUser();
            Statistics statistics = new Statistics(currentAthlete);
            statistics.addResults();
            result.setText(currentAthlete.Progress.get(0).toString());
        } else {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LogIn.findUser(athleteName.getText().toString()) != null) {
                        currentAthlete = (Athlete) LogIn.findUser(athleteName.getText().toString());
                        Statistics statistics = new Statistics(currentAthlete);
                        statistics.addResults();
                        result.setText(currentAthlete.Progress.get(0).toString());
                    } else {
                        Toast.makeText(statsActivity.this, "Enter a valid username!", Toast.LENGTH_SHORT).show();
                        result.setText("");
                    }

                }
            });
        }
    }

    public User getLoggedInUser() {
        Intent i = getIntent();
        return (User) i.getSerializableExtra("userObj");
    }

    public void init() {
        searchButton = findViewById(R.id.searchButton);
        athleteName = findViewById(R.id.athleteName);
        result = findViewById(R.id.result
        );

    }
}
