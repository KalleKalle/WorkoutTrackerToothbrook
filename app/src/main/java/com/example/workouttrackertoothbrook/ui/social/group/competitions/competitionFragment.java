package com.example.workouttrackertoothbrook.ui.social.group.competitions;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.Data.Competition;
import com.example.workouttrackertoothbrook.Data.CompetitionCategories;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.social.group.groupFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class competitionFragment extends Fragment {

    private CompetitionViewModel mViewModel;
    private Button createCompetition;
    private TextView competitionType;
    private RecyclerView contestants;
    private Competition competition;
    public static competitionFragment newInstance() {
        return new competitionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.competition_fragment, container, false);
        createCompetition = root.findViewById(R.id.createCompetitionButton);
        competitionType = root.findViewById(R.id.typeofCompetition);
        contestants = root.findViewById(R.id.recyclerCompetitionContestants);
        contestants.setLayoutManager(new LinearLayoutManager(getActivity()));
        contestants.hasFixedSize();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CompetitionViewModel.class);
        String groupName = getArguments().getString("groupName");

        createCompetition.setOnClickListener(v -> {
            AlertDialog.Builder createGroupDialog = new AlertDialog.Builder(getActivity());
            ArrayList<String> types= new ArrayList<>();
            types.add(CompetitionCategories.mostKilometers);
            types.add(CompetitionCategories.mostMinutes);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,types);

            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = new Spinner(getActivity());
            spinner.setAdapter(arrayAdapter);
            createGroupDialog.setMessage("Choose Competition type");
            createGroupDialog.setTitle("Create Competition");

            createGroupDialog.setView(spinner);

            createGroupDialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int positiveButton) {
                    String type = spinner.getSelectedItem().toString();

                    mViewModel.createCompetition(type,groupName,this);
                }
            });

            createGroupDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int negativeButton) {
                }
            });

            createGroupDialog.show();
        });


        mViewModel.getCompetiton(groupName,this);



    }

    public void CompReady(Competition competitionVar,String groupName) {
        if (competitionVar!=null) {
            competition= competitionVar;
            createCompetition.setVisibility(View.GONE);
            competitionType.setText(competition.getCategory());
            mViewModel.getMembersOfGroup(groupName,this);
        }
    }

    public void categoryAndMembersReady(List<HashMap> members) {
        competitionAdapter adapter = new competitionAdapter(members, competition.getCategory());

        contestants.setAdapter(adapter);
    }

}