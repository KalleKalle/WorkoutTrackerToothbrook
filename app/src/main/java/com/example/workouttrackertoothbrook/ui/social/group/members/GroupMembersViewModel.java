package com.example.workouttrackertoothbrook.ui.social.group.members;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class GroupMembersViewModel extends ViewModel {
    Network network;

    public GroupMembersViewModel() {
        network= new Network();
    }

    public ArrayList<User> getMembersOfGroup(String name) {
       return network.searchforGroup(name).getMembers();
    }
}