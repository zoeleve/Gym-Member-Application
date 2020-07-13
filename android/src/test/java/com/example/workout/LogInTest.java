package com.example.workout;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LogInTest {


    @Before
    public void setUp() {
        String givenPhone = "6945667501";            //we wil take those vars from Sign In UI at the next assignment
        String givenUsername = "Spyros";
        String givenPassword = "1234";
        String givenEmail = "baziosspyros@gmail.com";
        int givenAge = 20;
        int givenWeight = 70;

        SignUp.createNewAthlete(givenEmail, givenUsername, givenPassword, givenPhone, givenWeight, givenAge);
    }

    @Test
    public void findUserTest() {
        Athlete athleteFromSearch = (Athlete) LogIn.findUser("Spyros");
        assertEquals(athleteFromSearch, SignUp.userList.get(1));

        assertNull(LogIn.findUser("Manos"));
    }

    @Test
    public void authenticateTest() {

        assertFalse(LogIn.authenticate(SignUp.userList.get(2), "12345"));
        assertTrue(LogIn.authenticate(SignUp.userList.get(2), "1234"));
    }


}