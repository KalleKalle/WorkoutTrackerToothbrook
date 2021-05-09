package com.example.workouttrackertoothbrook.ui.personal.editBody;

import androidx.lifecycle.Observer;
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
import android.widget.Toast;

import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;

public class bodyInformationFragment extends Fragment {

    private BodyInformationViewModel mViewModel;
    private EditText name;
    private EditText height;
    private EditText weight;
    private Button save;
    private EditText email;

    public static bodyInformationFragment newInstance() {
        return new bodyInformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.body_information_fragment, container, false);
        name = root.findViewById(R.id.nameOfUser);
        height= root.findViewById(R.id.heightOfUser);
        weight= root.findViewById(R.id.WeightOfUser);
        email= root.findViewById(R.id.emailOfUser);

        save= root.findViewById(R.id.saveEditInformation);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BodyInformationViewModel.class);

        name.setText(mViewModel.getName());
        height.setText(mViewModel.getHeight());
        weight.setText(mViewModel.getWeight());
        email.setText(mViewModel.getEmail());


        save.setOnClickListener(v -> {
            mViewModel.saveInformation(name.getText().toString(),height.getText().toString(),weight.getText().toString(),email.getText().toString());
            Toast toast= Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG);
            toast.show();
        });
    }

}