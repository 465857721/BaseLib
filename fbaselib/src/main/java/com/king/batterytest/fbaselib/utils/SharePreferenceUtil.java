package com.king.batterytest.fbaselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
    private static final String LOGINMSG = "loginmsg";
    private static final String USERNAME = "username";
    //    private static final String PSW = "psw";
    private static final String TOKEN = "token";
    private static final String SHOPNAME = "shopname";
    private static final String AUTOMATE = "automate";
    private static final String MEALBOX_FEE = "mealbox_fee";
    private static final String OUT = "out";
    private static final String APPOUT = "appout";
    private static final String AUTOOUT = "autoout";
    private static final String DESKNUM = "desknum";//桌位点餐
    private static final String RESTAURANTID = "restaurantid";//桌位点餐
    private static final String LOGOURL = "logourl";//桌位点餐
    private static final String SOUNTTYPE = "soundtype";
    private static final String USERTYPE = "usertype";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }




    //用户角色
    public void setUserType(String msg) {
        setValue(USERTYPE, msg);
    }

    public String getUserType() {
        return getValue(USERTYPE);
    }

    //声音选项   0  关 1  开启
    public void setSoundType(String msg) {
        setValue(SOUNTTYPE, msg);
    }

    public String getSoundType() {
        return getValue(SOUNTTYPE);
    }


    public void setLogourl(String msg) {
        setValue(LOGOURL, msg);
    }

    public String getLogourl() {
        return getValue(LOGOURL);
    }

    /**
     * 餐厅id
     */
    public void setRestaurantId(String msg) {
        setValue(RESTAURANTID, msg);
    }

    public String getRestaurantId() {
        return getValue(RESTAURANTID);
    }

    /**
     * 是否开启桌位点餐
     */
    public void setDesknum(String msg) {
        setValue(DESKNUM, msg);
    }

    public String getDesknum() {
        return getValue(DESKNUM);
    }

    /**
     * 是否自动接单
     */
    public void setAutomate(String msg) {
        setValue(AUTOMATE, msg);
    }

    public String getAutomate() {
        return getValue(AUTOMATE);
    }

    /**
     * mealbox_fee
     */
    public void setMealbox_fee(String msg) {
        setValue(MEALBOX_FEE, msg);
    }

    public String getMealbox_fee() {
        return getValue(MEALBOX_FEE);
    }

    /**
     * 是否外卖
     */
    public void setOut(String msg) {
        setValue(OUT, msg);
    }

    public String getOut() {
        return getValue(OUT);
    }

    /**
     * 是否外卖auto
     */
    public void setAutoOut(String msg) {
        setValue(AUTOOUT, msg);
    }

    public String getAutoOut() {
        return getValue(AUTOOUT);
    }

    /**
     * 是否外卖 app
     */
    public void setAppOut(String msg) {
        setValue(APPOUT, msg);
    }

    public String getAppOut() {
        return getValue(APPOUT);
    }

    /**
     * 登录返回的店铺名称
     *
     * @param msg
     */
    public void setShopname(String msg) {
        setValue(SHOPNAME, msg);
    }

    public String getShopname() {
        return getValue(SHOPNAME);
    }


    /**
     * 登录返回的token
     *
     * @param msg
     */
    public void setToken(String msg) {
        setValue(TOKEN, msg);
    }

    public String getToken() {
        return getValue(TOKEN);
    }

    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        return sp.getString(key, "");
    }


    /**
     * 登录返回的string
     *
     * @param msg
     */
//    public void setLoginMsg(String msg) {
//        editor.putString(LOGINMSG, msg);
//        editor.commit();
//    }
//
//    public String getLoginMsg() {
//        return sp.getString(LOGINMSG, "");
//    }
//
//
//    public void removeLoginMsg() {
//        editor.remove(LOGINMSG);
//        editor.commit();
//    }

//    /**
//     * 临时保存密码
//     */
//    public void setPsw(String msg) {
//        editor.putString(PSW, msg);
//        editor.commit();
//    }
//
//    public String getPsw() {
//        return sp.getString(PSW, "");
//    }
//
//
//    public void removePsw() {
//        editor.remove(PSW);
//        editor.commit();
//    }

    /**
     * 等登录的账号
     *
     * @param name
     */
    public void setUsername(String name) {
        editor.putString(USERNAME, name);
        editor.commit();
    }

    public String getUsername() {
        return sp.getString(USERNAME, "");
    }
}
