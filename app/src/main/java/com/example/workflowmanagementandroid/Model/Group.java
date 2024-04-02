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
}
