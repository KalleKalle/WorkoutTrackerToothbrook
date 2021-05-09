package com.example.workouttrackertoothbrook.ui.personal.setGoal;

import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.ViewModel;

public class SetGoalViewModel extends ViewModel {
    workoutModel model;

    public SetGoalViewModel() {
        model= workoutModel.getInstance();
    }

    public void saveGoals(String Km, String Kcal, String weight) {
        if(!Km.equals("")) {
            model.setTKm(Double.parseDouble(Km));
        }
        else {
            model.setTKm(-1);
        }
        if (!Kcal.equals("")){
            model.setTKcal(Integer.parseInt(Kcal));
        }
        else {
            model.setTKcal(-1);
        }

        if (!weight.equals("")) {
            model.setTWeight(Double.parseDouble(weight));
        }
        else {
            model.setTWeight(-1);
        }

    }

    public String getTWeight() {

        if(model.getTWeight()>0)
        {
            return Double.toString(model.getTWeight());
        }
        return "";

    }

    public String getTKcal() {
        if(model.getTKcal()>0)
        {
            return Integer.toString(model.getTKcal());
        }
        return "";
    }

    public String getTKm() {
        if(model.getTKm()>0)
        {
            return Double.toString(model.getTKm());
        }
        return "";

    }
}