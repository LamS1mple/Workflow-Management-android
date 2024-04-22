package com.example.workflowmanagementandroid.api;


import com.example.workflowmanagementandroid.Model.Group;
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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()

            .baseUrl("http://192.168.77.203:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @POST("user/login-user")
    Call<ApiResponse > login(@Body User user);

    @GET("task/get-all-task-of-user/{id}/{check}")
    Call< ApiResponse > getTaskOfMember(@Path("id") long id,
                                              @Path("check") boolean check);
        @POST("task/get-task-after-schedule")
    Call< ApiResponse > getTaskAfter(@Query("id") long id);

    @POST("task/get-task-ahead-schedule")
    Call< ApiResponse > getTaskAhead(@Query("id") long id);

    @POST("task/save-task-member")
    Call<ApiResponse> saveTaskMember(@Body TaskMember taskMember);

    @GET("group/get-group/{id}")
    Call<List<Group>> getGroup(@Path("id")long id);

    @GET("group/get-group-{id}")
    Call<Group> getZoom(@Path("id")long id);
}
