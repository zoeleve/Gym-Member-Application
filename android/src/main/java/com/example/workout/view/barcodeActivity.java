package com.example.workout.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.Athlete;
import com.example.workout.R;
import com.example.workout.Trainer;
import com.example.workout.User;

public class barcodeActivity extends AppCompatActivity {

    Athlete loggedInAthlete;
    Trainer loggedInTrainer;
    TextView remainingDays;
    //for trainer
    Button assignButton;
    Button approveButton;
    Button showRequestsButton;
    //for athlete
    Button exerciseLogButton;
    Button teamProgramButton;
    Button statsButton;

    /**
     * This is the main activity that provides the most important information.
     * Used to display the unique barcode for each user.Only for the athletes
     * it provides also additional information about their subscription.
     * Contains all the necessary buttons, to launch other most specific activities
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        init();
        //determine if we dealing with athlete or trainer
        if (getLoggedInUser() instanceof Athlete) {
            //downcasting
            loggedInAthlete = (Athlete) getLoggedInUser();
            if (loggedInAthlete.membership.getStatus()) {

                remainingDays.setText("Your membership is active!\nExpires at: " + loggedInAthlete.membership.getEndMonth() + "/" + loggedInAthlete.membership.getEndYear());
                displayBarcode();
            } else {
                remainingDays.setText("Your membership is expired!");
            }
        } else if (getLoggedInUser() instanceof Trainer) {

            displayBarcode();
            loggedInTrainer = (Trainer) getLoggedInUser();
            remainingDays.setVisibility(View.INVISIBLE);

            assignButton = findViewById(R.id.assignButton);
            approveButton = findViewById(R.id.approveButton);

            teamProgramButton.setVisibility(View.INVISIBLE);
            exerciseLogButton.setVisibility(View.INVISIBLE);

            assignButton.setVisibility(View.VISIBLE);
            approveButton.setVisibility(View.VISIBLE);
            showRequestsButton.setVisibility(View.VISIBLE);


        }
        //for athlete
        teamProgramButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(barcodeActivity.this, requestTeamProgram.class);
                intent.putExtra("athleteObj", getLoggedInUser());
                startActivity(intent);
            }
        });
        statsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(barcodeActivity.this, statsActivity.class);
                intent.putExtra("userObj", getLoggedInUser());
                startActivity(intent);
            }
        });

        exerciseLogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(barcodeActivity.this, exerciseLogActivity.class);
                intent.putExtra("athleteObj", loggedInAthlete);
                startActivity(intent);
            }
        });

        //for trainer
        approveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(barcodeActivity.this, processRequestActivity.class);
                intent.putExtra("trainerObj", getLoggedInUser());
                startActivity(intent);
            }
        });

        showRequestsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(barcodeActivity.this, showRequestsActivity.class));
            }
        });


        assignButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(barcodeActivity.this, updatePersonalScheduleActivity.class);
                intent.putExtra("trainerObj", getLoggedInUser());
                startActivity(intent);
            }
        });

        TextView welcomeUser = findViewById(R.id.wellcome);
        welcomeUser.setText("Welcome, " + getLoggedInUser().username);


    }

    private void init() {
        remainingDays = findViewById(R.id.remainingDays);

        assignButton = findViewById(R.id.assignButton);
        approveButton = findViewById(R.id.approveButton);
        showRequestsButton = findViewById(R.id.showRequestsButton);

        teamProgramButton = findViewById(R.id.teamProgramButton);
        statsButton = findViewById(R.id.statsButton);
        exerciseLogButton = findViewById(R.id.logButton);
    }

    private void displayBarcode() {
        ImageView barcode = findViewById(R.id.barcodeImageView);
        //display barcode
        barcode.setImageBitmap(getLoggedInUser().generateBarcode(getLoggedInUser().getBarcode()));
        barcode.getLayoutParams().height = 350;
        barcode.getLayoutParams().width = 350;
    }

    private User getLoggedInUser() {
        Intent i = getIntent();
        return (User) i.getSerializableExtra("loggedInUser");
    }

}
