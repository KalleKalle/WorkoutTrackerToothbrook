package com.example.workouttrackertoothbrook.ui.personal.seeworkouts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttrackertoothbrook.R;

public class seeWorkoutFragment extends Fragment {

    private SeeWorkoutViewModel mViewModel;

    public static seeWorkoutFragment newInstance() {
        return new seeWorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.see_workout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SeeWorkoutViewModel.class);
        // TODO: Use the ViewModel
    }

}