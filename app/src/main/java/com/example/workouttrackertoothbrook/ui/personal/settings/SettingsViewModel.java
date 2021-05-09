package com.example.workouttrackertoothbrook.ui.personal.settings;

import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    workoutModel model;

    public SettingsViewModel() {
        model= workoutModel.getInstance();
    }
    public void addWorkoutType(String workoutType) {
        model.addWorkoutType(workoutType);
    }
    // TODO: Implement the ViewModel
}