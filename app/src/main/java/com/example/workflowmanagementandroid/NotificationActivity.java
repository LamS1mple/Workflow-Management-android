package com.example.workflowmanagementandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.workflowmanagementandroid.Adapter.ListNoticeAdapter;
import com.example.workflowmanagementandroid.Model.NoticeTask;
import com.example.workflowmanagementandroid.Model.Notification;
import com.example.workflowmanagementandroid.api.ApiService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView recyclerView;

    private ListNoticeAdapter listNoticeAdapter;

    private List<NoticeTask> listNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        findId();
        click();






    }
    private void findId(){
        btnBack = findViewById(R.id.ic_back);
        recyclerView = findViewById(R.id.list_notice);
        listNoticeAdapter = new ListNoticeAdapter();
        listNotice = new ArrayList<>();


        listNoticeAdapter.setList(listNotice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listNoticeAdapter);

        long id = getIntent().getLongExtra("idUser", 0);
        ApiService.apiService.getAllNotice(id).enqueue(new Callback<List<NoticeTask>>() {
            @Override
            public void onResponse(Call<List<NoticeTask>> call, Response<List<NoticeTask>> response) {
                List<NoticeTask> list = response.body();
                listNoticeAdapter.setList(list);
            }

            @Override
            public void onFailure(Call<List<NoticeTask>> call, Throwable t) {

            }
        });
    }

    private void click(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}