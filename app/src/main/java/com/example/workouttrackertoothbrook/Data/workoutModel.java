package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class workoutModel {


    private static workoutModel single_instance = null;

    Map<String, Integer> workouts;
    Map<String, Integer> previousWeekWorkouts;
    int workoutMinutes;
    int previousWeekMinutes;
    int averageMinutes;
    double averageKilometers;
    double kilometers;
    double prevKilometers;
    User self;
    ArrayList<User> friends;
    ArrayList<String> workoutTypes;

    public static workoutModel getInstance()
    {
        if (single_instance == null) {
            single_instance = new workoutModel();
        }

        return single_instance;
    }

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        friends = friends;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    private workoutModel() {
        workoutMinutes=-1;
        previousWeekMinutes=-1;
        averageKilometers=-1;
        averageMinutes=-1;
        kilometers=-1;
        WorkoutTypesCreator();
        prevKilometers=-1;
        workouts= new HashMap<>();
        previousWeekWorkouts = new HashMap<>();
    }

    private void WorkoutTypesCreator() {
        workoutTypes=new ArrayList<>();
        workoutTypes.add("Glute bridge");
        workoutTypes.add("Lateral rise");
        workoutTypes.add("Reverse lunge");
        workoutTypes.add("Bicep curl");
        workoutTypes.add("Chest press");
        workoutTypes.add("Crunches");
        workoutTypes.add("Overhead press");
        workoutTypes.add("Plank");
        workoutTypes.add("Push up");
        workoutTypes.add("Squat");
        workoutTypes.add("Superman");
    }

    public Map<String, Integer> getWorkouts() {
        return workouts;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getPrevKilometers() {
        return prevKilometers;
    }

    public void setPrevKilometers(double prevKilometers) {
        this.prevKilometers = prevKilometers;
    }

    public void setWorkouts(Map<String, Integer> workouts) {
        this.workouts = workouts;
    }

    public Map<String, Integer> getPreviousWeekWorkouts() {
        return previousWeekWorkouts;
    }

    public void setPreviousWeekWorkouts(Map<String, Integer> previousWeekWorkouts) {
        this.previousWeekWorkouts = previousWeekWorkouts;
    }

    public int getWorkoutMinutes() {
        return workoutMinutes;
    }

    public void setWorkoutMinutes(int workoutMinutes) {
        this.workoutMinutes = workoutMinutes;
    }

    public int getPreviousWeekMinutes() {
        return previousWeekMinutes;
    }

    public int getAverageMinutes() {
        return averageMinutes;
    }

    public void setAverageMinutes(int averageMinutes) {
        this.averageMinutes = averageMinutes;
    }

    public void setAverageKilometers(double averageKilometers) {
        this.averageKilometers = averageKilometers;
    }

    public double getAverageKilometers() {
        return averageKilometers;
    }

    public void setPreviousWeekMinutes(int previousWeekMinutes) {
        this.previousWeekMinutes = previousWeekMinutes;
    }

    public ArrayList<String> getWorkoutTypes() {
        return workoutTypes;
    }

    public void addWorkoutType(String workouts) {
        workoutTypes.add(workouts);
    }
}
