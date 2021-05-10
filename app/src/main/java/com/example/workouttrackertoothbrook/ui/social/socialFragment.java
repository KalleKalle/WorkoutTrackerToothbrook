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

import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.personal.addFriend.AddFriendViewModel;

public class socialFragment extends Fragment {

    private socialViewModel viewModel;
    private TextView groupName;
    private Button addGroup;
    private ImageView search;
    private EditText searchbar;
    Group group;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_social, container, false);

        groupName= root.findViewById(R.id.groupNameView);
        addGroup= root.findViewById(R.id.addGroupButton);
        search= root.findViewById(R.id.searchGroupButton);
        searchbar= root.findViewById(R.id.findGroup);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(socialViewModel.class);
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