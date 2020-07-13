package com.example.workout;

import java.util.ArrayList;
import java.util.Random;

public class SignUp {

    static ArrayList<User> userList = new ArrayList<User>();
   public static ArrayList<Request> Requests = new ArrayList<Request>();

    public static void createNewAthlete(String givenEmail, String givenUsername, String givenPassword, String givenPhone, int givenWeight, int givenAge) {
        User user = new Athlete(givenEmail, givenUsername, givenPassword, givenPhone, generateUniqueRandom(userList), givenWeight, givenAge);
        userList.add(user);
    }

    public static void createNewTrainer(String givenEmail, String givenUsername, String givenPassword, String givenPhone) {
        User user = new Trainer(givenEmail, givenUsername, givenPassword, givenPhone, generateUniqueRandom(userList));
        userList.add(user);

    }

    private static int generateUniqueRandom(ArrayList<User> uniqueIn) {
        int random = generateRandomIntIntRange(1, 1000);
        int i = 0;
        if (!userList.isEmpty()) {
            while (uniqueIn.get(i).getBarcode() == random) {
                random = generateRandomIntIntRange(1, 1000);
                i++;
            }
        }
        return random;
    }

    //used to generate barcode
    private static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
