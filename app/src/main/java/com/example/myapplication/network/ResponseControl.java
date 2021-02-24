package com.example.myapplication.network;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Response;

public class ResponseControl {

    public void Checkpoint
            (retrofit2.Response<List<com.example.myapplication.model.Response>> response,
             MutableLiveData<List<com.example.myapplication.model.Response>> mutable,
             MutableLiveData<String> errorliveData){
        if(response!=null) {
            if (response.isSuccessful()) {
                List<com.example.myapplication.model.Response> a = response.body();
                mutable.postValue(a);
            } else {
                errorliveData.postValue(response.code()+"-"+response.message());
            }
        }else{
            errorliveData.postValue("You have a connection issue.");
        }
    }
}
