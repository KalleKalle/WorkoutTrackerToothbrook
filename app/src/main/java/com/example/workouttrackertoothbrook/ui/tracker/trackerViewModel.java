package com.example.workouttrackertoothbrook.ui.tracker;

import android.content.Context;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

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

    public void saveWorkout(long timeTaken, double kilometers, Context context) {
        int minutes = (int) (timeTaken/60000);
        model.getSelf().setWorkoutMinutes(model.getWorkoutMinutes()+minutes);
        model.setWorkoutMinutes(model.getWorkoutMinutes()+minutes);
        model.getSelf().setKilometers(model.getKilometers()+kilometers);
        model.setKilometers(model.getKilometers() + kilometers);
        //.75 x your weight (in lbs.)
        double weightInLbs = model.getSelf().getWeight()*2.205;
        double TCB = 0.75*weightInLbs;
        Workout workout = new Workout(context.getString(R.string.walkRun),minutes, new Date(),kilometers,(int)TCB);
        model.getWorkouts().add(workout);
        network.saveAll();
    }

    public double CalcDistance(List<LatLng> list) {
        double earthRadius = 6371000; //meters
        double dist=0;
        for (int i = 0; i <list.size() ; i++) {
            if (i!=0) {
                double latitudeCord1= list.get(i-1).latitude;
                double latitudeCord2= list.get(i).latitude;
                double longitudeCord1= list.get(i-1).longitude;
                double longitudeCord2= list.get(i).longitude;
                double dLat = Math.toRadians(latitudeCord2 - latitudeCord1);
                double dLon = Math.toRadians(longitudeCord2 - longitudeCord1);

                // convert to radians
                latitudeCord1 = Math.toRadians(latitudeCord1);
                latitudeCord2 = Math.toRadians(latitudeCord2);

                // apply formulae
                double a = Math.pow(Math.sin(dLat / 2), 2) +
                        Math.pow(Math.sin(dLon / 2), 2) *
                                Math.cos(latitudeCord1) *
                                Math.cos(latitudeCord2);
                double rad = 6371;
                double c = 2 * Math.asin(Math.sqrt(a));
                dist= dist + rad * c;
            }
        }


        return dist;
    }

    public String takeTime(long timer) {
        long timeTakenInMilis = System.currentTimeMillis()-timer;
        int seconds = (int) (timeTakenInMilis / 1000) % 60;
        int minutes = (int) ((timeTakenInMilis / (1000 * 60)) % 60);
        int hours = (int) ((timeTakenInMilis / (1000 * 60 * 60)) % 24);
        return (hours + ":" + minutes +":" + seconds);

    }
}