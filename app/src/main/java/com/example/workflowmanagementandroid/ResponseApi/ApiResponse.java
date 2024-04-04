package com.example.workflowmanagementandroid.ResponseApi;

import java.io.Serializable;

public class ApiResponse{

    private int code ;
    private Object result;


    public ApiResponse(int code, Object result) {
        this.code = code;
        this.result = result;
    }
    public ApiResponse(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}