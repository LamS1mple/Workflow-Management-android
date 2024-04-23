package com.example.workflowmanagementandroid.Model;

public class NoticeTask {


    private long id;

    private String titleNotice;

    private String contentNotic;


    private TaskMember taskMember;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleNotice() {
        return titleNotice;
    }

    public void setTitleNotice(String titleNotice) {
        this.titleNotice = titleNotice;
    }

    public String getContentNotic() {
        return contentNotic;
    }

    public void setContentNotic(String contentNotic) {
        this.contentNotic = contentNotic;
    }

    public TaskMember getTaskMember() {
        return taskMember;
    }

    public void setTaskMember(TaskMember taskMember) {
        this.taskMember = taskMember;
    }
}