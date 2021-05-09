package com.example.workouttrackertoothbrook.ui.personal.editBody;

import com.example.workouttrackertoothbrook.Data.workoutModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BodyInformationViewModel extends ViewModel {
    MutableLiveData<workoutModel> model;

    public BodyInformationViewModel() {
        model= new MutableLiveData<>();
        model.setValue(workoutModel.getInstance());
    }

    public String getName() {
        return model.getValue().getSelf().getName();
    }

    public String getHeight() {
        return Integer.toString(model.getValue().getSelf().getHeight());
    }

    public String getWeight() {
        return Double.toString(model.getValue().getSelf().getWeight());
    }

    public void saveInformation(String name, String height, String weight,String email) {
        model.getValue().getSelf().setName(name);
        model.getValue().getSelf().setHeight(Integer.parseInt(height));
        model.getValue().getSelf().setWeight(Double.parseDouble(weight));
        model.getValue().getSelf().setEmail(email);
    }

    public LiveData<workoutModel> getModel() {
        return model;
    }

    public String getEmail() {
       return model.getValue().getSelf().getEmail();
    }
}