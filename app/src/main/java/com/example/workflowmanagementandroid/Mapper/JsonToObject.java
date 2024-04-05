package com.example.workflowmanagementandroid.Mapper;

import android.os.Build;

import androidx.constraintlayout.widget.Guideline;

import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.Task;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JsonToObject {

    private static JsonToObject instance;

    public static JsonToObject getInstance(){
        if (instance == null){
            instance = new JsonToObject();
        }
        return instance;
    }

    public User jsonToUser(String object){

        try {
            User user = new User();
            JSONObject jsonObject = new JSONObject(object);
            JSONObject result = jsonObject.getJSONObject("result");
            user.setId(result.getInt("id"));
            user.setName( result.getString("name"));

            if (!result.isNull("img") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                user.setImg(Base64.getDecoder().decode(result.getString("img")));
            }

            return user;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public Task jsonToTask(String s){
//        try {
//
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
    public List<TaskMember> jsonToListTaskMember(String s){
        try {

            List<TaskMember> list = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            int size = jsonArray.length();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            for (int i = 0 ;  i < size ; i ++){
                TaskMember taskMember = new TaskMember();

                JSONObject item = jsonArray.getJSONObject(i);
                taskMember.setId(item.getInt("id"));
                taskMember.setContent(item.getString("contentTask"));
                taskMember.setFinish(item.getBoolean("finish"));
                taskMember.setDateFinish(dateFormat.parse(item.getString("dateFinish")));

                Task task = new Task();
                JSONObject jsonTask = item.getJSONObject("task");
                task.setId(jsonTask.getInt("id"));

                task.setPostDate( dateFormat.parse( jsonTask.getString("postDate")) );

                Group group = new Group();
                JSONObject jsonGroup = jsonTask.getJSONObject("group");

                group.setId(jsonGroup.getInt("id"));
                group.setNameGroup(jsonGroup.getString("nameGroup"));

                task.setGroup(group);

                taskMember.setTask(task);

                list.add(taskMember);

            }
            return list;
        } catch (JSONException e) {
                throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
