package com.example.workouttrackertoothbrook.ui.tracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class trackerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public trackerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tracker fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}