package com.example.workflowmanagementandroid.Model;

import java.util.Date;
import java.util.List;

public class Task extends Post{



    private Date dateFinish;

    private String title;

    private List<TaskMember> listTaskMember;

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public List<TaskMember> getListTaskMember() {
        return listTaskMember;
    }

    public void setListTaskMember(List<TaskMember> listTaskMember) {
        this.listTaskMember = listTaskMember;
    }

    public String getTitle() {
        return title;
    }

    public void setTitleTask(String titleTask) {
        this.title = titleTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "dateFinish=" + dateFinish +
                ", titleTask='" + title + '\'' +
                ", listTaskMember=" + listTaskMember +
                '}';
    }
}
