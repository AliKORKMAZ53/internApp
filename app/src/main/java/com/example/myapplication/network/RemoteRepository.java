package com.example.myapplication.network;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.myapplication.model.Response;
import com.example.myapplication.roomDB.AppDatabase;
import com.example.myapplication.roomDB.RepoDao;
import com.example.myapplication.roomDB.RepoDbTable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RemoteRepository {


    //API WORKS
    Api client = RetrofitService.createService(Api.class);


    public void getReposFromRepository(String username, MutableLiveData<List<Response>> mutable){


        client.getRepoNames(username).enqueue(new Callback<List<Response>>() {

            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                List<Response> a=response.body();
                if(response.isSuccessful()){
                    Log.d("ARRIVALDATA:","CAME");
                    mutable.postValue(a);
                    Log.d("ARRIVALDATA:","GONE - IsEmpty:"+a.isEmpty());
                }else{
                    mutable.postValue(null);

                    Log.d("ARRIVALDATA:","GONE - error code:"+response.code());

                }


            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                    mutable.postValue(null);
                Log.d("ARRIVALDATA:","onFAILURE");

            }
        });

    }
}
