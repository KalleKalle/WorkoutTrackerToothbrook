package com.example.workouttrackertoothbrook.Data;

import java.util.Date;

public class Workout {
    String Type;
    int duration;
    Date time;
    int reps;
    int calories;

    public Workout(String type, int duration, Date time, int reps, int calories) {
        Type = type;
        this.calories=calories;
        this.duration = duration;
        this.time = time;
        this.reps = reps;
    }

    public String getCalories() {
        return Integer.toString(calories);
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
        this.time = time;
    }

    public String getReps() {
        return Integer.toString(reps);
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
