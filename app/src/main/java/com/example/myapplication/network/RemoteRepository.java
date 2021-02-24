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
    ResponseControl responseControl=new ResponseControl();

    public void getReposFromRepository(String username, MutableLiveData<List<Response>> positiveMutable,MutableLiveData<String> errorLiveData){


        client.getRepoNames(username).enqueue(new Callback<List<Response>>() {

            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
         /*      List<Response> a=response.body();
                if(response.isSuccessful()){
                    mutable.postValue(a);
                    Log.d("ARRIVALDATA:","GONE - IsEmpty:"+a.isEmpty());
                }else{
                    mutable.postValue(null);
                    Log.d("ARRIVALDATA:","GONE - error code:"+response.code());
                }
*/
            responseControl.Checkpoint(response,positiveMutable,errorLiveData);

            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                responseControl.Checkpoint(null,positiveMutable,errorLiveData);
                Log.d("ARRIVALDATA:",t.getMessage());

            }
        });

    }
}
