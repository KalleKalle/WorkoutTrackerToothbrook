package com.example.workouttrackertoothbrook.ui.personal.seeInformation;

import android.content.Context;

import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;

import java.text.DecimalFormat;

import androidx.lifecycle.ViewModel;

public class InformationViewModel extends ViewModel {
    workoutModel model;

    public InformationViewModel() {
        model= workoutModel.getInstance();
}
    public String getHeight(Context context) {
        int h=model.getSelf().getHeight();
        if (h!=0){
            return Integer.toString(h)+"cm";
        }
        return context.getString(R.string.noDate);
    }

    public String getWeight(Context context) {
        double w=model.getSelf().getWeight();
        if (w!=0){
            return Double.toString(w)+"Kg";
        }
        return context.getString(R.string.noDate);
    }

    public String getWeightGoal(Context context) {
        double w=model.getTweight();
        if (w!=0){
            return Double.toString(w);
        }
        return context.getString(R.string.noDate);
    }

    public String getBMI() {
        double heightInM = ((double) model.getSelf().getHeight()/100);
        double bmi= (model.getSelf().getWeight()/(Math.pow((heightInM),2)));
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(bmi);
    }

    public String getKCalBurnedWeek() {
        //Total calories burned = Duration (in minutes)*(MET*3.5*weight in kg)/200
        double TCB = (model.getWorkoutMinutes()*(3*3.5*model.getSelf().getWeight())/200);
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(TCB);
    }

    private int getKCalBurnedWeekInt() {
        //Total calories burned = Duration (in minutes)*(MET*3.5*weight in kg)/200
        return (int)(model.getWorkoutMinutes()*(3*3.5*model.getSelf().getWeight())/200);

    }



    public String getKCalGoal(Context context) {
        int kCal=model.getTkcal();
        if (kCal!=0){
            return (Integer.toString(kCal-getKCalBurnedWeekInt())+"kcal");
        }
        return context.getString(R.string.noDate);
    }

    public String getKmGoal(Context context) {
        double Km=model.getTkm();
        if (Km!=0){
            return (Double.toString(Km-model.getKilometers())+"Km");
        }
        return context.getString(R.string.noDate);
    }


}