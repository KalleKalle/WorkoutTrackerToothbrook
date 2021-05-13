package com.example.workouttrackertoothbrook.ui.personal.seeworkouts;

import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Workout> getWorkouts() {
        ArrayList<Workout> workouts= new ArrayList<>(model.getWorkouts());
        Collections.reverse(workouts);
        return workouts;
    }
}