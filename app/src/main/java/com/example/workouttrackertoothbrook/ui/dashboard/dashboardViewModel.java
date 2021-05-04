package com.example.workouttrackertoothbrook.ui.dashboard;

import com.example.workouttrackertoothbrook.Data.Workouts;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class dashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<workoutModel> model;

    public MutableLiveData<workoutModel> getModel() {
        return model;
    }

    public dashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        model = new MutableLiveData<>();
        model.setValue(workoutModel.getInstance());
        model.getValue().setPreviousWeekMinutes(120);
        model.getValue().setWorkoutMinutes(300);
        model.getValue().getPreviousWeekWorkouts().put(model.getValue().getWorkoutTypes().get(4),50);
        model.getValue().getWorkouts().put(model.getValue().getWorkoutTypes().get(2),100);
        model.getValue().setKilometers(2.5);
        model.getValue().setPrevKilometers(0);
        model.getValue().setAverageMinutes(200);
        model.getValue().setAverageKilometers(2);

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Double> getPreviousKilometers() {

        MutableLiveData<Double> d = new MutableLiveData<>();
        d.setValue(model.getValue().getPrevKilometers());
        return d;

    }

    public LiveData<Double> getKilometers() {
        MutableLiveData<Double> d = new MutableLiveData<>();
        d.setValue(model.getValue().getKilometers());
        return d;
    }

    public LiveData<Integer> getMinutes() {
        MutableLiveData<Integer> d = new MutableLiveData<>();
        d.setValue(model.getValue().getWorkoutMinutes());
        return d;
    }

    public LiveData<Integer> getPreviousMinutes() {
        MutableLiveData<Integer> d = new MutableLiveData<>();
        d.setValue(model.getValue().getPreviousWeekMinutes());
        return d;
    }

    public LiveData<Integer> getAverageMinutes(){
        MutableLiveData<Integer> d = new MutableLiveData<>();
        d.setValue(model.getValue().getAverageMinutes());
        return d;
    }

    public LiveData<Double> getAverageKilometers(){
        MutableLiveData<Double> d = new MutableLiveData<>();
        d.setValue(model.getValue().getAverageKilometers());
        return d;
    }


    public String getLastWeekTimeString() {
        int time= model.getValue().getPreviousWeekMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return time+"Min";
        }
        return hour+"H "+time+"Min";
    }

    public String getTimeString() {
        int time= model.getValue().getWorkoutMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return time+"Min";
        }
        return hour+"H "+time+"Min";
    }

    public String getAvgTimeString() {
        int time= model.getValue().getAverageMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return time+"Min";
        }
        return hour+"H "+time+"Min";
    }

    public ArrayList<String> getWorkoutTypes(){
       return model.getValue().getWorkoutTypes();
    }

    public void addWorkout(String workoutType, int minutes, int reps) {
        addWorkoutMinutes(minutes);
        addWorkoutReps(workoutType,reps);
    }

    private void addWorkoutReps(String workoutType, int reps) {
        model.getValue().getWorkouts().put(workoutType,reps);
    }

    private void addWorkoutMinutes(int minutes) {
        int wm=model.getValue().getWorkoutMinutes()+minutes;
        model.getValue().setWorkoutMinutes(wm);
    }
}