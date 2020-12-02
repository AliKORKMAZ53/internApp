package com.example.myapplication.network;

import com.example.myapplication.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
@GET("/{username}/repos")
    Call<List<Response>> getRepoNames(@Path("username")String name);

}
