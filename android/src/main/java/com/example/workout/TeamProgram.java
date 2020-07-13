package com.example.workout;

import java.io.Serializable;

class TeamProgram implements Serializable {

    private String teamProgramName;
    private String status;

    TeamProgram(String teamProgramName, String status) {
        this.status = status;
        this.teamProgramName = teamProgramName;
    }

    String getTeamProgramName() {
        return teamProgramName;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }
}
