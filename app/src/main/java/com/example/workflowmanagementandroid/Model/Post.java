package com.example.workflowmanagementandroid.Model;

import java.util.Date;
import java.util.List;

public class Post {


    private long id;

    private Date postDate;

    private String content;


    private Group group;


    private List<Media> listMedia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Media> getListMedia() {
        return listMedia;
    }

    public void setListMedia(List<Media> listMedia) {
        this.listMedia = listMedia;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postDate=" + postDate +
                ", content='" + content + '\'' +
                ", group=" + group +
                ", listMedia=" + listMedia +
                '}';
    }
}