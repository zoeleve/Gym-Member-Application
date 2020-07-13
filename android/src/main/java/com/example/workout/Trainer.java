package com.example.workout;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.workout.SignUp.Requests;

public class Trainer extends User implements Serializable {

    Trainer(String email, String username, String password, String phone, int barcode) {
        super(email, username, password, phone, barcode);
    }

    Trainer() {
    }

    public static String tempname;


   public static void approveTeamProgram(String teamProgramName, ArrayList<TeamProgram> TeamSchedule) {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getTeamProgramName().equals(teamProgramName)) {
                temp.setStatus("Approved");
            }
        }
    }

    public void declineTeamProgram(String teamProgramName, ArrayList<TeamProgram> TeamSchedule) {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getTeamProgramName().equals(teamProgramName)) {
                temp.setStatus("Declined");
            }
        }
    }

    public void updatePersonalSchedule(Athlete athlete, String exerciseDesc, String exerciseName, int exerciseReps, int exerciseWeight, String exerciseCategory) {
        Exercise exercise = new Exercise(exerciseDesc, exerciseName, exerciseReps, exerciseWeight, exerciseCategory);
        athlete.PersonalSchedule.add(exercise);
    }

    public void displayRequests() {

        for (Request temp : Requests)
            tempname = Request.getuserName();
        System.out.println(tempname);
    }

}
