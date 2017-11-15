package com.king.batterytest.fbaselib.main.iview;

/**
 * Created by zhoukang on 2017/5/15.
 */

public interface IBaseVIew {

    void showLoading();
    void showLoading(boolean can);

    void onTimeOut();
    void dismissLoading();
    void onNetError();
    void onError(String s);
    void onFormJsonError();
    void onNoPermission();
    String getToken();
}
