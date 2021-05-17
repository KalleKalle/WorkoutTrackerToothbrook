package com.example.workouttrackertoothbrook.ui.social.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.Data.Group;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class groupAdapter extends RecyclerView.Adapter<groupAdapter.ViewHolder> {

    private List<HashMap> groupsUserIsMemberOf;
    private FragmentActivity fragmentActivity;

    public groupAdapter(List<HashMap> groupsUserIsMemberOf, FragmentActivity f) {
        this.groupsUserIsMemberOf = groupsUserIsMemberOf;
        fragmentActivity=f;
    }

    @NonNull
    @Override
    public groupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.group_item,parent,false);
        return new groupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull groupAdapter.ViewHolder holder, int position) {
        holder.groupName.setText(groupsUserIsMemberOf.get(position).get("name").toString());
        holder.groupName.setOnClickListener(v -> {
            FragmentTransaction trans = fragmentActivity.getSupportFragmentManager().beginTransaction();
            groupFragment groupFragment = new groupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("groupName",groupsUserIsMemberOf.get(position).get("name").toString());
            groupFragment.setArguments(bundle);
            trans.replace(R.id.nav_host_fragment,groupFragment);
            trans.commit();
        });
    }

    @Override
    public int getItemCount() {
        return groupsUserIsMemberOf.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName= itemView.findViewById(R.id.textViewGroupName);
        }
    }
}
