package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Response;
import com.example.myapplication.network.Repository;

import java.util.List;

public class RepoViewModel extends ViewModel {
    private MutableLiveData<List<Response>> mutableLiveData=new MutableLiveData<>();
    private Repository repository= new Repository();

    public void initanduptade(String username){

            mutableLiveData= repository.getReposFromRepository(username);

      //  repository= Repository.getInstance();

    }


    public LiveData<List<Response>> getAllRepoNames(){
        return mutableLiveData;
    }


}
