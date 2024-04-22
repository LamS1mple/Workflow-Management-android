package com.example.workflowmanagementandroid;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NoticeWorker extends Worker {

    private static final String CHANNEL_ID = "NOTICE_WORK";
    private Context context;
    public NoticeWorker(Context context, WorkerParameters parameters){
        super(context, parameters);
        this.context = context;

    }
    @NonNull
    @Override
    public Result doWork() {
        doNoticeWork();
        return Result.retry();
    }

    private void doNoticeWork() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.note)
                .setContentText("Sắp đến hạn")
                .setContentTitle("Work")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
