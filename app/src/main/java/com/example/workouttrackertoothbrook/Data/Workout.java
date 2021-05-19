package com.example.workouttrackertoothbrook.Data;

import java.util.Date;

public class Workout {
    String type;
    int duration;
    String time;
    double reps;
    int calories;

    public Workout(String type, int duration, Date time, double reps, int calories) {
        this.type = type;
        this.calories=calories;
        this.duration = duration;
        this.time = time.toString();
        this.reps = reps;
    }

    public String getCalories() {
        return Integer.toString(calories);
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return Integer.toString(duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(Date time) {
        this.time = time.toString();;
    }

    public String getReps() {
        return Double.toString(reps);
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
