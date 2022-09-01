package com.menuutama.projectaplikasimayora.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {



    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("Welcome, To run the production process, you must fulfill all productivity data by scanning or generate the barcode and please fill out all the report sheets in this application");
    }

    public LiveData<String> getText() {
        return mText;
    }
}