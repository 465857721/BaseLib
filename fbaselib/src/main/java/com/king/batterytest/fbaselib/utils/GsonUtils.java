package com.king.batterytest.fbaselib.utils;

import com.google.gson.Gson;

/**
 * Created by alvis on 2017/4/13.
 */

public class GsonUtils {
    private static Gson instance = new Gson();

    private GsonUtils() {
    }

    public static Gson getGsonInstance() {
        return instance;
    }
}
