package com.example.workout;

import java.io.Serializable;

class Exercise implements Serializable {

    private String exerciseDesc; //Trainer's description for the exercise(how to it correctly)
    int exerciseReps; //Number of reps athlete must achieve
    int exerciseWeight; //Amount of suggested weight for the athlete. For exercises that you cannot choose the weight (e.g. push ups) weight is set to zero.
    String exerciseName; //The name of the exercise
    private String exerciseCategory;// Which category the exercises appertains to (e.g. legs ,lower back , abs etc)


    //~~~Constructor~~~
    Exercise(String exerciseDesc, String exerciseName, int exerciseReps, int exerciseWeight, String exerciseCategory) {
        this.exerciseDesc = exerciseDesc;
        this.exerciseName = exerciseName;
        this.exerciseReps = exerciseReps;
        this.exerciseWeight = exerciseWeight;
        this.exerciseCategory = exerciseCategory;
    }

    Exercise(String exerciseName, int exerciseReps, int exerciseWeight) {
        this.exerciseName = exerciseName;
        this.exerciseReps = exerciseReps;
        this.exerciseWeight = exerciseWeight;
    }

    //~~~Getters~~~
    String getExerciseDesc() {
        return exerciseDesc;
    }

    String getExerciseName() {
        return exerciseName;
    }

    String getExerciseCategory() {
        return exerciseCategory;
    }

    int getExerciseReps() {
        return exerciseReps;
    }

    int getExerciseWeight() {
        return exerciseWeight;
    }


}
