package com.example.workflowmanagementandroid.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Notification {
    private int imgNotice;
    private String title;
    private String content;
    private LocalDateTime time;
    public Notification(){

    }
    public Notification(int imgNotice, String title, String content, LocalDateTime time) {
        this.imgNotice = imgNotice;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getImgNotice() {
        return imgNotice;
    }

    public void setImgNotice(int imgNotice) {
        this.imgNotice = imgNotice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
