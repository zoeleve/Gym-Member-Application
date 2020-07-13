package com.example.workout;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignUpTest {
    private String givenPhone = "6945667501";            //we wil take those vars from Sign In UI at the next assignment
    private String givenUsername = "Spyros";
    private String givenPassword = "1234";
    private String givenEmail = "baziosspyros@gmail.com";
    private int givenAge = 20;
    private int givenWeight = 70;

    @Test
    public void createTest() {
        SignUp.createNewAthlete(givenEmail, givenUsername, givenPassword, givenPhone, givenWeight, givenAge);

        assertEquals(SignUp.userList.get(1).getEmail(), givenEmail);
        assertEquals(SignUp.userList.get(1).getUsername(), givenUsername);
        assertEquals(SignUp.userList.get(1).getPassword(), givenPassword);
        assertEquals(SignUp.userList.get(1).getPhone(), givenPhone);
        assertEquals(((Athlete) SignUp.userList.get(1)).getWeight(), givenWeight);
        assertEquals(((Athlete) SignUp.userList.get(1)).getAge(), givenAge);

        SignUp.createNewTrainer(givenEmail, givenUsername, givenPassword, givenPhone);

        assertEquals(SignUp.userList.get(2).getEmail(), givenEmail);
        assertEquals(SignUp.userList.get(2).getUsername(), givenUsername);
        assertEquals(SignUp.userList.get(2).getPassword(), givenPassword);
        assertEquals(SignUp.userList.get(2).getPhone(), givenPhone);
    }

}