package com.example.workouttrackertoothbrook.ui.personal.seeInformation;

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

public class InformationFragment extends Fragment {

    private InformationViewModel mViewModel;
    private TextView kmGoal;
    private TextView kCalGoal;
    private TextView getkCalBurnedWeek;
    private TextView bmi;
    private TextView weightGoal;
    private TextView weight;
    private TextView height;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.information_fragment, container, false);

        height= root.findViewById(R.id.heightStatus);
        weight= root.findViewById(R.id.weightStatus);
        weightGoal= root.findViewById(R.id.targetWeightStatus);
        bmi= root.findViewById(R.id.bmiStatus);
        getkCalBurnedWeek= root.findViewById(R.id.kCalburnedWeek);

        kCalGoal= root.findViewById(R.id.KcalGoal);
        kmGoal= root.findViewById(R.id.kmGoal);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InformationViewModel.class);
        height.setText(mViewModel.getHeight(getContext()));
        weight.setText(mViewModel.getWeight(getContext()));
        weightGoal.setText(mViewModel.getWeightGoal(getContext()));
        bmi.setText(mViewModel.getBMI());
        getkCalBurnedWeek.setText(mViewModel.getKCalBurnedWeek());
        kCalGoal.setText(mViewModel.getKCalGoal(getContext()));
        kmGoal.setText(mViewModel.getKmGoal(getContext()));
    }

}