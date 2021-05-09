package com.example.workouttrackertoothbrook.Data;

public class User {
    private String id;
    private String Email;
    private String Name;
    private int Height;
    private double Weight;

    public String getId() {
        return id;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public void setName(String name) {
        Name = name;
    }

    public User(String email, String name,int height,double weight) {
        Email = email;
        Name = name;
        Height=height;
        Weight=weight;

    }
}
