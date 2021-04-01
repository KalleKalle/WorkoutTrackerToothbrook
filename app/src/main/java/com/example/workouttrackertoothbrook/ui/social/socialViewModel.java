package com.example.workouttrackertoothbrook.ui.social;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class socialViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public socialViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is social fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}