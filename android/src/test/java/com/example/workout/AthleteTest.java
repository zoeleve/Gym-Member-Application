package com.example.workout;

import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AthleteTest {
    private Athlete athlete = new Athlete();

    @Test
    public void updateExercisesLogsTest() {
        String givenExerciseDesc = "psila ta podia";
        int givenExerciseReps = 20;
        int givenExerciseWeight = 0;
        String givenExerciseName = "keraki";
        String givenExerciseCategory = "podia";
        athlete.updateExercisesLogs(givenExerciseDesc, givenExerciseName, givenExerciseReps, givenExerciseWeight, givenExerciseCategory);

        assertEquals(athlete.exercisesLogs.get(0).getExerciseDesc(), givenExerciseDesc);
        assertEquals(athlete.exercisesLogs.get(0).getExerciseName(), givenExerciseName);
        assertEquals(athlete.exercisesLogs.get(0).getExerciseReps(), givenExerciseReps);
        assertEquals(athlete.exercisesLogs.get(0).getExerciseWeight(), givenExerciseWeight);
        assertEquals(( athlete.exercisesLogs.get(0)).getExerciseCategory(), givenExerciseCategory);
    }

    @Test
    public void buyMembershipTest() {
        athlete.buyMembership(6);
        assertEquals(athlete.membership.getDuration(), 6);
    }

    @Test
    public void updateVisit() {
        Clock clockForTest = Clock.fixed(LocalDateTime.of(2017, 2, 15, 12, 00)
                .toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        Athlete athlete = new Athlete();
        athlete.updateVisit(clockForTest);
        assertEquals(athlete.visitLogs.get(0), "2017-02-15T12:00:00Z");
    }

    @Test
    public void RequestTeamProgramTest(){
        athlete.RequestTeamProgram("TRX");
        assertEquals(athlete.TeamSchedule.get(0).getStatus(), "Pending");
    }

    @Test
    public void HasntRequest() {
        assertFalse(athlete.HasRequest());
    }

    @Test
    public void HasRequest(){
        athlete.RequestTeamProgram("TRX");
        assertTrue(athlete.HasRequest());
    }
}