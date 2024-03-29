package com.example.workflowmanagementandroid.Model;

import java.io.Serializable;

public class Group implements Serializable {
    private String nameGroup;
    private String password;
    private User user;

    private int imgGroup;

    public Group(String nameGroup, String password, User user, int imgGroup) {
        this.nameGroup = nameGroup;
        this.password = password;
        this.user = user;
        this.imgGroup = imgGroup;
    }

    public int getImgGroup() {
        return imgGroup;
    }

    public void setImgGroup(int imgGroup) {
        this.imgGroup = imgGroup;
    }

    public Group(){

    }
    public Group(String nameGroup, String password, User user) {
        this.nameGroup = nameGroup;
        this.password = password;
        this.user = user;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
