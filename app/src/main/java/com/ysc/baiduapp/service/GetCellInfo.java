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

import com.google.gson.Gson;
import com.ysc.baiduapp.MainActivity;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class GetCellInfo {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private FragmentActivity mymainActivity;
    private Context mycontext;
    private TelephonyManager telephonyManager;
    private String strTmp = "";
    private String strCellInfo = "";

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
//        System.out.println(strTmp);
        return strTmp;
    }

    public String myCell() throws ParseException {
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
//                    new AlertDialog.Builder(mycontext).setTitle("标题").setMessage("请求卫星和网络权限！！").setPositiveButton("确定", null).show();
                    return null;
                }
                List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
//                ListIterator<CellInfo> itr=cellInfoList.listIterator();
//                System.out.println("traversing elements in forward direction...");
//                while(itr.hasNext()){
//                    System.out.println(itr.next());
//                }
//
//                if (cellInfoList.size() > 0) {
//                    for (CellInfo info : cellInfoList) {
//                        info.getTimeStamp();
//                        info.toString();
//                    }
//
//                } else {
//                    return null;
//                }
                try {
//                strCellInfo =  Arrays.toString(cellInfoList.toArray());
                    Gson gson = new Gson();
                    strCellInfo = gson.toJson(cellInfoList);
//                JSONParser parser = new JSONParser();
//                JSONObject json = (JSONObject) parser.parse(jsonString);

//                JSONObject jsonObj = new JSONObject("[{\"mCellIdentityCdma\":{\"mBasestationId\":52946,\"mLatitude\":574990,\"mLongitude\":1676491,\"mNetworkId\":3,\"mSystemId\":13824},\"mCellSignalStrengthCdma\":{\"mCdmaDbm\":-73,\"mCdmaEcio\":-85,\"mEvdoDbm\":2147483647,\"mEvdoEcio\":2147483647,\"mEvdoSnr\":2147483647},\"mRegistered\":true,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":25170179,\"mEarfcn\":100,\"mMcc\":460,\"mMnc\":11,\"mPci\":242,\"mTac\":6245},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-78,\"mRsrq\":-10,\"mRssnr\":2147483647,\"mSignalStrength\":31,\"mTimingAdvance\":1},\"mRegistered\":true,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":100,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":290,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-88,\"mRsrq\":-16,\"mRssnr\":2147483647,\"mSignalStrength\":27,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":100,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":181,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-92,\"mRsrq\":-20,\"mRssnr\":2147483647,\"mSignalStrength\":26,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-76,\"mRsrq\":-6,\"mRssnr\":2147483647,\"mSignalStrength\":27,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-88,\"mRsrq\":-19,\"mRssnr\":2147483647,\"mSignalStrength\":27,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-90,\"mRsrq\":-19,\"mRssnr\":2147483647,\"mSignalStrength\":27,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":5958058485325,\"mTimeStampType\":3}]\n");
//                    JSONArray jsonarray = new JSONArray(jsonString);
//                    for(int i=0; i < jsonarray.length(); i++) {
//                    JSONObject jsonobject = jsonarray.getJSONObject(0);
//                        strCellInfo = jsonobject.getJSONObject("mCellSignalStrengthCdma").getString("mCdmaDbm");

//                        String id       = jsonobject.getString("id");
//                        String title    = jsonobject.getString("title");
//                        String company  = jsonobject.getString("company");
//                        String category = jsonobject.getString("category");
//                    }
//                    strCellInfo = jsonObj.getJSONObject("mCellIdentityCdma").getString("mBasestationId");
                    System.out.print(strCellInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return strCellInfo;
    }
}
