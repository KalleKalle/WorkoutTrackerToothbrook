package com.example.workouttrackertoothbrook.ui.personal.seeworkouts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.Data.Workout;
import com.example.workouttrackertoothbrook.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class workoutAdapter extends RecyclerView.Adapter<workoutAdapter.ViewHolder> {

    List<Workout> workouts;

    public workoutAdapter(List<Workout> w) {
        workouts=w;
    }
    @NonNull
    @Override
    public workoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull workoutAdapter.ViewHolder holder, int position) {
        holder.typeOfWorkout.setText(workouts.get(position).getType());
        holder.duration.setText(workouts.get(position).getDuration()+"Min");
        holder.calories.setText(workouts.get(position).getCalories()+"kcal");
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeOfWorkout;
        TextView duration;
        TextView calories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeOfWorkout = itemView.findViewById(R.id.typeOfWorkout);
            duration= itemView.findViewById(R.id.durationOfWorkout);
            calories= itemView.findViewById(R.id.caloriesBurnedWorkout);
        }
    }
}
