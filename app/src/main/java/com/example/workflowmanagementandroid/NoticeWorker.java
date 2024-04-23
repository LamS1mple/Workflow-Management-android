package com.example.workflowmanagementandroid;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.workflowmanagementandroid.Model.NoticeTask;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.api.ApiService;
import com.google.gson.Gson;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeWorker extends Worker {

    private static final String CHANNEL_ID = "NOTICE_WORK";

    private TaskMember taskMember;
    private Context context;
    public NoticeWorker(Context context, WorkerParameters parameters){
        super(context, parameters);
        this.context = context;

    }
    @NonNull
    @Override
    public Result doWork() {
        String value = getInputData().getString("taskMember");
        doNoticeWork(value);
        return Result.success();
    }

    private void doNoticeWork(String s) {
        Gson gson = new Gson();
        taskMember = gson.fromJson(s, TaskMember.class);
        long id = getInputData().getLong("idUser", 1);
        Intent notifyIntent = new Intent(context, NotificationActivity.class);
        notifyIntent.putExtra("idUser", id);
// Set the Activity to start in a new, empty task.
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent.
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                context, 0, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.note)
                .setContentIntent(notifyPendingIntent)
                .setContentText(taskMember.getContentTask() + " " + taskMember.getDateFinish().toString())
                .setContentTitle("Hãy hoàn thành nhiệm vụ")
                .setPriority(NotificationCompat.PRIORITY_MAX);


        createNotificationChannel(builder);
        NoticeTask noticeTask = new NoticeTask();
        noticeTask.setTaskMember(taskMember);
        noticeTask.setTitleNotice(taskMember.getTask().getTitle());
        noticeTask.setContentNotic(taskMember.getContentTask());

        ApiService.apiService.saveNotice(noticeTask).enqueue(new Callback<NoticeTask>() {
            @Override
            public void onResponse(Call<NoticeTask> call, Response<NoticeTask> response) {
                Log.d("thanh","thanh cong");

            }

            @Override
            public void onFailure(Call<NoticeTask> call, Throwable t) {
                Log.d("thanh", t.toString());
            }
        });
    }

    private void createNotificationChannel(NotificationCompat.Builder builder) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


            notificationManager.notify(0, builder.build());

        }
    }
}
