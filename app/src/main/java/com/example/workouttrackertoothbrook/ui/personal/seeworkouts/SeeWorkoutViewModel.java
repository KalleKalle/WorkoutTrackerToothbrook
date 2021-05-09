package com.example.workouttrackertoothbrook.ui.personal.seeworkouts;

import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.ViewModel;

public class SeeWorkoutViewModel extends ViewModel {
    workoutModel model;

    public SeeWorkoutViewModel() {
        this.model = workoutModel.getInstance();
    }

    public workoutModel getModel() {
        return model;
    }

    public void setModel(workoutModel model) {
        this.model = model;
    }
}