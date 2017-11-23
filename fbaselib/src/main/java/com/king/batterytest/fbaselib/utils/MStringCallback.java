package com.king.batterytest.fbaselib.utils;

import android.content.Context;

import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.main.model.ErrorBean;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/3.
 */

public class MStringCallback extends StringCallback {
    private Context mContext;
    private boolean showDialog;
    private boolean showErrorToast;
    private boolean showSuccessToast;
    private boolean cancleable;

    public MStringCallback(Context mContext) {
        this.mContext = mContext;
        this.showDialog = true;
        this.showErrorToast = true;
        this.cancleable = true;

    }

    public MStringCallback(Context mContext, boolean cancleable) {
        this.mContext = mContext;
        this.showDialog = true;
        this.showErrorToast = true;
        this.cancleable = cancleable;

    }

    public MStringCallback(Context mContext, boolean showDialog, boolean showErrorToast) {
        this.mContext = mContext;
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
        this.cancleable = true;
    }


    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (showDialog) {
            Tools.showWaitDialog(mContext, cancleable);
        }

    }


    @Override
    public void onError(Call call, Exception e, int i) {
        if (showDialog) {
            Tools.hideWaitDialog();
        }
//        if (showErrorToast) {
//            if (Tools.checkNetwork(mContext)) {
//                Tools.toastInBottom(mContext, e.toString());
//            } else {
//                Tools.toastInBottom(mContext, "请检查网络");
//            }


//        }

    }

    @Override
    public void onResponse(String s, int i) {
        if (showDialog) {
            Tools.hideWaitDialog();
        }

        BaseBean bean = getGsonInstance().fromJson(s, BaseBean.class);

        if (bean.getError() != 0) {
            if (showErrorToast) {
                ErrorBean errorBean = getGsonInstance().fromJson(s, ErrorBean.class);
                Tools.toastInBottom(mContext, errorBean.getMsg());
            }
        }
        if (bean.getError() == 0) {
            if (showSuccessToast) {
                ErrorBean errorBean = getGsonInstance().fromJson(s, ErrorBean.class);
                Tools.toastInBottom(mContext, errorBean.getMsg());
            }
        }
        if (bean.getError() == 403) {
            Tools.logout(mContext);
//            FApp.getInstance().removeALLActivity();
//            Intent go = new Intent(mContext, LoginActivity.class);
//            mContext.startActivity(go);
        }
    }
}
