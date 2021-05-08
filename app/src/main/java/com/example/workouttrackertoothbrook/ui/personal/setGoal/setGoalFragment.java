package com.example.workouttrackertoothbrook.ui.personal.setGoal;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttrackertoothbrook.R;

public class setGoalFragment extends Fragment {

    private SetGoalViewModel mViewModel;

    public static setGoalFragment newInstance() {
        return new setGoalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_goal_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SetGoalViewModel.class);
        // TODO: Use the ViewModel
    }

}