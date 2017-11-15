package com.king.batterytest.fbaselib.main;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.king.batterytest.fbaselib.utils.SharePreferenceUtil;


public class BaseFragmentActivity extends FragmentActivity {
    public SharePreferenceUtil spu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spu = new SharePreferenceUtil(this, "wetime");
    }
}
