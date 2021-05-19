package com.example.workouttrackertoothbrook.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class dashboardFragment extends Fragment {

    private com.example.workouttrackertoothbrook.ui.dashboard.dashboardViewModel dashboardViewModel;
    TextView minutes;
    TextView prevMinutes;
    TextView averageKilometers;
    TextView averageMinutes;
    TextView kilometers;
    TextView prevKilometers;
    Spinner spinner;
    Button addWorkout;
    EditText editMinutes;
    EditText editReps;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userId;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        spinner = root.findViewById(R.id.workoutname);
        minutes = root.findViewById(R.id.Minutesworkedout);
        prevMinutes= root.findViewById(R.id.minutespreviousweek);
        averageKilometers= root.findViewById(R.id.averageKilometers);
        averageMinutes= root.findViewById(R.id.minuteaverage);
        kilometers= root.findViewById(R.id.kilometerstraversed);
        prevKilometers= root.findViewById(R.id.kilometersPreviousWeek);
        editMinutes = root.findViewById(R.id.editMinutes);
        editReps= root.findViewById(R.id.editWorkoutReps);
        addWorkout = root.findViewById(R.id.AddWorkoutButton);

        firebaseAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userId = firebaseAuth.getUid();







       /* prevMinutes.setText(dashboardViewModel.getPreviousMinutes().getValue().toString());
        minutes.setText(dashboardViewModel.getMinutes().getValue().toString());
        kilometers.setText(dashboardViewModel.getKilometers().getValue().toString());
        prevKilometers.setText(dashboardViewModel.getPreviousKilometers().getValue().toString());

        */
        //final TextView textView = root.findViewById(R.id.text_dashboard);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dashboardViewModel = new ViewModelProvider(this).get(com.example.workouttrackertoothbrook.ui.dashboard.dashboardViewModel.class);

        dashboardViewModel.getModel().observe(getActivity(), new Observer<workoutModel>() {
            @Override
            public void onChanged(workoutModel workoutModel) {
                prevMinutes.setText(dashboardViewModel.getLastWeekTimeString(getContext()));
                minutes.setText(dashboardViewModel.getTimeString(getContext()));
                kilometers.setText(dashboardViewModel.getKilometers(getContext()).getValue());
                prevKilometers.setText(dashboardViewModel.getPreviousKilometers(getContext()).getValue());
                averageKilometers.setText(dashboardViewModel.getAverageKilometers(getContext()).getValue());
                averageMinutes.setText(dashboardViewModel.getAvgTimeString(getContext()));

            }
        });

        DocumentReference documentReference= firestore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value!=null) {
                    try {
                        dashboardViewModel.loadModel(value, getContext());
                        prevMinutes.setText(dashboardViewModel.getLastWeekTimeString(getContext()));
                        minutes.setText(dashboardViewModel.getTimeString(getContext()));

                        kilometers.setText(dashboardViewModel.getKilometers(getActivity()).getValue());
                        prevKilometers.setText(dashboardViewModel.getPreviousKilometers(getActivity()).getValue());
                        averageKilometers.setText(dashboardViewModel.getAverageKilometers(getActivity()).getValue());
                        averageMinutes.setText(dashboardViewModel.getAvgTimeString(getActivity()));
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, dashboardViewModel.getWorkoutTypes());
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(arrayAdapter);
                    }catch (NullPointerException ignored){
                    }
                }
            }
        });



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item,dashboardViewModel.getWorkoutTypes());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        addWorkout.setOnClickListener(v -> {
            dashboardViewModel.addWorkout(spinner.getSelectedItem().toString(),
                    editMinutes.getText().toString(),
                    editReps.getText().toString());
            editReps.setText("");
            editMinutes.setText("");
            minutes.setText(dashboardViewModel.getTimeString(getContext()));
            Toast.makeText(getActivity(), getActivity().getString(R.string.workoutAdded), Toast.LENGTH_LONG).show();
        });
    }

}