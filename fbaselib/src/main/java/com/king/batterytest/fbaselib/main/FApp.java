package com.king.batterytest.fbaselib.main;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.king.batterytest.fbaselib.utils.MLoggerInterceptor;
import com.king.batterytest.fbaselib.utils.SharePreferenceUtil;
import com.king.batterytest.fbaselib.utils.SignCheck;
import com.king.batterytest.fbaselib.utils.Tools;
import com.king.batterytest.fbaselib.view.MyMaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.os.Process.killProcess;

/**
 * Created by zhoukang on 2017/4/12.
 */

public class FApp extends Application {
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    private SharePreferenceUtil spu;
    private static FApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList<String> keys = new ArrayList<>();

        if (isApkInDebug(this)) {
            keys.add("41:C2:55:46:96:1E:86:A8:FC:21:77:2C:77:37:6C:C9:30:41:C9:FA");//test
        }

        boolean checked = false;
        for (String s : keys) {
            SignCheck signCheck = new SignCheck(this, s);
            if (signCheck.check()) {
                checked = true;
            }
        }
        if (!checked) {
            System.exit(0);
            killProcess(android.os.Process.myPid());
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MLoggerInterceptor("http", true))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        oList = new ArrayList<>();
        spu = Tools.getSpu(this);
        instance = this;


    }

    static {

        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MyMaterialHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {

                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    public static FApp getInstance() {
        return instance;
    }



    public void addActivity(Activity activity) {

        if (!oList.contains(activity)) {
            oList.add(activity);
        }
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }


    public void removeActivity(Activity activity) {

        if (oList.contains(activity)) {
            oList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }


    public void removeALLActivity() {

        for (Activity activity : oList) {
            activity.finish();
        }
    }
}

