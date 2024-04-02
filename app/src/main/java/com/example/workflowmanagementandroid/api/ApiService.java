package com.example.workflowmanagementandroid.api;


import retrofit2.Retrofit;

public interface  ApiService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.124.203:8080/")
            .build();
}
