package com.example.workflowmanagementandroid.Model;

public class Media {


    private long id;


    private Post post;

    private TaskMember taskMember;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public TaskMember getTaskMember() {
        return taskMember;
    }

    public void setTaskMember(TaskMember taskMember) {
        this.taskMember = taskMember;
    }
}