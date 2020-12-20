package com.example.myapplication.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Repository {
  /*  private static Repository repository;

    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
*/
    Api client = RetrofitService.createService(Api.class);


    public void getReposFromRepository(String username, MutableLiveData<List<Response>> mutable){


        client.getRepoNames(username).enqueue(new Callback<List<Response>>() {

            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                List<Response> a=response.body();
                if(response.isSuccessful()){
                    Log.d("ARRIVALDATA:","CAME");
                    //List<Response> a=response.body();
                    Log.d("ARRIVALDATA:","GONE - IsEmpty:"+a.isEmpty());
                    mutable.postValue(a);
                }else{
                    mutable.postValue(null);

                    Log.d("ARRIVALDATA:","GONE - in else"+response.code());

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
