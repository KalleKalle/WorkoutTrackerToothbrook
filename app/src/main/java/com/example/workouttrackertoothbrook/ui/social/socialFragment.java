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

        groupAdapter groupAdapter= new groupAdapter(viewModel.getGroups(),getActivity());

        groupsView.setAdapter(groupAdapter);

        search.setOnClickListener(v -> {
            group= viewModel.searchForGroup(searchbar.getText().toString());
            if(group!=null){
                addGroup.setVisibility(View.VISIBLE);
                groupNameTextView.setVisibility(View.VISIBLE);
                groupNameTextView.setText("FOUND: "+group.getName());
            }
        });

        addGroup.setOnClickListener(v -> viewModel.addGroup(group));

        createGroupButton.setOnClickListener(v -> {
            AlertDialog.Builder createGroupDialog = new AlertDialog.Builder(getActivity());
            final EditText groupNameEditText = new EditText(getActivity());
            createGroupDialog.setMessage("Enter your group name");
            createGroupDialog.setTitle("Create Group");

            createGroupDialog.setView(groupNameEditText);

            createGroupDialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int positiveButton) {
                    String groupName = groupNameEditText.getText().toString();
                    viewModel.createGroup(groupName);
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    groupFragment groupFragment = new groupFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("groupName",groupName);
                    groupFragment.setArguments(bundle);
                    trans.replace(R.id.nav_host_fragment,groupFragment);
                    trans.commit();
                }
            });

            createGroupDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int negativeButton) {
                }
            });

            createGroupDialog.show();
        });


    }
}