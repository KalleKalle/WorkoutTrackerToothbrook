package com.example.workouttrackertoothbrook.ui.dashboard;

import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workouttrackertoothbrook.R;

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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(com.example.workouttrackertoothbrook.ui.dashboard.dashboardViewModel.class);

        dashboardViewModel.getModel().observe(getViewLifecycleOwner(), workoutModel -> {
            prevMinutes.setText("Last week: "+dashboardViewModel.getLastWeekTimeString());
            minutes.setText("This week "+dashboardViewModel.getTimeString());
            kilometers.setText("This week: "+dashboardViewModel.getKilometers().getValue().toString()+"Km");
            prevKilometers.setText("Last week: "+dashboardViewModel.getPreviousKilometers().getValue().toString()+"Km");
            averageKilometers.setText("Avg Distance: "+dashboardViewModel.getAverageKilometers().getValue().toString()+"Km");
            averageMinutes.setText("Avg time: "+dashboardViewModel.getAvgTimeString());

        });

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




        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,dashboardViewModel.getWorkoutTypes());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        addWorkout.setOnClickListener(v -> {
            dashboardViewModel.addWorkout(spinner.getSelectedItem().toString(),
                    Integer.parseInt(editMinutes.getText().toString()),
                    Integer.parseInt(editReps.getText().toString()));
            editReps.setText("");
            editMinutes.setText("");
            //minutes.setText("This week "+dashboardViewModel.getTimeString());
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Workout added", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();


        });

       /* prevMinutes.setText(dashboardViewModel.getPreviousMinutes().getValue().toString());
        minutes.setText(dashboardViewModel.getMinutes().getValue().toString());
        kilometers.setText(dashboardViewModel.getKilometers().getValue().toString());
        prevKilometers.setText(dashboardViewModel.getPreviousKilometers().getValue().toString());

        */
        //final TextView textView = root.findViewById(R.id.text_dashboard);

        return root;
    }

}