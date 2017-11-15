package com.king.batterytest.fbaselib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.batterytest.fbaselib.R;
import com.king.batterytest.fbaselib.customview.multiimageselector.MultiImageSelectorActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by alvis on 2017/4/12.
 */

public class Tools {
    private static Toast toast;
    private static Dialog loadingDialog;

    public static final int REQUEST_IMAGE = 1004;

    public static SharePreferenceUtil getSpu(Context mContext) {
        return new SharePreferenceUtil(mContext.getApplicationContext(), "wetime");
    }



    public static void toastInBottom(Context context, String msg) {
        if (context != null) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 300);
            toast.show();
        }
    }

    public static void logout(Context mContext) {
        SharePreferenceUtil spu = getSpu(mContext);
        spu.setToken("");
    }

    public static void hideSoftInput(Activity mActivity) {
        IBinder mIBinder = mActivity.getCurrentFocus().getWindowToken();
        if (mIBinder != null)
            ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(mIBinder, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public static void gotoSelectPic(Activity mActivity) {
        Intent intent = new Intent(mActivity, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                MultiImageSelectorActivity.MODE_SINGLE);
        mActivity.startActivityForResult(intent, REQUEST_IMAGE);
    }

    public static void showWaitDialog(Context context, boolean cancelable) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.loading_dialog, null);
        builder.setView(view);
        loadingDialog = builder.show();
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setCanceledOnTouchOutside(cancelable);


//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.loading_dialog);// 加载布局


//        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "scaleX", 1.0f, 1f);
//        scaleXAnim.setRepeatCount(-1);
//        scaleXAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "scaleY", 1.0f, 0.8f);
//        scaleYAnim.setRepeatCount(-1);
//        scaleYAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "translationY", 0f, Tools.dip2px(context, 10));
//        transYAnim.setRepeatCount(-1);
//        transYAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//        ObjectAnimator scaleXAnimB = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.iv_loading_bottom), "scaleX", 0.8f, 1.1f);
//        scaleXAnimB.setRepeatCount(-1);
//        scaleXAnimB.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        ObjectAnimator scaleYAnimB = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.iv_loading_bottom), "scaleY", 1.0f, 1.3f);
//        scaleYAnimB.setRepeatCount(-1);
//        scaleYAnimB.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        AnimatorSet set = new AnimatorSet();
//        set.setInterpolator(new AccelerateInterpolator());
//        set.playTogether(scaleXAnim, scaleYAnim, transYAnim, scaleXAnimB, scaleYAnimB);
//        set.setDuration(800);
//
//        set.start();
    }


    public static void showWaitDialog(Context context) {
        showWaitDialog(context, true);
    }



    public static boolean checkPrice(String price) {
        if (price.contains(".")) {
            if (price.substring(0, 1).equals(".")) {
                price = "0" + price;
            }
            String[] array = price.split("\\.");
            if (array.length > 1 && array[1].length() > 2) {
                return false;
            }
        }
        return true;
    }




    public static void hideWaitDialog() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
    }

    public static void showTipsDialog(Context mContext, String tips,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();
        TextView tvtips = (TextView) layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        Button btnCancel = (Button) layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });

        Button btnOK = (Button) layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);
            }
        });

    }

    public static void showTipsDialog(Context mContext, SpannableString tips, String left, String right,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();
        TextView tvtips = (TextView) layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        Button btnCancel = (Button) layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });

        Button btnOK = (Button) layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);
            }
        });
        btnCancel.setText(left);
        btnOK.setText(right);
        if (TextUtils.isEmpty(left)) {
            layout.findViewById(R.id.view_middle).setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }
    }

    public static void showTipsDialog(Context mContext, String tips, String left, String right,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();
        TextView tvtips = (TextView) layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        Button btnCancel = (Button) layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });

        Button btnOK = (Button) layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);
            }
        });
        btnCancel.setText(left);
        btnOK.setText(right);
        if (TextUtils.isEmpty(left)) {
            layout.findViewById(R.id.view_middle).setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }
    }


    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);
                }
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return verCode;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {

        }

        return verName;
    }

    public static void goMarket(Context mContext) {
        try {

            Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());// id为包名
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(it);

        } catch (Exception e) {
            toastInBottom(mContext, "请打开应用市场，搜索 " + mContext.getString(R.string.app_name));
            e.printStackTrace();
        }
    }

    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenW(Activity mActivity) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);

        return mDisplayMetrics.widthPixels;
    }

    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}
