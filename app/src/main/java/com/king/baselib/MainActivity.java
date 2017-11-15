package com.king.baselib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.king.batterytest.fbaselib.customview.multiimageselector.MultiImageSelectorActivity;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.ScanActivity;
import com.king.batterytest.fbaselib.utils.Tools;

import java.util.List;


public class MainActivity extends BaseActivity {
    private int REQUEST_CODE = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Tools.gotoSelectPic(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Tools.REQUEST_IMAGE && data != null) {
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (path != null && path.size() > 0) {
                Tools.toastInBottom(this, path.get(0));
            }

        }
    }
}
