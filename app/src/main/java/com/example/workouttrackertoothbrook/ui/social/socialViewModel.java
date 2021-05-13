package com.example.workouttrackertoothbrook.ui.social;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class socialViewModel extends ViewModel {

    private workoutModel model;
    private Network network;

    public socialViewModel() {
        model= workoutModel.getInstance();
        network= new Network();
    }

    public Group searchForGroup(String groupName) {

       return network.searchforGroup(groupName);
    }

    public void addGroup(Group group) {
        network.addGroup(group,model.getSelf());
    }


    public List<Group> getGroups() {
        return network.getGroupsThatUserIsMemberOf();
    }


    public void createGroup(String groupName) {
        network.CreateGroup(groupName,model.getSelf());
    }
}