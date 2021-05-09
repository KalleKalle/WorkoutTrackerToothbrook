package com.example.workouttrackertoothbrook.ui.personal.addFriend;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.ViewModel;

public class AddFriendViewModel extends ViewModel {
    Network network;
    workoutModel model;

    public AddFriendViewModel(){
        network= new Network();
        model= workoutModel.getInstance();
    }
    public User searchForFriend(String email) {

        return network.searchForFriend(email);

    }

    public void addFriend(User friend) {
        model.addFriend(friend);
    }
}