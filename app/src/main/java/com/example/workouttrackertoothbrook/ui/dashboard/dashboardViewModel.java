package com.example.workouttrackertoothbrook.ui.dashboard;

import android.content.Context;
import android.util.Log;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class dashboardViewModel extends ViewModel implements LifecycleObserver {

    private MutableLiveData<String> mText;
    private MutableLiveData<workoutModel> model;
    private FirebaseFirestore firestore;
    private boolean run;
    private ArrayList<Group> groupArrayList;
    private ArrayList<String>alreadyUpdatedGroups;

    public MutableLiveData<workoutModel> getModel() {
        return model;
    }

    public dashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        model = new MutableLiveData<>();
        model.setValue(workoutModel.getInstance());
        firestore= FirebaseFirestore.getInstance();


    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getPreviousKilometers(Context context) {

        MutableLiveData<String> d = new MutableLiveData<>();
        double km= model.getValue().getPrevKilometers();
        if (km==-1){
            d.setValue(R.string.Last_week + "0 Km");
            return d;
        }
        d.setValue(context.getString(R.string.Last_week) + km+" Km");
        return d;

    }

    public LiveData<String> getKilometers(Context context) {
        MutableLiveData<String> d = new MutableLiveData<>();
        d.setValue(context.getString(R.string.This_week) +model.getValue().getKilometers()+" Km");
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

    public LiveData<String> getAverageKilometers(Context context){
        MutableLiveData<String> d = new MutableLiveData<>();
        d.setValue(context.getString(R.string.avg_distance) + model.getValue().getAverageKilometers()+" Km");
        return d;
    }


    public String getLastWeekTimeString(Context context) {
        int time= model.getValue().getPreviousWeekMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return context.getString(R.string.Last_week) +  time+" Min";
        }
        return context.getString(R.string.Last_week) +  hour+R.string.hour +time+" Min";
    }

    public String getTimeString(Context context) {
        int time= model.getValue().getWorkoutMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return context.getString(R.string.This_week)+ time+" Min";
        }
        return context.getString(R.string.This_week)+ hour+R.string.hour + time+" Min";
    }

    public String getAvgTimeString(Context context) {
        int time= model.getValue().getAverageMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return context.getString(R.string.avg_minutes) +  time+" Min";
        }
        return context.getString(R.string.avg_minutes) +  hour+R.string.hour +time+" Min";
    }

    public ArrayList<String> getWorkoutTypes(){
        if (model.getValue().getWorkoutTypes()!= null){
            return model.getValue().getWorkoutTypes();
        }
       return new ArrayList<>();
    }

    public void addWorkout(String workoutType, String minutes, String reps) {
        if(workoutType!=null && !minutes.equals("") && !reps.equals("")) {
            int m = Integer.parseInt(minutes);
            int r = Integer.parseInt(reps);
            if (m > 0 && r > 0) {
                addWorkoutMinutes(m);
                addWorkoutReps(workoutType, r,m);
                Network network = new Network();
                network.saveAll();
                updateGroups();
            }
        }

    }

    private void updateGroups() {
        alreadyUpdatedGroups=new ArrayList<>();
        DocumentReference documentReference= firestore.collection("users").document(model.getValue().getSelf().getId());
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
                                if (!alreadyUpdatedGroups.contains(value.getString("name"))) {
                                    alreadyUpdatedGroups.add(value.getString("name"));
                                    ArrayList<User> mem= new Gson().fromJson(new Gson().toJson(value.get("members")),new TypeToken<List<User>>(){}.getType());
                                    int i=-1;
                                    for (int j = 0; j < mem.size(); j++)
                                        if (mem.get(j).getId().equals(model.getValue().getSelf().getId())){
                                            i=j;
                                        }

                                   if (i>=0) {
                                       mem.get(i).setWorkoutMinutes(model.getValue().getWorkoutMinutes());
                                       DocumentReference documentReference3 = firestore.collection("groups").document((String) g.getName());
                                       documentReference3.update("members", mem).addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void aVoid) {
                                               Log.d("database", "minutes is updated successfully");
                                           }
                                       }).addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {
                                               Log.d("database", e.toString());
                                           }
                                       });
                                   }

                                }
                            }
                        });
                    }


            }
        });
    }

    private void addWorkoutReps(String workoutType, int reps, int duration) {
        double TCB = (duration*(3*3.5*model.getValue().getSelf().getWeight())/200);
        model.getValue().getWorkouts().add(new Workout(workoutType,duration,new Date(),reps,(int)TCB));
    }

    private void addWorkoutMinutes(int minutes) {
        int wm=model.getValue().getWorkoutMinutes()+minutes;
        model.getValue().setWorkoutMinutes(wm);

    }

    public void loadModel(DocumentSnapshot value, Context context) {
        Gson gson= new Gson();
        String data= gson.toJson(value.get("userData"));
        workoutModel fromDatabase = gson.fromJson(data,workoutModel.class);
        try {
            model.getValue().getSelf().setId(fromDatabase.getSelf().getId());
            model.getValue().getSelf().setName(fromDatabase.getSelf().getName());
            model.getValue().getSelf().setHeight(fromDatabase.getSelf().getHeight());
            model.getValue().getSelf().setWeight(fromDatabase.getSelf().getWeight());
            model.getValue().getSelf().setEmail(fromDatabase.getSelf().getEmail());
            model.getValue().setWorkouts(fromDatabase.getWorkouts());
            model.getValue().setPreviousWeekWorkouts(fromDatabase.getPreviousWeekWorkouts());
            model.getValue().setWorkoutMinutes(fromDatabase.getWorkoutMinutes());
            model.getValue().setPreviousWeekMinutes(fromDatabase.getPreviousWeekMinutes());
            model.getValue().setAverageMinutes(fromDatabase.getAverageMinutes());
            model.getValue().setAverageKilometers(fromDatabase.getAverageKilometers());
            model.getValue().setKilometers(fromDatabase.getKilometers());
            model.getValue().setPrevKilometers(fromDatabase.getPrevKilometers());
            model.getValue().setTweight(fromDatabase.getTweight());
            model.getValue().setTkcal(fromDatabase.getTkcal());
            model.getValue().setTkm(fromDatabase.getTkm());
            model.getValue().setFriends(fromDatabase.getFriends());
            model.getValue().setWorkoutTypes(fromDatabase.getWorkoutTypes());
        }catch (NullPointerException e) {
            new Network().LogOut();
        }
            if (!model.getValue().getWorkouts().isEmpty()) {
                try {
                    Calendar c = Calendar.getInstance();
                    Workout workout = model.getValue().getWorkouts().get(0);
                    Date toDate = new SimpleDateFormat().parse(workout.getTime());
                    c.setTime(toDate);
                    c.add(Calendar.DATE, 7);
                    if (c.getTime().compareTo(toDate) < 0) {
                        endOfWeek();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    void endOfWeek(){
        model.getValue().setAverageMinutes(calcAverageMin());
        model.getValue().setAverageKilometers(calcAverageKm());
        model.getValue().setPreviousWeekWorkouts(model.getValue().getWorkouts());
        model.getValue().setPrevKilometers(model.getValue().getKilometers());
        model.getValue().setPreviousWeekMinutes(model.getValue().getWorkoutMinutes());
        model.getValue().setKilometers(0);
        model.getValue().setWorkoutMinutes(0);
        model.getValue().setWorkouts(new ArrayList<>());
    }
    int calcAverageMin(){
        int min = model.getValue().getWorkoutMinutes();
        int avgMin= model.getValue().getAverageMinutes();
        return (min+avgMin/2);
    }

    double calcAverageKm(){
        double km= model.getValue().getKilometers();
        double avgKm= model.getValue().getAverageKilometers();
        return (km+avgKm/2);

    }
}