package com.example.workouttrackertoothbrook.Data;

public class User {
    private String id;
    private String email;
    private String name;
    private int height;
    private double weight;
    private int workoutMinutes;
    double kilometers;

    public void setId(String id) {
        this.id = id;
    }

    public User(String email, String name, int height, double weight) {
        this.email = email;
        this.name = name;
        this.height =height;
        this.weight =weight;

    }
    public User(){     }

    public int getWorkoutMinutes() {
        return workoutMinutes;
    }

    public void setWorkoutMinutes(int workoutMinutes) {
        this.workoutMinutes = workoutMinutes;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public String getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }


}
