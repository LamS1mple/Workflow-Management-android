package com.example.workflowmanagementandroid.Model;

import java.time.LocalDateTime;

public class Work {

    private String nameWork;
    private LocalDateTime dateTimeWork;
    private Group group;
    public Work(){

    }
    public Work(String nameWork, LocalDateTime dateTimeWork, Group group) {
        this.nameWork = nameWork;
        this.dateTimeWork = dateTimeWork;
        this.group = group;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public LocalDateTime getDateTimeWork() {
        return dateTimeWork;
    }

    public void setDateTimeWork(LocalDateTime dateTimeWork) {
        this.dateTimeWork = dateTimeWork;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
