package com.example.workouttrackertoothbrook.ui.social.group.competitions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.Data.Competition;
import com.example.workouttrackertoothbrook.Data.CompetitionCategories;
import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.Data.kilometersCompetition;
import com.example.workouttrackertoothbrook.Data.minutesCompetition;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.social.group.groupAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class competitionAdapter extends RecyclerView.Adapter<competitionAdapter.ViewHolder> {
    List<HashMap> contestants;
    String type;
    Competition competition;

    public competitionAdapter(List<HashMap> members, String typeOfCompetition) {
        type=typeOfCompetition;
        switch (type){
            case "Most Minutes":
                competition= new minutesCompetition(type);
                break;
            default:
                competition= new kilometersCompetition(type);
                break;
        }
        this.contestants= competition.getRanking(members);

    }

    @NonNull
    @Override
    public competitionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.competition_item,parent,false);
        return new competitionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull competitionAdapter.ViewHolder holder, int position) {
        holder.placement.setText(String.valueOf(position+1));
        holder.name.setText(contestants.get(position).get("name").toString());
        switch (type){
            case "Most Minutes":
                holder.score.setText(contestants.get(position).get("workoutMinutes")+" Min");
                break;
            default:
                DecimalFormat df = new DecimalFormat("#.##");
                holder.score.setText(df.format(contestants.get(position).get("kilometers"))+" Km");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return contestants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView placement;
        TextView score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contestantName);
            placement= itemView.findViewById(R.id.contestantPlacement);
            score = itemView.findViewById(R.id.contestantScore);



        }
    }
}
