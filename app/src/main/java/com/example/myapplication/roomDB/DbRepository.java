package com.example.myapplication.roomDB;

import android.app.Activity;
import android.app.Application;

import androidx.room.Room;

public class DbRepository {
    //DB WORKS
    private RepoDao repoDao;
    public DbRepository(Application application) {
        AppDatabase db=AppDatabase.getDatabase(application);
        repoDao=db.repoDao();
    }

    public void getRepoFromViewModel(String owner,String repo){
AppDatabase.databaseWriteExecutor.execute(()->{
    RepoDbTable repoDbTable=new RepoDbTable();
    repoDbTable.ownerName=owner;
    repoDbTable.repoName=repo;
    repoDao.insertAll(repoDbTable);
});

    }
}
