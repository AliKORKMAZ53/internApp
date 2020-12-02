package com.example.myapplication.network;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static String API_BASE_URL = "https://https://api.github.com/users/";

    static Gson gson=new GsonBuilder().serializeNulls().setDateFormat(DateFormat.LONG).create();
    //customizing GSON: JSON converter

    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit=builder.build();


    private static HttpLoggingInterceptor loggingInterceptor=
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);



    private static OkHttpClient.Builder httpClientBuilder= new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS);



    public static <S> S createService(Class<S> serviceClass){
        if(!httpClientBuilder.interceptors().contains(loggingInterceptor)){

            httpClientBuilder.addInterceptor(loggingInterceptor);
            builder=builder.client(httpClientBuilder.build());
            retrofit= builder.build(); //sadece server oluşturma kodu çağırıldığı zaman server
            // loggerı çalışsın için
        }

        return retrofit.create(serviceClass);
    }
}
