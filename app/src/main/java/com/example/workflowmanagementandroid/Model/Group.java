package com.example.workflowmanagementandroid.Model;

import java.io.Serializable;
import java.util.List;


public class Group {

    private long id;

    private String nameGroup;

    private String passWordGroup;


    private User hostGroup;


    private List<GroupMember> listMembers;


    private List<Post> listPosts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getPassWordGroup() {
        return passWordGroup;
    }

    public void setPassWordGroup(String passWordGroup) {
        this.passWordGroup = passWordGroup;
    }

    public User getHostGroup() {
        return hostGroup;
    }

    public void setHostGroup(User hostGroup) {
        this.hostGroup = hostGroup;
    }

    public List<GroupMember> getListMembers() {
        return listMembers;
    }

    public void setListMembers(List<GroupMember> listMembers) {
        this.listMembers = listMembers;
    }

    public List<Post> getListPosts() {
        return listPosts;
    }

    public void setListPosts(List<Post> listPosts) {
        this.listPosts = listPosts;
    }
}
