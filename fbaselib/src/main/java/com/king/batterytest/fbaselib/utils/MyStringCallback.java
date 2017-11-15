package com.king.batterytest.fbaselib.utils;

import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.main.model.BaseBean;

import com.king.batterytest.fbaselib.main.model.ErrorBean;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/3.
 */

public class MyStringCallback extends StringCallback {
    private boolean showDialog;
    private boolean showErrorToast;
    //    private boolean showSuccessToast;  // 接口规范后  才能抽象出来
    private IBaseVIew iview;
    private boolean isJudge;

    public MyStringCallback(IBaseVIew iview) {
        this.iview = iview;
        this.showDialog = true;
        this.showErrorToast = true;
        this.isJudge = true;
//        this.showSuccessToast = true;
    }

    //    public MyStringCallback(boolean showDialog, boolean showErrorToast,boolean showSuccessToast) {
//        this.showDialog = showDialog;
//        this.showErrorToast = showErrorToast;
//        this.showSuccessToast = showSuccessToast;
//    }
    public MyStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast, boolean showSuccessToast) {
        this.iview = iview;
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
//        this.showSuccessToast = showSuccessToast;
        this.isJudge = false;
    }

    public MyStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast, boolean showSuccessToast, boolean isJudge) {
        this.iview = iview;
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
//        this.showSuccessToast = showSuccessToast;
        this.isJudge = isJudge;
    }


    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (showDialog) {
            iview.showLoading();
        }

    }


    @Override
    public void onError(Call call, Exception e, int i) {
        if (showDialog) {
            iview.dismissLoading();
        }
        if (showErrorToast) {
            iview.onNetError();
        }

    }

    @Override
    public void onResponse(String s, int i) {
        if (showDialog) {
            iview.dismissLoading();
        }

        BaseBean bean = getGsonInstance().fromJson(s, BaseBean.class);

        if (bean.getError() != 0) {
            if (showErrorToast) {
                ErrorBean errorBean = getGsonInstance().fromJson(s, ErrorBean.class);
                iview.onError(errorBean.getMsg());
            }
        }
        // 接口规范后 才能抽象出来
//        if (bean.getError() == 0) {
//            if (showSuccessToast) {
//                ErrorBean errorBean = getGsonInstance().fromJson(s, ErrorBean.class);
//                iview.onError(errorBean.getMsg());
//            }
//        }
        if (isJudge && bean.getError() == 403) {
            iview.onTimeOut();
        }

    }
}
