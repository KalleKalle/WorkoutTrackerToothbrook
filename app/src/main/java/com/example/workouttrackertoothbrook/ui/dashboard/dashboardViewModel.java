package com.example.workouttrackertoothbrook.ui.dashboard;

import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;

import java.util.ArrayList;
import java.util.Date;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class dashboardViewModel extends ViewModel implements LifecycleObserver {

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
        model.getValue().getPreviousWeekWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(4),50, new Date(),50,2000));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(2),20, new Date(),10,3581));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(0),30, new Date(),20,123651));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(1),40, new Date(),35,1415));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(3),80, new Date(),1,145));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(4),20, new Date(),10,1111));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(5),900, new Date(),500,1235));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(6),20, new Date(),10,7786));
        model.getValue().getWorkouts().add(new Workout(model.getValue().getWorkoutTypes().get(7),20, new Date(),10,10000));
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

    public void addWorkout(String workoutType, String minutes, String reps) {
        if(workoutType!=null && !minutes.equals("") && !reps.equals("")) {
            int m = Integer.parseInt(minutes);
            int r = Integer.parseInt(reps);
            if (m > 0 && r > 0) {
                addWorkoutMinutes(m);
                addWorkoutReps(workoutType, r,m);
            }
        }

    }

    private void addWorkoutReps(String workoutType, int reps, int duration) {
        double TCB = (duration*(3*3.5*model.getValue().getSelf().getWeight())/200);
        model.getValue().getWorkouts().add(new Workout(workoutType,duration,new Date(),reps,(int)TCB));
    }

    private void addWorkoutMinutes(int minutes) {
        int wm=model.getValue().getWorkoutMinutes()+minutes;
        model.getValue().setWorkoutMinutes(wm);
    }
}