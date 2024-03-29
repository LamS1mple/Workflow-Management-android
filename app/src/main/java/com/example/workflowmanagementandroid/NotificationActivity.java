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
import com.example.workflowmanagementandroid.Model.Notification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView recyclerView;

    private ListNoticeAdapter listNoticeAdapter;

    private List<Notification> listNotice;
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

        listNotice.add(new Notification(R.drawable.edit_icon_user, "Đăng bài mới"
                , "Hôm nay chúng ta sẽ họp vào 7h tối"
                , LocalDateTime.of(2020, 12, 12,10, 10)));
        listNotice.add(new Notification(R.drawable.edit_icon_user, "Giao nhiệm vụ"
                , "Hãy hoàn thành nhiệm vụ"
                , LocalDateTime.of(2022, 1, 12,10, 10)));
        listNotice.add(new Notification(R.drawable.edit_icon_user, "Deadline đang đến"
                , "Hết hạn vào lúc 10h"
                , LocalDateTime.of(2020, 12, 12,10, 10)));

        listNoticeAdapter.setList(listNotice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listNoticeAdapter);
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