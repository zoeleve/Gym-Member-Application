package com.example.workout;

class Request {
    static private String userName;
    static private String teamProgramName;

    Request(String userName, String teamProgramName) {
        this.teamProgramName = teamProgramName;
        this.userName = userName;
    }

    static String getuserName() {
        return userName;
    }
    static String getTeamProgramName() { return teamProgramName;}
}
