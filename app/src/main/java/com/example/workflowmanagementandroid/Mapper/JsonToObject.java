package com.example.workflowmanagementandroid.Mapper;

import android.os.Build;

import androidx.constraintlayout.widget.Guideline;

import com.example.workflowmanagementandroid.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

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
            user.setName( result.getString("name"));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                user.setImg(Base64.getDecoder().decode(result.getString("img")));
            }

            return user;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
