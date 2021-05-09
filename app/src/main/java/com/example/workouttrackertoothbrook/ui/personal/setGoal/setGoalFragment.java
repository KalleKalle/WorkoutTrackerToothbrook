package com.example.workouttrackertoothbrook.ui.personal.setGoal;

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

public class setGoalFragment extends Fragment {

    private SetGoalViewModel mViewModel;

    public static setGoalFragment newInstance() {
        return new setGoalFragment();
    }

    private EditText idealWeight;
    private EditText idealKm;
    private EditText idealKcal;
    private Button save;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.set_goal_fragment, container, false);
        idealKcal= root.findViewById(R.id.target_Kcal);
        idealKm= root.findViewById(R.id.targetKm);
        idealWeight= root.findViewById(R.id.idealWeightOfUser);
        save= root.findViewById(R.id.saveGoal);

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SetGoalViewModel.class);

        idealWeight.setText(mViewModel.getTWeight());
        idealKcal.setText(mViewModel.getTKcal());
        idealKm.setText(mViewModel.getTKm());

        save.setOnClickListener(v -> {
            mViewModel.saveGoals(idealKm.getText().toString(),idealKcal.getText().toString(),idealWeight.getText().toString());
        });


    }

}