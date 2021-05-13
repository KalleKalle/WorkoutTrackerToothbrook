package com.example.workouttrackertoothbrook.ui.social.group.members;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.personal.seeworkouts.workoutAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class membersAdapter extends RecyclerView.Adapter<membersAdapter.ViewHolder> {
    private ArrayList<User> members;
    public membersAdapter(ArrayList<User> Members) {
        members=Members;
    }

    @NonNull
    @Override
    public membersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.member_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull membersAdapter.ViewHolder holder, int position) {
        holder.name.setText(members.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.memberName);

        }
    }
}
