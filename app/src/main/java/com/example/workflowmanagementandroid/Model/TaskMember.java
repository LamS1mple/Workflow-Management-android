package com.example.workflowmanagementandroid.Model;

import java.util.Date;
import java.util.List;


public class TaskMember {


    private long id;

    private Date dateFinish;

    private boolean isFinish;


    private Task task;


    private User user;


    private List<DetailTaskMember> listDetalDetailTaskMember;


    private List<Media> listMedia;
}
