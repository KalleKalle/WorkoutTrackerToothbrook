package com.example.workouttrackertoothbrook.ui.personal.settings;

import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.google.firebase.auth.FirebaseAuth;

import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    workoutModel model;
    FirebaseAuth firebaseAuth;

    public SettingsViewModel() {
        model= workoutModel.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void addWorkoutType(String workoutType) {
        model.addWorkoutType(workoutType);
    }

    public void logoutUser() {
        firebaseAuth.signOut();
    }
}