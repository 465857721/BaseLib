package com.king.batterytest.fbaselib.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.king.batterytest.fbaselib.LogoutEvent;
import com.king.batterytest.fbaselib.R;
import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.utils.SharePreferenceUtil;
import com.king.batterytest.fbaselib.utils.Tools;

import org.greenrobot.eventbus.EventBus;


public class BaseFragment extends Fragment implements IBaseVIew {
    public SharePreferenceUtil spu;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void showLoading(boolean can) {
        Tools.showWaitDialog(getContext(), can);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spu = new SharePreferenceUtil(getActivity(), "wetime");
    }

    @Override
    public String getToken() {
        return spu.getToken();
    }

    @Override
    public void showLoading() {
        Tools.showWaitDialog(getActivity());
    }

    @Override
    public void onTimeOut() {
//        Tools.toastInBottom(getActivity(), getString(R.string.tips_timeout));
//        Tools.logout(getActivity());
//        FApp.getInstance().removeALLActivity();
//        Intent go = new Intent(getActivity(), LoginActivity.class);
//        startActivity(go);
        EventBus.getDefault().post(new LogoutEvent());
    }

    @Override
    public void dismissLoading() {
        Tools.hideWaitDialog();
    }

    @Override
    public void onNetError() {
        Tools.toastInBottom(getActivity(), getString(R.string.tips_net_error));
    }

    @Override
    public void onError(String s) {
        Tools.toastInBottom(getActivity(), s);
    }

    @Override
    public void onFormJsonError() {
        Tools.toastInBottom(getActivity(), getString(R.string.tips_form_json_error));
    }

    @Override
    public void onNoPermission() {

    }

}
