package com.example.workout;

public class Statistics {

    private Athlete currentAthlete;
    private double sumOfWeightPercentage;
    private double sumOfRepPercentage;

    public Statistics(Athlete currentAthlete) {
        this.currentAthlete = currentAthlete;
    }

    private void resultsForWeek() {
        Exercise exerciseDone;
        //setting optimal percentage (if everything get followed perfectly )
        sumOfWeightPercentage = currentAthlete.PersonalSchedule.size() * 100;
        sumOfRepPercentage = currentAthlete.PersonalSchedule.size() * 100;
        for (Exercise exerciseAssigned : currentAthlete.PersonalSchedule) {
            exerciseDone = searchFor(exerciseAssigned);
            if (exerciseDone != null) {
                sumOfWeightPercentage -= 100 - (double) exerciseDone.exerciseWeight * 100 / exerciseAssigned.exerciseWeight;
                sumOfRepPercentage -= 100 - (double) exerciseDone.exerciseReps * 100 / exerciseAssigned.exerciseReps;
            } else {
                sumOfWeightPercentage -= 100;
                sumOfRepPercentage -= 100;
            }
        }
    }

    private Exercise searchFor(Exercise exercise) {
        for (Exercise exerciseDone : currentAthlete.exercisesLogs)
            if (exerciseDone.exerciseName.equals(exercise.exerciseName)) {
                return exerciseDone;
            }
        return null;
    }

    public void addResults() {
        resultsForWeek();

        sumOfWeightPercentage /= currentAthlete.PersonalSchedule.size();
        sumOfRepPercentage /= currentAthlete.PersonalSchedule.size();
        double average = (sumOfRepPercentage + sumOfWeightPercentage) / 2;

        currentAthlete.Progress.add(average); // adds statistic to progress and
        currentAthlete.exercisesLogs.clear(); // deletes logs for current week
    }
}
