package com.example.workouttrackertoothbrook.ui.personal;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    private LinearLayout bodyLayout;
    private LinearLayout settingsLayout;
    private LinearLayout friendLayout;
    private LinearLayout goalLayout;
    private LinearLayout workoutLayout;
    private LinearLayout InformationLayout;

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
        bodyLayout= root.findViewById(R.id.bodyLayout);
        settingsLayout =root.findViewById(R.id.settingsLayout);
        friendLayout=root.findViewById(R.id.friendLayout);
        goalLayout=root.findViewById(R.id.goalLayout);
        workoutLayout=root.findViewById(R.id.workoutLayout);
        InformationLayout=root.findViewById(R.id.progressLayout);





        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);

        editBody.setOnClickListener(v -> {
            mViewModel.changeFragment(new bodyInformationFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.CYAN);
            settingsLayout.setBackgroundColor(Color.WHITE);
            friendLayout.setBackgroundColor(Color.WHITE);
            goalLayout.setBackgroundColor(Color.WHITE);
            workoutLayout.setBackgroundColor(Color.WHITE);
            InformationLayout.setBackgroundColor(Color.WHITE);
        });

        settings.setOnClickListener(v -> {
            mViewModel.changeFragment(new settingsFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.WHITE);
            settingsLayout.setBackgroundColor(Color.CYAN);
            friendLayout.setBackgroundColor(Color.WHITE);
            goalLayout.setBackgroundColor(Color.WHITE);
            workoutLayout.setBackgroundColor(Color.WHITE);
            InformationLayout.setBackgroundColor(Color.WHITE);
        });
        addFriend.setOnClickListener(v -> {
            mViewModel.changeFragment(new addFriendFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.WHITE);
            settingsLayout.setBackgroundColor(Color.WHITE);
            friendLayout.setBackgroundColor(Color.CYAN);
            goalLayout.setBackgroundColor(Color.WHITE);
            workoutLayout.setBackgroundColor(Color.WHITE);
            InformationLayout.setBackgroundColor(Color.WHITE);
        });
        setGoal.setOnClickListener(v -> {
            mViewModel.changeFragment(new setGoalFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.WHITE);
            settingsLayout.setBackgroundColor(Color.WHITE);
            friendLayout.setBackgroundColor(Color.WHITE);
            goalLayout.setBackgroundColor(Color.CYAN);
            workoutLayout.setBackgroundColor(Color.WHITE);
            InformationLayout.setBackgroundColor(Color.WHITE);
        });
        seeWorkouts.setOnClickListener(v -> {
            mViewModel.changeFragment(new seeWorkoutFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.WHITE);
            settingsLayout.setBackgroundColor(Color.WHITE);
            friendLayout.setBackgroundColor(Color.WHITE);
            goalLayout.setBackgroundColor(Color.WHITE);
            workoutLayout.setBackgroundColor(Color.CYAN);
            InformationLayout.setBackgroundColor(Color.WHITE);
        });
        seeInformation.setOnClickListener(v -> {
            mViewModel.changeFragment(new InformationFragment(),getActivity());
            bodyLayout.setBackgroundColor(Color.WHITE);
            settingsLayout.setBackgroundColor(Color.WHITE);
            friendLayout.setBackgroundColor(Color.WHITE);
            goalLayout.setBackgroundColor(Color.WHITE);
            workoutLayout.setBackgroundColor(Color.WHITE);
            InformationLayout.setBackgroundColor(Color.CYAN);
        });


    }

}