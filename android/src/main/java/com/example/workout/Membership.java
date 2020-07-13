package com.example.workout;

import java.io.Serializable;
import java.time.Clock;

public class Membership implements Serializable {

    private int endMonth;
    private int endYear;
    private int duration;    //duration of membership(e.g. 12month membership)
    private Boolean status = false;

    //constructor
    Membership(int duration, Clock clock) {
        this.clock = clock;
        this.duration = duration;
        this.status = true;
        calcMembershipDuration();
    }

    private Clock clock;
    private int[] tempArrayInt = {0, 0, 0, 0};

    void setClock(Clock clock) {
        this.clock = clock;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getDuration() {
        return duration;
    }

    public Boolean getStatus() {
        return status;
    }

    private void getCurrentDate() {
        String timeStamp = clock.instant().toString();
        timeStamp = timeStamp.replaceAll("-", "T");
        //temp Array to store splitted date format
        // tempArrayInt[2] = days, tempArray[1] = months, tempArray[0] = years
        String[] tempArrayString = timeStamp.split("T", 4);       //we ignore tempArrayString[4] contains hour

        //Converting String Array to Int in order to use it for calculations
        tempArrayInt[0] = Integer.parseInt(tempArrayString[0]);
        tempArrayInt[1] = Integer.parseInt(tempArrayString[1]);
        tempArrayInt[2] = Integer.parseInt(tempArrayString[2]);

    }

    void calcMembershipDuration() {
        getCurrentDate();

        //used to calc months
        int sum = duration + tempArrayInt[1];
        while (sum >= 12) {
            sum -= 12;
            tempArrayInt[0] += 1;
        }
        tempArrayInt[1] = sum;
        endMonth = tempArrayInt[1];
        endYear = tempArrayInt[0];
    }

    void checkStatus() {
        getCurrentDate();
        //else if current year > ending year
        if (tempArrayInt[1] > endMonth && tempArrayInt[0] == endYear) {    //if current month > month of membership ending and year remains the same
            status = false;
        } else status = tempArrayInt[0] <= endYear;
    }
}




