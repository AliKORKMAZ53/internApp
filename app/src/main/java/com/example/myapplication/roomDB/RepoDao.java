package com.example.myapplication.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM RepoDbTable")
    List<RepoDbTable> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(RepoDbTable... repoDbTable);

    @Delete
    void deleteOne(RepoDbTable repoDbTable);
}
