package com.example.workouttrackertoothbrook.Data;

import android.util.Log;

import com.example.workouttrackertoothbrook.ui.social.socialViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Network {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    Competition competition= null;
    ArrayList<Group> groupArrayList = null;
    Group group= null;
    boolean logout= false;

    public Network() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firestore= FirebaseFirestore.getInstance();
    }

    public User searchForFriend(String email) {
        return new User("test@friend.cl","John samson",178,78.7);
    }

    public Group searchforGroup(String groupName, socialViewModel socialViewModel) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<User> members= (ArrayList<User>) value.get("members");
                String name= value.getString("name");
                group = new Group(name, members);
                //socialViewModel.groupReady(group);
            }
        });

        return group;
    }

    public void addGroup(Group group, User self) {
        DocumentReference documentReference= firestore.collection("users").document(self.getId());
        Map<String,Object> groupsMap= new HashMap<>();
        List<Group> groups= getGroupsThatUserIsMemberOf(self);
        groups.add(group);
        groupsMap.put("groups",groups);
        documentReference.update(groupsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("database","add group Successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database",e.toString());
            }
        });


    }

    public List<Group> getGroupsThatUserIsMemberOf(User self){

        DocumentReference documentReference= firestore.collection("users").document(self.getId());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                groupArrayList = (ArrayList<Group>) value.get("groups");
            }
        });

        return groupArrayList;
    }

    public void CreateGroup(String groupName, User self) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        Map<String,Object> groupMap= new HashMap<>();
        ArrayList<User> members= new ArrayList<>();
        members.add(self);
        Group group = new Group(groupName,members);
        groupMap.put("name",groupName);
        groupMap.put("members",members);

        documentReference.set(groupMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("database","group is created successfully");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database",e.toString());
            }
        });



    }

    public Competition findCompetition(String groupName) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                competition= (Competition) value.get("Competition");
            }
        });
        return competition;
    }


    public void createCompetition(Competition competition, String groupName) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        Map<String,Object> competitionMap= new HashMap<>();
        competitionMap.put("Competition",competition);

        documentReference.set(competitionMap).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public void LogOut() {
        logout=true;
        saveAll();

    }

    private void realLogout() {
        firebaseAuth.signOut();
    }

    public void saveAll() {
        try {
            if (firebaseAuth != null) {
                DocumentReference documentReference = firestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
                Map<String, Object> change = new HashMap<>();
                change.put("userData", workoutModel.getInstance());
                documentReference.update(change).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("database", "save Successful");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("database", e.toString());
                    }
                });
            }
            if (logout) {
                logout = false;
                realLogout();
            }
        }catch (NullPointerException ignored){

        }
    }
}
