package com.king.batterytest.fbaselib.main.model;

/**
 * Created by zhoukang on 2017/5/26.
 */

public class BaseDataBean<T> {
    private int error;
    private String msg;
    private T data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
