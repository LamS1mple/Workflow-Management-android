package com.example.workflowmanagementandroid.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workflowmanagementandroid.MainActivity2;
import com.example.workflowmanagementandroid.Mapper.JsonToObject;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.NotificationActivity;
import com.example.workflowmanagementandroid.R;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.example.workflowmanagementandroid.api.ApiService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private ImageView btn_notification;
    private ImageView imageUser;

    private TextView nameUser;

    private List<TaskMember> taskMemberList;
    private User user;
    private ProgressBar progressBar;

    private TextView checkTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String api = (String) getArguments().getString("user");

        user = JsonToObject.getInstance().jsonToUser(api);

        Log.d("userid", user.getId() + "");
        ApiService.apiService.getTaskOfMember(user.getId() ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse =  response.body();

                if (apiResponse != null && apiResponse.getCode() == 1000){
                    progressBar.setVisibility(View.INVISIBLE);

                    Gson gson = new Gson();
                    taskMemberList = JsonToObject.getInstance()
                            .jsonToListTaskMember(gson.toJson(apiResponse));
                    if (taskMemberList == null){
                        checkTask.setVisibility(View.VISIBLE);
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

        findId(view);
        setDate();

        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });
    }

    private void setDate() {
        nameUser.setText(user.getName());
        Log.d("user", user.getImg().length + "");
        if (user.getImg() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getImg(), 0 , user.getImg().length);
            imageUser.setImageBitmap(bitmap);
        }

    }

    private  void findId(View view){
        btn_notification = view.findViewById(R.id.ic_notification);
        nameUser = view.findViewById(R.id.name_user);
        imageUser = view.findViewById(R.id.img_user);
        progressBar = view.findViewById(R.id.loadingTask);
        checkTask = view.findViewById(R.id.check_task);
    }
}