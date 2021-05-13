package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;
import java.util.List;

public class Network {
    public User searchForFriend(String email) {
        return new User("test@friend.cl","John samson",178,78.7);
    }

    public Group searchforGroup(String groupName) {
        ArrayList<User> members= new ArrayList<>();
        User u= new User("test@friend.cl","Timmy Turner",178,78.7);
        u.setKilometers(10);
        u.setWorkoutMinutes(100);
        User u1= new User("test@friend.cl","Jon jamson",178,78.7);
        u1.setKilometers(40);
        u1.setWorkoutMinutes(250);
        User u2= new User("test@friend.cl","John Connor",178,78.7);
        u2.setKilometers(0);
        u2.setWorkoutMinutes(800);
        User u3= new User("test@friend.cl","Samson the fake",178,78.7);
        u3.setKilometers(100);
        u3.setWorkoutMinutes(500);

        members.add(u);
        members.add(u1);
        members.add(u2);
        members.add(u3);

        return new Group(groupName,members);
    }

    public void addGroup(Group group, User self) {

    }

    public List<Group> getGroupsThatUserIsMemberOf(){
        ArrayList<Group> groups= new ArrayList<>();
        groups.add(searchforGroup("test"));
        groups.add(searchforGroup("test2"));
        groups.add(searchforGroup("test3"));
        return groups;
    }

    public void CreateGroup(String groupName, User self) {
    }

    public Competition findCompetition(String groupName) {
        return null;
    }

    public void createGroup(String type, String groupName) {
    }

    public void createCompetition(Competition competition, String groupName) {

    }
}
