package com.example.workouttrackertoothbrook.Data;

public class User {
    String id;
    String Email;
    String Name;

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

    public void setName(String name) {
        Name = name;
    }

    public User(String email, String name) {
        Email = email;
        Name = name;
    }
}
