package com.myself.app.model.retrofit.entity;

/**
 * 服务器返回结果
 * Created by zhujl on 2017/6/13 0013.
 */

public class BaseResultEntity<T> {

    private int errorCode;
    private String message;
    private boolean success;
    private String data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
