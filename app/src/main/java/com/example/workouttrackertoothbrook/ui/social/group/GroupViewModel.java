package com.example.workouttrackertoothbrook.ui.social.group;

import android.os.Bundle;

import com.example.workouttrackertoothbrook.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

public class GroupViewModel extends ViewModel {

    public void changeFragment(Fragment fragment, FragmentActivity activity, Bundle values) {
        FragmentTransaction trans = activity.getSupportFragmentManager().beginTransaction();
        fragment.setArguments(values);
        trans.replace(R.id.groupSubFragment,fragment);
        trans.commit();
    }

    public void back(FragmentActivity activity) {

    }
}