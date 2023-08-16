package com.example.tour.answer;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.tour.answer.AnswerModel;

public class MyApplication extends Application {
    private AnswerModel sharedViewModel;

    public AnswerModel getSharedViewModel() {
        if (sharedViewModel == null) {
            sharedViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AnswerModel.class);
        }
        return sharedViewModel;
    }
}

