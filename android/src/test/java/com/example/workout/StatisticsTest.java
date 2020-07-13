package com.example.workout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    private Statistics statisticsPerfect;
    private Statistics statisticsWithoutExercises;
    private Statistics statisticsMediocre;
    private Statistics statisticsForExceptionTest;
    private Athlete athleteWithoutExercisesDone;
    private Athlete athleteWhoDidPerfectScores;
    private Athlete athleteWithMediocreScore;
    private Athlete athleteForExceptionTest;

    @Before
    public void setUpForOneExercise() {
        Trainer trainer = new Trainer();
        athleteWhoDidPerfectScores = new Athlete();
        //trainer assigns one exercises to athlete
        trainer.updatePersonalSchedule(athleteWhoDidPerfectScores, "example desc",
                "test exercise",
                10,
                10,
                "example category");

        //athlete completes perfectly the exercise
        athleteWhoDidPerfectScores.updateExercisesLogs("example desc",
                "test exercise",
                10,
                10,
                "example category");

        statisticsPerfect = new Statistics(athleteWhoDidPerfectScores);

        ///////////////////////

        athleteWithoutExercisesDone = new Athlete();
        trainer.updatePersonalSchedule(athleteWithoutExercisesDone, "example desc",
                "test exercise 1",
                10,
                10,
                "example category");
        statisticsWithoutExercises = new Statistics(athleteWithoutExercisesDone);

        ////////////////////////

        athleteWithMediocreScore = new Athlete();
        //trainer assigns two exercises to athlete
        trainer.updatePersonalSchedule(athleteWithMediocreScore, "example desc",
                "test exercise 1",
                10,
                10,
                "example category");
        trainer.updatePersonalSchedule(athleteWithMediocreScore, "example desc",
                "test exercise 2",
                20,
                20,
                "example category");
        //athlete completes only the half of each exercise
        athleteWithMediocreScore.updateExercisesLogs("example desc",
                "test exercise 1",
                5,
                5,
                "example category");
        athleteWithMediocreScore.updateExercisesLogs("example desc",
                "test exercise 2",
                10,
                10,
                "example category");
        statisticsMediocre = new Statistics(athleteWithMediocreScore);

        ////////////////////////

        athleteForExceptionTest = new Athlete();
        athleteForExceptionTest.updateExercisesLogs("example desc",
                "test exercise 1",
                10,
                10,
                "example category");


    }

    //this time we will try to try to export statistics without any exercises assigned by any trainer
    //but logged from athlete in ExerciseLog array list
    @Test
    public void ExceptionTest() {
        statisticsForExceptionTest = new Statistics(athleteForExceptionTest);
        statisticsForExceptionTest.addResults();
    }



    @Test
    public void StatisticTest() {
        statisticsPerfect.addResults();
        Assert.assertEquals(100, athleteWhoDidPerfectScores.Progress.get(0), 1e-15);

        statisticsWithoutExercises.addResults();
        Assert.assertEquals(0, athleteWithoutExercisesDone.Progress.get(0), 1e-15);

        statisticsMediocre.addResults();
        Assert.assertEquals(50, athleteWithMediocreScore.Progress.get(0), 1e-15);
    }
}
