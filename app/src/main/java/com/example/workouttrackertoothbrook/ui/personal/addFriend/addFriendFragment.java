package com.example.workouttrackertoothbrook.ui.personal.addFriend;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workouttrackertoothbrook.Data.User;
import com.example.workouttrackertoothbrook.R;

public class addFriendFragment extends Fragment {

    private AddFriendViewModel mViewModel;
    private TextView friendName;
    private Button addFriend;
    private ImageView search;
    private EditText searchbar;
    private User friend;

    public static addFriendFragment newInstance() {
        return new addFriendFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.add_friend_fragment, container, false);

        friendName= root.findViewById(R.id.friendNameView);
        addFriend= root.findViewById(R.id.addFriendButton);
        search= root.findViewById(R.id.searchbutton);
        searchbar= root.findViewById(R.id.findFriendEmailAddress);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddFriendViewModel.class);
        search.setOnClickListener(v -> {
            friend= mViewModel.searchForFriend(searchbar.getText().toString());
            if(friend!=null){
                addFriend.setVisibility(View.VISIBLE);
                friendName.setText("FOUND: "+friend.getName());
            }
        });

        addFriend.setOnClickListener(v -> mViewModel.addFriend(friend));
    }

}