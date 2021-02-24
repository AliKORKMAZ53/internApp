package com.example.myapplication.roomDB;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class LocalRepository {

    //DB WORKS
    private RepoDao repoDao;
    private LiveData<List<RepoDbTable>> allTables;
    public LocalRepository(Application application) {
        AppDatabase db=AppDatabase.getDatabase(application);
        repoDao=db.repoDao();
        allTables=repoDao.getAll();
    }

    public void insertData(String owner, String repo, String url){
AppDatabase.databaseWriteExecutor.execute(()->{
    RepoDbTable repoDbTable=new RepoDbTable();
    repoDbTable.ownerName=owner;
    repoDbTable.repoName=repo;
    repoDbTable.url=url;
    repoDao.insertAll(repoDbTable);
});

    }
    public LiveData<List<RepoDbTable>> getItemsFromDatabase(){
        return allTables;
    }

    public void deleteData(String repo){
        AppDatabase.databaseWriteExecutor.execute(()->{
            repoDao.deleteOne(repo);
        });
    }
}
