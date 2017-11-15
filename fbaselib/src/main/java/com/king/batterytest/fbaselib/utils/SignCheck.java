package com.king.batterytest.fbaselib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Created by zhoukang on 2017/9/6.
 */
public class SignCheck {
    private Context context;
    private String cer = null;
    private String realCer = null;
    private static final String TAG = "xg";

    public SignCheck(Context context) {
        this.context = context;
        this.cer = getCertificateSHA1Fingerprint();
    }

    public SignCheck(Context context, String realCer) {
        this.context = context;
        this.realCer = realCer;
        this.cer = getCertificateSHA1Fingerprint();
    }

    public String getRealCer() {
        return realCer;
    }

    public void setRealCer(String realCer) {
        this.realCer = realCer;
    }

    public String getCertificateSHA1Fingerprint() {
        PackageManager pm = context.getPackageManager();

        String packageName = context.getPackageName();

        int flags = PackageManager.GET_SIGNATURES;

        PackageInfo packageInfo = null;

        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();

        InputStream input = new ByteArrayInputStream(cert);

        CertificateFactory cf = null;

        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (Exception e) {
            e.printStackTrace();
        }

        X509Certificate c = null;

        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String hexString = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");

            byte[] publicKey = md.digest(c.getEncoded());

            hexString = byte2HexFormatted(publicKey);

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        return hexString;
    }


    private String byte2HexFormatted(byte[] arr) {

        StringBuilder str = new StringBuilder(arr.length * 2);

        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1)
                h = "0" + h;
            if (l > 2)
                h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1))
                str.append(':');
        }
        return str.toString();
    }


    public boolean check() {

        if (this.realCer != null) {
            cer = cer.trim();
            realCer = realCer.trim();
            if (this.cer.equals(this.realCer)) {
                return true;
            }
        } else {
        }
        return false;
    }
}
