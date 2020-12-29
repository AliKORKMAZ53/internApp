package com.example.myapplication.roomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM RepoDbTable")
    LiveData<List<RepoDbTable>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(RepoDbTable... repoDbTable);

    @Query("DELETE FROM RepoDbTable WHERE repo_name=:repo")
    void deleteOne(String repo);
}
