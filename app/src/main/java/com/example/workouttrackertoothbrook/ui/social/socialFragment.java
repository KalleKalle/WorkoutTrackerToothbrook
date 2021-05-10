package com.example.workouttrackertoothbrook.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.personal.addFriend.AddFriendViewModel;

import java.util.List;

public class socialFragment extends Fragment {

    private socialViewModel viewModel;
    private TextView groupName;
    private Button addGroup;
    private ImageView search;
    private EditText searchbar;
    private RecyclerView groupsView;
    private Group group;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_social, container, false);

        groupName= root.findViewById(R.id.groupNameView);
        addGroup= root.findViewById(R.id.addGroupButton);
        search= root.findViewById(R.id.searchGroupButton);
        searchbar= root.findViewById(R.id.findGroup);
        groupsView= root.findViewById(R.id.recycleGroup);

        groupsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupsView.hasFixedSize();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(socialViewModel.class);

        groupAdapter groupAdapter= new groupAdapter(viewModel.getGroups(),getActivity());

        groupsView.setAdapter(groupAdapter);

        search.setOnClickListener(v -> {
            group= viewModel.searchForGroup(searchbar.getText().toString());
            if(group!=null){
                addGroup.setVisibility(View.VISIBLE);
                groupName.setVisibility(View.VISIBLE);
                groupName.setText("FOUND: "+group.getName());
            }
        });

        addGroup.setOnClickListener(v -> viewModel.addGroup(group));


    }
}