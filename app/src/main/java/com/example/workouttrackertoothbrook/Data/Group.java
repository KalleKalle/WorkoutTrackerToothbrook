package com.example.workouttrackertoothbrook.Data;

import com.example.workouttrackertoothbrook.Data.User;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private ArrayList<User> members;
    private Competition competition;

    public Group(String name, ArrayList<User> members) {
        this.name = name;
        this.members = members;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }
}
