package com.example.workflowmanagementandroid.api;


import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
    ApiService apiService = new Retrofit.Builder()

            .baseUrl("http://192.168.122.203:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @POST("user/login-user")
    Call<ApiResponse > login(@Body User user);

    @GET("task/get-all-task-of-user/{id}")
    Call< ApiResponse > getTaskOfMember(@Path("id") long id);


}
