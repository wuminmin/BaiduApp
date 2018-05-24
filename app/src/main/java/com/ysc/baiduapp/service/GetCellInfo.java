package com.ysc.baiduapp.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.support.v4.app.FragmentActivity;
import com.ysc.baiduapp.MainActivity;

import java.util.Arrays;
import java.util.List;

public class GetCellInfo {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private FragmentActivity mymainActivity;
    private Context mycontext;
    private TelephonyManager telephonyManager;
    private String strTmp = "基站信息：";
    private String strCellInfo = "小区信息：";

    public GetCellInfo(Context c, TelephonyManager t, FragmentActivity m) {
        mycontext = c;
        telephonyManager = t;
        mymainActivity = m;
    }

    public String myBase() {

        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    new AlertDialog.Builder(mycontext).setTitle("标题").setMessage("请求卫星和网络权限！！").setPositiveButton("确定", null).show();
                    return "请求卫星和网络权限！！";
                }

                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation)
                        telephonyManager.getCellLocation();
                int cid = cdmaCellLocation.getBaseStationId(); //获取cdma基站识别标号 BID
                int lac = cdmaCellLocation.getNetworkId(); //获取cdma网络编号NID
                int sid = cdmaCellLocation.getSystemId(); //用谷歌API的话cdma网络的mnc要用这个getSystemId()取得→SID
                strTmp = "基站信息 " + Integer.toString(cid) + Integer.toString(lac) + Integer.toString(sid);
            } else {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                int cid = gsmCellLocation.getCid(); //获取gsm基站识别标号
                int lac = gsmCellLocation.getLac(); //获取gsm网络编号
                strTmp = "基站信息 " + Integer.toString(cid) + Integer.toString(lac);
            }
        }
        return strTmp;
    }

    public String myCell() {
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    new AlertDialog.Builder(mycontext).setTitle("标题").setMessage("请求卫星和网络权限！！").setPositiveButton("确定", null).show();
                    return "请求卫星和网络权限！！";
                }
                List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
                strCellInfo = "小区信息：" + Arrays.toString(cellInfoList.toArray());
            }
        }
        return strCellInfo;
    }
}
