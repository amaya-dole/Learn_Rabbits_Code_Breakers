package com.example.timetable.ui.Timetable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TimetableViewModel {
    private MutableLiveData<String> mText;

    public TimetableViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Timetable fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
