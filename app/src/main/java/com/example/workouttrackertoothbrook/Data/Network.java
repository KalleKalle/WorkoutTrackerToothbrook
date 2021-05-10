package com.example.workouttrackertoothbrook.Data;

import com.example.workouttrackertoothbrook.ui.social.Group;

import java.util.ArrayList;

public class Network {
    public User searchForFriend(String email) {
        return new User("test@friend.cl","John samson",178,78.7);
    }

    public Group searchforGroup(String groupName) {
        ArrayList<User> members= new ArrayList<>();
        members.add(new User("test@friend.cl","John samson",178,78.7));
        return new Group("test",members);
    }

    public void addGroup(Group group, User self) {
    }
}
