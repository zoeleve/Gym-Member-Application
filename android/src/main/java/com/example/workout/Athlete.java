package com.example.workout;

import java.io.Serializable;
import java.time.Clock;
import java.util.ArrayList;

import static com.example.workout.SignUp.Requests;

public class Athlete extends User implements Serializable {

    private int age;
    private int weight;
    public Membership membership;

    ArrayList<String> visitLogs = new ArrayList<String>();

    ArrayList<Exercise> PersonalSchedule = new ArrayList<Exercise>();

    public ArrayList<Double> Progress = new ArrayList<Double>();

    public ArrayList<TeamProgram> TeamSchedule = new ArrayList<TeamProgram>();

    ArrayList<Exercise> exercisesLogs = new ArrayList<Exercise>();


    private void InitializeTS() {
        TeamSchedule.add(new TeamProgram("TRX", "Declined"));
        TeamSchedule.add(new TeamProgram("YOGA", "Declined"));
        TeamSchedule.add(new TeamProgram("KICK BOXING", "Declined"));
        TeamSchedule.add(new TeamProgram("PILATES", "Declined"));
        TeamSchedule.add(new TeamProgram("ZUMBA", "Declined"));
        TeamSchedule.add(new TeamProgram("AEROBIC", "Declined"));
        TeamSchedule.add(new TeamProgram("POWER PUMP", "Declined"));
        TeamSchedule.add(new TeamProgram("BIKING CARDIO", "Declined"));
    }

    Athlete() {
        InitializeTS();
    }

    Athlete(String email, String username, String password, String phone, int barcode, int weight, int age) {
        super(email, username, password, phone, barcode);
        this.weight = weight;
        this.age = age;
        InitializeTS();
    }

    int getAge() {
        return age;
    }

    int getWeight() {
        return weight;
    }

    void updateVisit(Clock clock) {
        String timeStamp = clock.instant().toString();
        visitLogs.add(timeStamp);
    }

    public void buyMembership(int duration) {     //duration in months e.g. duration of 1 year = 12
        membership = new Membership(duration, Clock.systemUTC());
    }

    public void updateExercisesLogs(String exerciseDesc, String exerciseName, int exerciseReps, int exerciseWeight, String exerciseCategory) {
        Exercise Log = new Exercise(exerciseDesc, exerciseName, exerciseReps, exerciseWeight, exerciseCategory);
        exercisesLogs.add(Log);
    }


    public void RequestTeamProgram(String teamProgramName) {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getTeamProgramName().equals(teamProgramName)) {
                temp.setStatus("Pending");
                Request req = new Request(this.username, teamProgramName);
                Requests.add(req);
            }
        }
    }

    public String displayApproved() {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getStatus().equals("Approved")) {
                return temp.getTeamProgramName();
            }
        }
        return null;
    }

    public boolean validTeamProgram(String givenName) {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getTeamProgramName().equals(givenName)) {
                return true;
            }
        }
        return false;
    }





   public boolean HasRequest() {
        for (TeamProgram temp : TeamSchedule) {
            if (temp.getStatus().equals("Pending")) {
                return true;
            }
        }
        return false;
    }

}
