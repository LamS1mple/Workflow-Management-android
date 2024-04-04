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
import android.widget.TextView;
import android.widget.Toast;

import com.example.workflowmanagementandroid.MainActivity2;
import com.example.workflowmanagementandroid.Mapper.JsonToObject;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.NotificationActivity;
import com.example.workflowmanagementandroid.R;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.google.gson.Gson;


public class HomeFragment extends Fragment {

    private ImageView btn_notification;
    private ImageView imageUser;

    private TextView nameUser;
    private ApiResponse jsonUser;
    private User user;

    public void setApiResponse(ApiResponse apiResponse) {
        this.jsonUser = apiResponse;
    }

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



        findId(view);
        setDate();

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setDate() {
        nameUser.setText(user.getName());
        Log.d("user", user.getImg().length + "");
        Bitmap bitmap = BitmapFactory.decodeByteArray(user.getImg(), 0 , user.getImg().length);

        imageUser.setImageBitmap(bitmap);
    }

    private  void findId(View view){
        btn_notification = view.findViewById(R.id.ic_notification);
        nameUser = view.findViewById(R.id.name_user);
        imageUser = view.findViewById(R.id.img_user);
    }
}