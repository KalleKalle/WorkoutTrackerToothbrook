package com.example.workouttrackertoothbrook.Data;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class Network {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    public Network() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firestore= FirebaseFirestore.getInstance();
    }

    public User searchForFriend(String email) {
        return new User("test@friend.cl","John samson",178,78.7);
    }

    public Group searchforGroup(String groupName) {
        ArrayList<User> members= new ArrayList<>();
        User u= new User("test@friend.cl","Timmy Turner",178,78.7);
        u.setKilometers(10);
        u.setWorkoutMinutes(100);
        User u1= new User("test@friend.cl","Jon jamson",178,78.7);
        u1.setKilometers(40);
        u1.setWorkoutMinutes(250);
        User u2= new User("test@friend.cl","John Connor",178,78.7);
        u2.setKilometers(0);
        u2.setWorkoutMinutes(800);
        User u3= new User("test@friend.cl","Samson the fake",178,78.7);
        u3.setKilometers(100);
        u3.setWorkoutMinutes(500);

        members.add(u);
        members.add(u1);
        members.add(u2);
        members.add(u3);

        return new Group(groupName,members);
    }

    public void addGroup(Group group, User self) {

    }

    public List<Group> getGroupsThatUserIsMemberOf(){
        ArrayList<Group> groups= new ArrayList<>();

        return groups;
    }

    public void CreateGroup(String groupName, User self) {

    }

    public Competition findCompetition(String groupName) {
        return null;
    }

    public void createGroup(String type, String groupName) {
    }

    public void createCompetition(Competition competition, String groupName) {

    }

    public void LogOut() {
        firebaseAuth.signOut();
    }

    public void saveAll() {
        DocumentReference documentReference = firestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        Map<String,Object> change= new HashMap<>();
        change.put("userData",workoutModel.getInstance());
        documentReference.update(change).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("database","save Successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database",e.toString());
            }
        });
    }
}
