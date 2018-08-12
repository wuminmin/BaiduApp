package com.ysc.baiduapp.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * 系统工具类
 * Created by zhuwentao on 2016-07-18.
 */
public class SystemUtil {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @SuppressLint("HardwareIds")
    public static String getIMEI(Context ctx , Activity mymainActivity ) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(ctx, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(mymainActivity,
                        new String[]{READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                return "";
            }
            return tm.getDeviceId();
        }
        return null;
    }

    public static String getNetworkType(Context ctx ,Activity mymainActivity ) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(ctx, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(mymainActivity,
                        new String[]{READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                return "";
            }
            int networkType = tm.getNetworkType();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT: return "1xRTT";
                case TelephonyManager.NETWORK_TYPE_CDMA: return "CDMA";
                case TelephonyManager.NETWORK_TYPE_EDGE: return "EDGE";
                case TelephonyManager.NETWORK_TYPE_EHRPD: return "eHRPD";
                case TelephonyManager.NETWORK_TYPE_EVDO_0: return "EVDO rev. 0";
                case TelephonyManager.NETWORK_TYPE_EVDO_A: return "EVDO rev. A";
                case TelephonyManager.NETWORK_TYPE_EVDO_B: return "EVDO rev. B";
                case TelephonyManager.NETWORK_TYPE_GPRS: return "GPRS";
                case TelephonyManager.NETWORK_TYPE_HSDPA: return "HSDPA";
                case TelephonyManager.NETWORK_TYPE_HSPA: return "HSPA";
                case TelephonyManager.NETWORK_TYPE_HSPAP: return "HSPA+";
                case TelephonyManager.NETWORK_TYPE_HSUPA: return "HSUPA";
                case TelephonyManager.NETWORK_TYPE_IDEN: return "iDen";
                case TelephonyManager.NETWORK_TYPE_LTE: return "LTE";
                case TelephonyManager.NETWORK_TYPE_UMTS: return "UMTS";
                case TelephonyManager.NETWORK_TYPE_UNKNOWN: return "Unknown";
            }
        }
        return "未知网络类型";
    }
}
