package com.example.myapplication.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Repository {
    private static Repository repository;

    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }

    Api client = RetrofitService.createService(Api.class);


    public MutableLiveData<List<Response>> getRepos(String username){
        MutableLiveData<List<Response>> repoData=new MutableLiveData<>();
        client.getRepoNames(username).enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                if(response.isSuccessful()){
                    Log.d("ARRIVALDATA:","CAME");
                    List<Response> a=response.body();
                    repoData.postValue(a);
                    Log.d("ARRIVALDATA:","GONE "+a.isEmpty());
                }
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                    repoData.setValue(null);

            }
        });
        return repoData;
    }
}
