package com.example.workouttrackertoothbrook.ui.personal.settings;

import android.widget.Toast;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    workoutModel model;
    FirebaseAuth firebaseAuth;
    Network network;

    public SettingsViewModel() {
        model= workoutModel.getInstance();
        network= new Network();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void addWorkoutType(String workoutType) {
        model.addWorkoutType(workoutType);
    }

    public void logoutUser() {
        network.LogOut();

    }


    public void SaveEmail(String email, FragmentActivity activity) {
        model.getSelf().setEmail(email);
        firebaseAuth.getCurrentUser().updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(activity.getApplicationContext(),"Email updated",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity.getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        });
        network.saveAll();

    }

    public String getEmail() {
        return model.getSelf().getEmail();
    }
}