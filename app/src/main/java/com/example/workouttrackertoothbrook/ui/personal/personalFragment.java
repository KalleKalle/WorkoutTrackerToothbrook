package com.example.workouttrackertoothbrook.ui.personal;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.personal.addFriend.addFriendFragment;
import com.example.workouttrackertoothbrook.ui.personal.editBody.bodyInformationFragment;
import com.example.workouttrackertoothbrook.ui.personal.seeInformation.InformationFragment;
import com.example.workouttrackertoothbrook.ui.personal.seeworkouts.seeWorkoutFragment;
import com.example.workouttrackertoothbrook.ui.personal.setGoal.setGoalFragment;
import com.example.workouttrackertoothbrook.ui.personal.settings.settingsFragment;

public class personalFragment extends Fragment {

    private PersonalViewModel mViewModel;
    private TextView editBody;
    private TextView settings;
    private TextView addFriend;
    private TextView setGoal;
    private TextView seeWorkouts;
    private TextView seeInformation;

    public static personalFragment newInstance() {
        return new personalFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_personal, container, false);

        editBody= root.findViewById(R.id.textBody);
        settings= root.findViewById(R.id.TextSettings);
        addFriend= root.findViewById(R.id.addFriendText);
        setGoal= root.findViewById(R.id.setGoalText);
        seeWorkouts= root.findViewById(R.id.seeWorkoutsText);
        seeInformation= root.findViewById(R.id.seeinformationText);





        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);

        editBody.setOnClickListener(v -> mViewModel.changeFragment(new bodyInformationFragment(),getActivity()));
        settings.setOnClickListener(v -> mViewModel.changeFragment(new settingsFragment(),getActivity()));
        addFriend.setOnClickListener(v -> mViewModel.changeFragment(new addFriendFragment(),getActivity()));
        setGoal.setOnClickListener(v -> mViewModel.changeFragment(new setGoalFragment(),getActivity()));
        seeWorkouts.setOnClickListener(v -> mViewModel.changeFragment(new seeWorkoutFragment(),getActivity()));
        seeInformation.setOnClickListener(v -> mViewModel.changeFragment(new InformationFragment(),getActivity()));


    }

}