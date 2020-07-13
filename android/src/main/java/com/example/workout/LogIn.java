package com.example.workout;

public class LogIn {

    public static User findUser(String givenUsername) {
        for (User user : SignUp.userList) {
            if (user.getUsername().equals(givenUsername)) {
                return user;
            }
        }
        return null;
    }

    public static boolean authenticate(User user, String password) {
        return user.getPassword().equals(password);
    }
}



