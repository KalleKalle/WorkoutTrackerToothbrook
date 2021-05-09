package com.example.workouttrackertoothbrook.ui.personal.seeworkouts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttrackertoothbrook.R;

public class seeWorkoutFragment extends Fragment {

    private SeeWorkoutViewModel mViewModel;
    private RecyclerView workouts;

    public static seeWorkoutFragment newInstance() {
        return new seeWorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.see_workout_fragment, container, false);

        workouts= root.findViewById(R.id.workoutRecycler);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SeeWorkoutViewModel.class);
        workouts.setLayoutManager(new LinearLayoutManager(getActivity()));
        workouts.hasFixedSize();

        workoutAdapter workoutAdapter= new workoutAdapter(mViewModel.getModel().getWorkouts());

        workouts.setAdapter(workoutAdapter);
    }

}