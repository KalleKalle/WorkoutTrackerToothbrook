package com.example.workouttrackertoothbrook.ui.personal.settings;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.workouttrackertoothbrook.MainActivity;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.login.Login;

public class settingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private EditText workoutType;
    private Button addWorkouttype;
    private EditText email;
    private Button saveEmail;
    private Button logout;

    public static settingsFragment newInstance() {
        return new settingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.settings_fragment, container, false);
        workoutType= root.findViewById(R.id.workoutType);
        addWorkouttype= root.findViewById(R.id.addWorkoutType);
        email= root.findViewById(R.id.emailOfUser);
        saveEmail = root.findViewById(R.id.saveEmail);
        logout= root.findViewById(R.id.logout);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        email.setText(mViewModel.getEmail());
        addWorkouttype.setOnClickListener(v -> mViewModel.addWorkoutType(workoutType.getText().toString()));
        logout.setOnClickListener(v -> {
            mViewModel.logoutUser();
            startActivity(new Intent(getActivity().getApplicationContext(), Login.class));
            getActivity().finish();
        });

        saveEmail.setOnClickListener(v ->mViewModel.SaveEmail(email.getText().toString(),getActivity()) );




    }

}