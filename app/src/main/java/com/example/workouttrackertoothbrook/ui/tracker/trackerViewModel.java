package com.example.workouttrackertoothbrook.ui.tracker;

import android.content.Context;
import android.util.Log;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class trackerViewModel extends ViewModel {
    private workoutModel model;
    private Network network;
    private FirebaseFirestore firestore;
    private ArrayList<Group> groupArrayList;
    private ArrayList<String>alreadyUpdatedGroups;

    private MutableLiveData<String> mText;

    public trackerViewModel() {
        model= workoutModel.getInstance();
        mText = new MutableLiveData<>();
        network= new Network();
        mText.setValue("This is tracker fragment");
        firestore= FirebaseFirestore.getInstance();
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
        double TCB = 0.75*weightInLbs*kilometers;
        Workout workout = new Workout(context.getString(R.string.walkRun),minutes, new Date(),kilometers,(int)TCB);
        model.getWorkouts().add(workout);
        updateGroups();
        network.saveAll();
    }
    private void updateGroups() {
        alreadyUpdatedGroups=new ArrayList<>();
        DocumentReference documentReference= firestore.collection("users").document(model.getSelf().getId());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                DocumentReference documentReference2 ;
                Map<String, Object> groupsMap = new HashMap<>();
                groupArrayList = new Gson().fromJson(new Gson().toJson(value.get("groups")),new TypeToken<List<Group>>(){}.getType());;
                for (Group g: groupArrayList) {
                    documentReference2= firestore.collection("groups").document((String) g.getName());
                    documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            try {
                                if (!alreadyUpdatedGroups.contains(value.getString("name"))) {
                                    alreadyUpdatedGroups.add(value.getString("name"));
                                    ArrayList<User> mem = new Gson().fromJson(new Gson().toJson(value.get("members")), new TypeToken<List<User>>() {
                                    }.getType());
                                    int i = -1;
                                    for (int j = 0; j < mem.size(); j++)
                                        if (mem.get(j).getId().equals(model.getSelf().getId())) {
                                            i = j;
                                        }

                                    if (i >= 0) {
                                        mem.get(i).setWorkoutMinutes(model.getWorkoutMinutes());
                                        mem.get(i).setKilometers(model.getKilometers());
                                        DocumentReference documentReference3 = firestore.collection("groups").document((String) g.getName());
                                        documentReference3.update("members", mem).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("database", "minutes and kilometers is updated successfully");
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("database", e.toString());
                                            }
                                        });
                                    }

                                }
                            }catch (NullPointerException ignore){}
                        }
                    });
                }


            }
        });
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