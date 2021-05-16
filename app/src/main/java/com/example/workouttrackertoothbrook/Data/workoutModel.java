package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;

public class workoutModel {


    private static workoutModel single_instance = null;

    ArrayList<Workout> workouts;
    ArrayList<Workout> previousWeekWorkouts;
    int workoutMinutes;
    int previousWeekMinutes;
    int averageMinutes;
    double averageKilometers;
    double kilometers;
    double prevKilometers;
    double tweight;
    int tkcal;
    double tkm;
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
        this.friends = friends;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public double getTweight() {
        return tweight;
    }

    public void setTweight(double tweight) {
        this.tweight = tweight;
    }

    public int getTkcal() {
        return tkcal;
    }

    public void setTkcal(int tkcal) {
        this.tkcal = tkcal;
    }

    public double getTkm() {
        return tkm;
    }

    public void setTkm(double tkm) {
        this.tkm = tkm;
    }

    private workoutModel() {
        /*

        TWeight= 85;
        TKcal=88000;
        TKm=20;


         */

        workoutMinutes=0;
        previousWeekMinutes=0;
        averageKilometers=0;
        averageMinutes=0;
        kilometers=0;
        WorkoutTypesCreator();
        workouts= new ArrayList<>();
        previousWeekWorkouts = new ArrayList<>();
        friends=new ArrayList<>();
        self = new User();

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
        workoutTypes.add("Walk/Run");
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
        self.setKilometers(kilometers);
    }

    public double getPrevKilometers() {
        return prevKilometers;
    }

    public void setPrevKilometers(double prevKilometers) {
        this.prevKilometers = prevKilometers;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    public ArrayList<Workout> getPreviousWeekWorkouts() {
        return previousWeekWorkouts;
    }

    public void setPreviousWeekWorkouts(ArrayList<Workout> previousWeekWorkouts) {
        this.previousWeekWorkouts = previousWeekWorkouts;
    }

    public int getWorkoutMinutes() {
        return workoutMinutes;
    }

    public void setWorkoutMinutes(int workoutMinutes) {

        this.workoutMinutes = workoutMinutes;
        self.setWorkoutMinutes(workoutMinutes);
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

    public void setWorkoutTypes(ArrayList<String> workoutTypes) {
        this.workoutTypes = workoutTypes;
    }

    public void addWorkoutType(String workouts) {
        workoutTypes.add(workouts);
    }
}
