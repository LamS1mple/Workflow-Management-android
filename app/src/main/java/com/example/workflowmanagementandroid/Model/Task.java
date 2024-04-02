package com.example.workflowmanagementandroid.Model;

import java.util.Date;
import java.util.List;

public class Task extends Post{



    private Date dateFinish;

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
}
