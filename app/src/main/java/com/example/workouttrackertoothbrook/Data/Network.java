package com.example.workouttrackertoothbrook.Data;

import com.example.workouttrackertoothbrook.ui.social.Group;

import java.util.ArrayList;
import java.util.List;

public class Network {
    public User searchForFriend(String email) {
        return new User("test@friend.cl","John samson",178,78.7);
    }

    public Group searchforGroup(String groupName) {
        ArrayList<User> members= new ArrayList<>();
        members.add(new User("test@friend.cl","John samson",178,78.7));
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
}
