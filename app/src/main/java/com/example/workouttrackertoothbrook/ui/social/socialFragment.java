package com.example.workouttrackertoothbrook.ui.social;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.social.group.groupAdapter;
import com.example.workouttrackertoothbrook.ui.social.group.groupFragment;

import java.util.HashMap;
import java.util.List;

public class socialFragment extends Fragment {

    private socialViewModel viewModel;
    private TextView groupNameTextView;
    private Button addGroup;
    private ImageView search;
    private EditText searchbar;
    private RecyclerView groupsView;
    private Group group;
    private Button createGroupButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_social, container, false);

        groupNameTextView = root.findViewById(R.id.groupNameView);
        addGroup= root.findViewById(R.id.addGroupButton);
        search= root.findViewById(R.id.searchGroupButton);
        searchbar= root.findViewById(R.id.findGroup);
        groupsView= root.findViewById(R.id.recycleGroup);
        createGroupButton= root.findViewById(R.id.CreateGroupbutton);

        groupsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupsView.hasFixedSize();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(socialViewModel.class);


        viewModel.getGroups(this);

        search.setOnClickListener(v -> {
            viewModel.searchForGroup(searchbar.getText().toString(),this);

        });

        addGroup.setOnClickListener(v -> viewModel.addGroup(group, true));

        createGroupButton.setOnClickListener(v -> {
            AlertDialog.Builder createGroupDialog = new AlertDialog.Builder(getActivity());
            final EditText groupNameEditText = new EditText(getActivity());
            createGroupDialog.setMessage(getString(R.string.entergroupname));
            createGroupDialog.setTitle(getString(R.string.createGroup));

            createGroupDialog.setView(groupNameEditText);

            createGroupDialog.setPositiveButton(getString(R.string.create), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int positiveButton) {
                    String groupName = groupNameEditText.getText().toString();
                    viewModel.createGroup(groupName,socialFragment.this);
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    groupFragment groupFragment = new groupFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("groupName",groupName);
                    groupFragment.setArguments(bundle);
                    trans.replace(R.id.nav_host_fragment,groupFragment);
                    trans.commit();
                }
            });

            createGroupDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int negativeButton) {
                }
            });

            createGroupDialog.show();
        });


    }

    public void groupsUserMemberOfReady(List<HashMap> groups) {
        groupAdapter groupAdapter= new groupAdapter(groups,getActivity());

        groupsView.setAdapter(groupAdapter);
    }

    public void groupReady(Group Group) {
        this.group= Group;
        if(group!=null){
            addGroup.setVisibility(View.VISIBLE);
            groupNameTextView.setVisibility(View.VISIBLE);
            groupNameTextView.setText(getString(R.string.foundgroup) + group.getName());
        }
        else {
            groupNameTextView.setText(getString(R.string.nogroupFound));
        }
    }
}