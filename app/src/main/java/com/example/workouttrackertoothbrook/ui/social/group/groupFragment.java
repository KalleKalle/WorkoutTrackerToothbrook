package com.example.workouttrackertoothbrook.ui.social.group;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.social.group.competitions.competitionFragment;
import com.example.workouttrackertoothbrook.ui.social.group.members.groupMembersFragment;
import com.example.workouttrackertoothbrook.ui.social.socialFragment;

public class groupFragment extends Fragment {

    private GroupViewModel mViewModel;
    private TextView title;
    private ImageView competitions;
    private ImageView members;
    private ImageView back;
    private String groupName;

    public static groupFragment newInstance() {
        return new groupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.group_fragment, container, false);
        groupName = getArguments().getString("groupName");
        title = root.findViewById(R.id.groupNameTitle);
        title.setText("Welcome to "+ groupName);

        competitions = root.findViewById(R.id.competition);
        members = root.findViewById(R.id.members);
        back = root.findViewById(R.id.backToGroup);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        mViewModel.removebackfragments(getActivity().getSupportFragmentManager());

        back.setOnClickListener(v -> {
            mViewModel.back(getActivity());
        });


        Bundle values= new Bundle();
        values.putString("groupName",groupName);
        competitions.setOnClickListener(v -> {
            mViewModel.changeFragment(new competitionFragment(),getActivity(), values);
            competitions.setBackgroundColor(Color.CYAN);
            title.setVisibility(View.GONE);
            members.setBackgroundColor(0);
        });


        members.setOnClickListener(v -> {
            mViewModel.changeFragment(new groupMembersFragment(),getActivity(),values);
            members.setBackgroundColor(Color.CYAN);
            title.setVisibility(View.GONE);
            competitions.setBackgroundColor(0);
        });
    }

}