package com.example.workflowmanagementandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.example.workflowmanagementandroid.api.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView txtUserName, txtPassWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginApi();
            }
        });

    }

    private void loginApi() {
        User user = new User();
        user.setUserName(txtUserName.getText().toString().trim());
        user.setPassWord(txtPassWork.getText().toString().trim());
        ApiService.apiService.login(user).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    ApiResponse api = response.body();

                    if (api != null && api.getCode() == 1000) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        Bundle bundle = new Bundle();



                        Gson gson = new Gson();
                        bundle.putString("user", gson.toJson(api));

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, getString(R.string.fail_username_passwork), Toast.LENGTH_LONG).show();
                    }

            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void findId(){
        btn_login = findViewById(R.id.btn_login);
        txtUserName = findViewById(R.id.usename);
        txtPassWork = findViewById(R.id.password);


    }
}

