package com.example.workouttrackertoothbrook.ui.personal.seeInformation;

import com.example.workouttrackertoothbrook.Data.workoutModel;

import java.text.DecimalFormat;

import androidx.lifecycle.ViewModel;

public class InformationViewModel extends ViewModel {
    workoutModel model;

    public InformationViewModel() {
        model= workoutModel.getInstance();
}
    public String getHeight() {
        int h=model.getSelf().getHeight();
        if (h!=0){
            return Integer.toString(h)+"cm";
        }
        return "No Data";
    }

    public String getWeight() {
        double w=model.getSelf().getWeight();
        if (w!=0){
            return Double.toString(w)+"Kg";
        }
        return "No Data";
    }

    public String getWeightGoal() {
        double w=model.getTweight();
        if (w!=0){
            return Double.toString(w);
        }
        return "No Data";
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



    public String getKCalGoal() {
        int kCal=model.getTkcal();
        if (kCal!=0){
            return (Integer.toString(kCal-getKCalBurnedWeekInt())+"kcal");
        }
        return "No Data";
    }

    public String getKmGoal() {
        double Km=model.getTkm();
        if (Km!=0){
            return (Double.toString(Km-model.getKilometers())+"Km");
        }
        return "No Data";
    }


}