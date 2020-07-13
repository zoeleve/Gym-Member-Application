package com.example.workout;
import org.junit.Before;
import org.junit.Test;

import static com.example.workout.Trainer.tempname;
import static org.junit.Assert.*;

public class TrainerTest {
    Athlete athlete = new Athlete("sdfsds@gmail","sfsdf", "dsfsd", "fdsfs", 12, 12, 12 );
    private String givenExerciseDesc="psila ta podia";
    private int givenExerciseReps=20;
    private int givenExerciseWeight=0;
    private String givenExerciseName="keraki";
    private String givenExerciseCategory="podia";
    Trainer trainer = new Trainer("manos.skan@tempmail.com","SKOW","liklik69","69696969696969",420);


    @Test
    public void updatePersonalScheduleTest() {
        trainer.updatePersonalSchedule(athlete, givenExerciseDesc, givenExerciseName, givenExerciseReps,givenExerciseWeight, givenExerciseCategory);

        assertEquals(athlete.PersonalSchedule.get(0).getExerciseDesc(), givenExerciseDesc);
        assertEquals(athlete.PersonalSchedule.get(0).getExerciseName(), givenExerciseName);
        assertEquals(athlete.PersonalSchedule.get(0).getExerciseReps(), givenExerciseReps);
        assertEquals(athlete.PersonalSchedule.get(0).getExerciseWeight(), givenExerciseWeight);
        assertEquals(( athlete.PersonalSchedule.get(0)).getExerciseCategory(), givenExerciseCategory);
    }

    @Test
    public void ApproveTeamProgramTest(){
        trainer.approveTeamProgram("TRX", athlete.TeamSchedule);
        assertEquals(athlete.TeamSchedule.get(0).getStatus(), "Approved");
    }

    @Test
    public void DeclineTeamProgramTest(){
        trainer.declineTeamProgram("TRX", athlete.TeamSchedule);
        assertEquals(athlete.TeamSchedule.get(0).getStatus(), "Declined");
    }

    @Test
    public void displayRequestsTest(){
        athlete.RequestTeamProgram("TRX");
        trainer.displayRequests();
        assertEquals(tempname, "sfsdf");
    }


}


