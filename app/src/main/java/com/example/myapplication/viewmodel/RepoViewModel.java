package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Response;
import com.example.myapplication.network.Repository;

import java.util.List;

public class RepoViewModel extends ViewModel {
    Repository repository;
    public RepoViewModel() {
        repository=new Repository();
    }

    private MutableLiveData<List<Response>> mutableLiveData=new MutableLiveData<>();


    public void initandupdate(String username){

       // repository= Repository.getInstance();

        repository.getReposFromRepository(username,mutableLiveData);

    }


    public LiveData<List<Response>> getAllRepoNames(){

        return mutableLiveData;
    }


}
