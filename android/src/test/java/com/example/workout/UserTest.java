package com.example.workout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private Athlete athlete;
    private User user;

    @Before
    public void setUp() {
        Trainer trainer = new Trainer();
        athlete = new Athlete("test", "test", "test", "test", 1234, 1234, 1234);
        SignUp.userList.add(athlete);
        //trainer assigns one exercises to athlete
        trainer.updatePersonalSchedule(athlete, "example desc",
                "test exercise",
                10,
                10,
                "example category");

        //athlete completes perfectly the exercise
        athlete.updateExercisesLogs("example desc",
                "test exercise",
                10,
                10,
                "example category");

        user = new User();

    }

    @Test
    public void exportStatisticsTest() {
        athlete.exportStatistics("test");
        Assert.assertEquals(athlete.Progress.get(0), 100, 1e-15);

    }
}