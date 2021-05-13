package com.example.workouttrackertoothbrook.ui.personal;



import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.ui.personal.editBody.bodyInformationFragment;
import com.example.workouttrackertoothbrook.ui.personal.seeInformation.InformationFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

public class PersonalViewModel extends ViewModel {

    public void changeFragment(Fragment fragment, FragmentActivity activity) {
            FragmentTransaction trans = activity.getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.fragmentpart,fragment);
            trans.commit();
        }
    }
