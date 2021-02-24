package com.example.myapplication.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.myapplication.model.Response;
import com.example.myapplication.network.RemoteRepository;


import java.util.List;

public class RepoViewModel extends ViewModel {
    RemoteRepository remoteRepository;


    public RepoViewModel(RemoteRepository remoteRepository) {

        this.remoteRepository = remoteRepository;

    }

    private MutableLiveData<List<Response>> positiveLiveData =new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData=new MutableLiveData<>();

    public void initandupdate(String username){
        remoteRepository.getReposFromRepository(username, positiveLiveData, errorLiveData);
    }


    public LiveData<List<Response>> getAllRepoNames(){
        return positiveLiveData;
    }
    public LiveData<String> getError(){
        return errorLiveData;
    }


}
