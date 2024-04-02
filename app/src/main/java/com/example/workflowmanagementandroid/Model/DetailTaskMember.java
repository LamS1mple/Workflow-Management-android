package com.example.workflowmanagementandroid.Model;

import java.util.Date;

public class DetailTaskMember {


    private long id;

    private Date dateFinish;

    private String titleTask;

    private String contentTask;

    private boolean isFinish;

    private TaskMember taskMember;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public String getContentTask() {
        return contentTask;
    }

    public void setContentTask(String contentTask) {
        this.contentTask = contentTask;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public TaskMember getTaskMember() {
        return taskMember;
    }

    public void setTaskMember(TaskMember taskMember) {
        this.taskMember = taskMember;
    }
}