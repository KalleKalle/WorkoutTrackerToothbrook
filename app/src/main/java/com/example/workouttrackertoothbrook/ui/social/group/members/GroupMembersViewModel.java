package com.example.workouttrackertoothbrook.ui.social.group.members;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public class GroupMembersViewModel extends ViewModel {
    Network network;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    Group group;
    Gson gson;

    public GroupMembersViewModel() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firestore= FirebaseFirestore.getInstance();
        gson= new Gson();
        network= new Network();
    }

    public void getMembersOfGroup(String name, groupMembersFragment groupMembersFragment) {
        DocumentReference documentReference= firestore.collection("groups").document(name);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<HashMap> members= (ArrayList<HashMap>) value.get("members");
                String name= value.getString("name");
                groupMembersFragment.groupMembersReady(members);
            }
        });
    }
}