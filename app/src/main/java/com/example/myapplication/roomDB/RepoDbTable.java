package com.example.myapplication.roomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RepoDbTable {
    @PrimaryKey(autoGenerate = true)
    public int repoid;

    @ColumnInfo(name = "owner_name")
    public String ownerName;

    @ColumnInfo(name = "repo_name")
    public String repoName;

    @ColumnInfo(name = "avt_url")
    public String url;

    
}

