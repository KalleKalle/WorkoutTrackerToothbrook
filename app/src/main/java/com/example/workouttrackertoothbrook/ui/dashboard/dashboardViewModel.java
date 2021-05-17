package com.example.workouttrackertoothbrook.ui.dashboard;

import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getPreviousKilometers() {

        MutableLiveData<String> d = new MutableLiveData<>();
        double km= model.getValue().getPrevKilometers();
        if (km==-1){
            d.setValue("Last week: " + "0 Km");
            return d;
        }
        d.setValue("Last week: " + km+" Km");
        return d;

    }

    public LiveData<String> getKilometers() {
        MutableLiveData<String> d = new MutableLiveData<>();
        d.setValue("This week "+model.getValue().getKilometers()+" Km");
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

    public LiveData<String> getAverageKilometers(){
        MutableLiveData<String> d = new MutableLiveData<>();
        d.setValue("Avg Distance: " + model.getValue().getAverageKilometers()+" Km");
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
            return "Last week: " +  time+" Min";
        }
        return "Last week: " +  hour+"H "+time+" Min";
    }

    public String getTimeString() {
        int time= model.getValue().getWorkoutMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return "This week "+ time+" Min";
        }
        return "This week "+ hour+"H "+time+" Min";
    }

    public String getAvgTimeString() {
        int time= model.getValue().getAverageMinutes();
        int hour=0;
        while(time>=60){
            hour++;
            time=time-60;
        }
        if(hour==0){
            return "Avg time: " +  time+" Min";
        }
        return "Avg time: " +  hour+"H "+time+" Min";
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

    public void loadModel(DocumentSnapshot value) {
        Gson gson= new Gson();
        String data= gson.toJson(value.get("userData"));
        workoutModel fromDatabase = gson.fromJson(data,workoutModel.class);
        if (model.getValue().getSelf()==null){
            new Network().LogOut();
        }
        else {
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