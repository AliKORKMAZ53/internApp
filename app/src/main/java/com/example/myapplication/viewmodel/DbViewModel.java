package com.example.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.roomDB.DbRepository;
import com.example.myapplication.roomDB.RepoDbTable;

import java.util.List;

public class DbViewModel extends AndroidViewModel {
    private static DbRepository dbRepository;
    private final LiveData<List<RepoDbTable>> allTables;

    public DbViewModel(Application application) {
        super(application);
        dbRepository=new DbRepository(application);
        allTables=dbRepository.getItemsFromDatabase();
    }

    public static void addFavourite(String owner, String repoName){

        dbRepository.insertData(owner,repoName);

    }
    public LiveData<List<RepoDbTable>> getFavourites(){
        return allTables;
    }

}
