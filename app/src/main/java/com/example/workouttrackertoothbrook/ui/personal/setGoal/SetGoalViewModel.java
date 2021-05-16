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
            model.setTkm(Double.parseDouble(Km));
        }
        else {
            model.setTkm(-1);
        }
        if (!Kcal.equals("")){
            model.setTkcal(Integer.parseInt(Kcal));
        }
        else {
            model.setTkcal(-1);
        }

        if (!weight.equals("")) {
            model.setTweight(Double.parseDouble(weight));
        }
        else {
            model.setTweight(-1);
        }

    }

    public String getTWeight() {

        if(model.getTweight()>0)
        {
            return Double.toString(model.getTweight());
        }
        return "";

    }

    public String getTKcal() {
        if(model.getTkcal()>0)
        {
            return Integer.toString(model.getTkcal());
        }
        return "";
    }

    public String getTKm() {
        if(model.getTkm()>0)
        {
            return Double.toString(model.getTkm());
        }
        return "";

    }
}