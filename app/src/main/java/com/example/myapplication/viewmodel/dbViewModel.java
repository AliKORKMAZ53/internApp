package com.example.myapplication.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.MainActivity;
import com.example.myapplication.roomDB.DbRepository;

public class dbViewModel extends AndroidViewModel {
    private static DbRepository dbRepository;


    public dbViewModel(Application application) {
        super(application);
        dbRepository=new DbRepository(application);

    }

    public static void addFavourite(String owner, String repoName){

        dbRepository.getRepoFromViewModel(owner,repoName);

    }
}
