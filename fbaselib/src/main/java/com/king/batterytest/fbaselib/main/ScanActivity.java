package com.king.batterytest.fbaselib.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.king.batterytest.fbaselib.R;

import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private static final String TAG = "zk scan";


    private QRCodeView mQRCodeView;
    private Intent data;
    private int CODE_RESULT = RESULT_CANCELED;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        ButterKnife.bind(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        initClick();
    }

    @Override
    public void onBackPressed() {
        setResult(CODE_RESULT, data);
        super.onBackPressed();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        CODE_RESULT = RESULT_OK;
        data = new Intent();
        data.putExtra("key", result);
        onBackPressed();
//        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    private void initClick() {
        findViewById(R.id.iv_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE_RESULT = RESULT_OK;
                onBackPressed();
            }
        });
        findViewById(R.id.iv_lib_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}