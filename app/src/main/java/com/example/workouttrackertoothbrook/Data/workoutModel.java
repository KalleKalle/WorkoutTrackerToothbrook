package com.example.workouttrackertoothbrook.Data;

import android.content.Context;

import com.example.workouttrackertoothbrook.R;

import java.util.ArrayList;

public class workoutModel {


    private static workoutModel single_instance = null;

   private ArrayList<Workout> workouts;
   private ArrayList<Workout> previousWeekWorkouts;
   private int workoutMinutes;
    private int previousWeekMinutes;
   private int averageMinutes;
    private double averageKilometers;

   private double kilometers;
   private double prevKilometers;
    private double tweight;
   private int tkcal;

    private double tkm;
    private User self;
    private ArrayList<User> friends;
    private ArrayList<String> workoutTypes;




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

        workoutMinutes=0;
        previousWeekMinutes=0;
        averageKilometers=0;
        averageMinutes=0;
        kilometers=0;
        workouts= new ArrayList<>();
        previousWeekWorkouts = new ArrayList<>();
        friends=new ArrayList<>();
        self = new User();

    }

    public void WorkoutTypesCreator(Context context) {
        workoutTypes=new ArrayList<>();
        workoutTypes.add(context.getString(R.string.glutebrigde));
        workoutTypes.add(context.getString(R.string.lateralrise));
        workoutTypes.add(context.getString(R.string.reverselunge));
        workoutTypes.add(context.getString(R.string.bicepcurl));
        workoutTypes.add(context.getString(R.string.chestpress));
        workoutTypes.add(context.getString(R.string.crunches));
        workoutTypes.add(context.getString(R.string.overheadpress));
        workoutTypes.add(context.getString(R.string.plank));
        workoutTypes.add(context.getString(R.string.pushup));
        workoutTypes.add(context.getString(R.string.squat));
        workoutTypes.add(context.getString(R.string.superman));
        workoutTypes.add(context.getString(R.string.walkRun));
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
