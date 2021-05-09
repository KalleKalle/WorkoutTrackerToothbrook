package com.example.workouttrackertoothbrook.ui.personal.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.workouttrackertoothbrook.R;

public class settingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private EditText workoutType;
    private Button addWorkouttype;

    public static settingsFragment newInstance() {
        return new settingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.settings_fragment, container, false);
        workoutType= root.findViewById(R.id.workoutType);
        addWorkouttype= root.findViewById(R.id.addWorkoutType);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        addWorkouttype.setOnClickListener(v -> mViewModel.addWorkoutType(workoutType.getText().toString()));


    }

}