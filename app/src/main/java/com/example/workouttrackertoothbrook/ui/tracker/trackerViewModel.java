package com.example.workouttrackertoothbrook.ui.tracker;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class trackerViewModel extends ViewModel {
    private workoutModel model;
    private Network network;

    private MutableLiveData<String> mText;

    public trackerViewModel() {
        model= workoutModel.getInstance();
        mText = new MutableLiveData<>();
        network= new Network();
        mText.setValue("This is tracker fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void saveWorkout(long timeTaken) {
        int minutes = (int) (timeTaken/60000);
        model.getSelf().setWorkoutMinutes(model.getWorkoutMinutes()+minutes);
        model.setWorkoutMinutes(model.getWorkoutMinutes()+minutes);
        network.saveAll();
    }
}