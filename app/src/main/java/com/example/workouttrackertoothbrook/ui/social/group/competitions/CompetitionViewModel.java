package com.example.workouttrackertoothbrook.ui.social.group.competitions;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.workouttrackertoothbrook.Data.Competition;
import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.kilometersCompetition;
import com.example.workouttrackertoothbrook.Data.minutesCompetition;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public class CompetitionViewModel extends ViewModel {
    Network network;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    Competition competition= null;

    public CompetitionViewModel() {
        network= new Network();
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firestore= FirebaseFirestore.getInstance();
    }

    public void getCompetiton(String groupName, competitionFragment competitionFragment) {
        Gson gson= new Gson();
        DocumentReference documentReference= firestore.collection("groups").document(groupName);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                HashMap<String,String> map= (HashMap) value.get("Competition");
                if (map!=null) {
                    competition = new Competition(map.get("category")) {
                        @Override
                        public List<HashMap> getRanking(List<HashMap> contestants) {
                            return null;
                        }
                    };
                }
                competitionFragment.CompReady(competition,groupName);
            }
        });
    }

    public void createCompetition(String type, String groupName, Context context) {
        Competition competition;
        if (type.equals(context.getString(R.string.mostminutes))){
            type="Most Minutes";
        }
        else {
            type="Most Kilometers";
        }
        switch (type){
            case "Most Minutes":
                competition= new minutesCompetition(context.getString(R.string.mostminutes));
                break;
            default:
                competition= new kilometersCompetition(context.getString(R.string.mostkilometers));
                break;
        }
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        Map<String,Object> competitionMap= new HashMap<>();
        competitionMap.put("Competition",competition);

        documentReference.update(competitionMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("database","competition is created successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database",e.toString());
            }
        });

    }

    public void getMembersOfGroup(String groupName, competitionFragment competitionFragment) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<HashMap> members= (ArrayList<HashMap>) value.get("members");
                String name= value.getString("name");
                competitionFragment.categoryAndMembersReady(members);
            }
        });
    }

    public void EndCompetition(String groupName, competitionFragment competitionFragment) {
        DocumentReference documentReference = firestore.collection("groups").document(groupName);
        Map<String,Object> map= new HashMap<>();
        map.put("Competition",null);
        documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("database","competition is ended successfully");
                competitionFragment.CompReady(null,groupName);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database",e.toString());
            }
        });

    }
}