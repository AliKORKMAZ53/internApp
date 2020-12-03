package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Response;
import com.example.myapplication.network.Repository;

import java.util.List;

public class RepoViewModel extends ViewModel {
    private MutableLiveData<List<Response>> mutableLiveData;
    private Repository repository;

    public void init(){
        if(mutableLiveData!=null){
            return;
        }
        repository= Repository.getInstance();
        mutableLiveData= repository.getRepos("AliKORKMAZ53");
    }
    public LiveData<List<Response>> getAllRepoNames(){
        return mutableLiveData;
    }


}
