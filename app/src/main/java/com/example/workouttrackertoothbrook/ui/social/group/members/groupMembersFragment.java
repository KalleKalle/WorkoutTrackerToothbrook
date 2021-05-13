package com.example.workouttrackertoothbrook.ui.social.group.members;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttrackertoothbrook.R;

public class groupMembersFragment extends Fragment {

    private GroupMembersViewModel mViewModel;
    private RecyclerView members;

    public static groupMembersFragment newInstance() {
        return new groupMembersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.group_members_fragment, container, false);
        members= root.findViewById(R.id.recyclerMembers);

        members.setLayoutManager(new LinearLayoutManager(getActivity()));
        members.hasFixedSize();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GroupMembersViewModel.class);
        String name = getArguments().getString("groupName");
        membersAdapter adapter= new membersAdapter(mViewModel.getMembersOfGroup(name));

        members.setAdapter(adapter);
    }

}