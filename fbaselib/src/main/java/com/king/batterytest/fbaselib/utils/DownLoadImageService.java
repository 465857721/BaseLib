package com.king.batterytest.fbaselib.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alvis on 2017/4/14.
 */

public class DownLoadImageService implements Runnable {
    public interface ImageDownLoadCallBack {
        void onDownLoadSuccess(File file);

        void onDownLoadSuccess(Bitmap bitmap);

        void onDownLoadFailed();
    }

    private String url;
    private Context context;
    private ImageDownLoadCallBack callBack;
    private File currentFile;

    public DownLoadImageService(Context context, String url, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void run() {
        File file = null;
        Bitmap bitmap = null;
        try {
//            file = Glide.with(context)
//                    .load(url)
//                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                    .get();

            bitmap = Glide.with(context).asBitmap()
                    .load(url)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap != null) {

                saveImageToGallery(context, bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (file != null) {
//                callBack.onDownLoadSuccess(file);
//            } else {
//                callBack.onDownLoadFailed();
//            }
            if (bitmap != null && currentFile.exists()) {
                callBack.onDownLoadSuccess(bitmap);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

    public void saveImageToGallery(Context context, Bitmap bmp) {

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String fileName = "wetime";
        File appDir = new File(file, fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName1 = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, fileName1);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }
}
