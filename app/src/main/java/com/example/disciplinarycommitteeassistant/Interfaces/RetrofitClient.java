package com.example.disciplinarycommitteeassistant.Interfaces;

import com.example.disciplinarycommitteeassistant.Interfaces.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;
    Gson gson = new GsonBuilder().setLenient().create();
    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        return getInstance(false);
    }

    public static synchronized RetrofitClient getInstance(boolean flag) {
        if(flag){
            instance = new RetrofitClient();
        }
        else if(instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }
    public Api getMyApi(){
        return myApi;
    }

}
