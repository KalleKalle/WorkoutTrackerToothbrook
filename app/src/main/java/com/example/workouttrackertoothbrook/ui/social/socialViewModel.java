package com.example.workouttrackertoothbrook.ui.social;

import android.util.Log;

import com.example.workouttrackertoothbrook.Data.Competition;
import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.workoutModel;
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
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public class socialViewModel extends ViewModel {

    private workoutModel model;
    private Network network;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private Competition competition= null;
    private ArrayList<Group> groupArrayList = null;
    private ArrayList<HashMap> groupHashMap = null;
    private Group group= null;
    Boolean run1;
    boolean run2;

    public socialViewModel() {
        model= workoutModel.getInstance();
        network= new Network();
        firebaseAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
    }

    public void searchForGroup(String groupName, socialFragment socialFragment) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<User> members= (ArrayList<User>) value.get("members");
                String name= value.getString("name");
                if (members!=null && name!=null) {
                    group = new Group(name, members);
                }
                    socialFragment.groupReady(group);

            }
        });
    }

    public void addGroup(Group group, boolean NewGroup) {
        run1=false;
        run2=false;
        DocumentReference documentReference= firestore.collection("users").document(model.getSelf().getId());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!run1) {
                    run1=true;
                    DocumentReference documentReference2 = firestore.collection("users").document(model.getSelf().getId());
                    Map<String, Object> groupsMap = new HashMap<>();
                    groupArrayList = (ArrayList<Group>) value.get("groups");
                    groupArrayList.add(group);
                    groupsMap.put("groups", groupArrayList);
                    documentReference2.update(groupsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("database", "add group Successful");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("database", e.toString());
                        }
                    });

                }
            }
        });
        if (!NewGroup) {
            DocumentReference documentReference3 = firestore.collection("groups").document(group.getName());
            documentReference3.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (!run2) {
                        run2 = true;
                        Map<String, Object> memberMap = new HashMap<>();
                        ArrayList<User> members = (ArrayList<User>) value.get("members");
                        members.add(model.getSelf());
                        memberMap.put("members", members);
                        documentReference3.update(memberMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("database", "add member to group Successful");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("database", e.toString());
                            }
                        });
                    }

                }

            });
            //run=true;
        }

    }


    public void getGroups(socialFragment socialFragment) {
        DocumentReference documentReference= firestore.collection("users").document(model.getSelf().getId());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                groupHashMap = (ArrayList<HashMap>) value.get("groups");
                if (groupHashMap ==null){
                    groupHashMap =new ArrayList<>();
                }
                socialFragment.groupsUserMemberOfReady(groupHashMap);
            }
        });

    }


    public void createGroup(String groupName, socialFragment socialFragment) {
        DocumentReference documentReference= firestore.collection("groups").document(groupName);
        Map<String,Object> groupMap= new HashMap<>();
        ArrayList<User> members= new ArrayList<>();
        members.add(model.getSelf());
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
        addGroup(group,true);
    }

    public void groupReady(Group group) {

    }
}