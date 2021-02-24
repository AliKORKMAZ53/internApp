package com.example.myapplication.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.roomDB.LocalRepository;
import com.example.myapplication.roomDB.RepoDbTable;

import java.util.List;

public class DbViewModel extends ViewModel {
    private static LocalRepository localRepository;
    private final LiveData<List<RepoDbTable>> allTables;

    public DbViewModel(LocalRepository localRepository) {
        this.localRepository = localRepository;
        allTables= localRepository.getItemsFromDatabase();
    }

    public static void addFavourite(String owner, String repoName,String url){

        localRepository.insertData(owner,repoName,url);

    }
    public LiveData<List<RepoDbTable>> getFavourites(){
        return allTables;
    }

    public void deleteOneFav(String repo){
        localRepository.deleteData(repo);
    }

}
