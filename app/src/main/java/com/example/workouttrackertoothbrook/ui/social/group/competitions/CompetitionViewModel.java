package com.example.workouttrackertoothbrook.ui.social.group.competitions;

import com.example.workouttrackertoothbrook.Data.Competition;
import com.example.workouttrackertoothbrook.Data.CompetitionCategories;
import com.example.workouttrackertoothbrook.Data.Network;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.kilometersCompetition;
import com.example.workouttrackertoothbrook.Data.minutesCompetition;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class CompetitionViewModel extends ViewModel {
    Network network;

    public CompetitionViewModel() {
        network= new Network();
    }

    public Competition getCompetiton(String groupName) {
        return network.findCompetition(groupName);
    }

    public Competition createCompetition(String type,String groupName) {
        Competition competition;
        switch (type){
            case "Most Minutes":
                competition= new minutesCompetition(CompetitionCategories.mostMinutes);
                break;
            default:
                competition= new kilometersCompetition(CompetitionCategories.mostKilometers);
                break;
        }
        network.createCompetition(competition,groupName);
        return competition;
    }

    public List<User> getMembersOfGroup(String groupName) {
        return network.searchforGroup(groupName).getMembers();
    }
}