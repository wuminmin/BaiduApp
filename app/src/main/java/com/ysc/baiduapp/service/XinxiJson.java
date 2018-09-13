package com.ysc.baiduapp.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ysc.baiduapp.database.DatabaseHelper;
import com.ysc.baiduapp.database.model.Note;
import com.ysc.baiduapp.database.model.Xinxi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.READ_PHONE_STATE;

public class XinxiJson {
    private GetCellInfo getCellInfo;
    private DatabaseHelper databaseHelper;
    private SystemUtil systemUtil;
    private Context c;
    private TelephonyManager t;
    private FragmentActivity m;

    public XinxiJson(Context c, TelephonyManager t, FragmentActivity m) {
        this.c = c;
        this.t = t;
        this.m = m;
        this.getCellInfo = new GetCellInfo(c, t, m);
        this.databaseHelper = new DatabaseHelper(c);
        this.systemUtil = new SystemUtil();
    }

    public void saveXinxiJson() {
        try {
            String lastCell = databaseHelper.getLastCell().getNote();
            JsonObject mySignalStrengthJson = new JsonParser().parse(lastCell).getAsJsonObject();
            JsonObject getCellInfojson = getCellInfo.myGetAllCellInfo();
            JsonObject xinxiJson = new JsonObject();
            if (mySignalStrengthJson.has("getRsrpCellInfoLte")) {
                xinxiJson.addProperty("rsrp", mySignalStrengthJson.get("getRsrpCellInfoLte").getAsString());
            }else {
                xinxiJson.addProperty("rsrp", "");
            }
            if (getCellInfojson.has("getRssnrCellInfoLte")) {
                xinxiJson.addProperty("rssnr", String.valueOf(getCellInfojson.get("getRssnrCellInfoLte")));
            }
            if (mySignalStrengthJson.has("getRsrqCellInfoLte")) {
                xinxiJson.addProperty("rsrq", String.valueOf(mySignalStrengthJson.get("getRsrqCellInfoLte")));
            }else {
                xinxiJson.addProperty("rsrq", "");
            }
            if (getCellInfojson.has("getEarfcnCellIdentityLte")) {
                xinxiJson.addProperty("earf", getCellInfojson.get("getEarfcnCellIdentityLte").getAsInt());
            }else {
                xinxiJson.addProperty("earf", "");
            }
            if (getCellInfojson.has("getMccCellIdentityLte")) {
                xinxiJson.addProperty("mcc", String.valueOf(getCellInfojson.get("getMccCellIdentityLte")));
            }
            if (getCellInfojson.has("getMncCellIdentityLte")) {
                xinxiJson.addProperty("mnc", String.valueOf(getCellInfojson.get("getMncCellIdentityLte")));
            }
            if (getCellInfojson.has("getPciCellIdentityLte")) {
                xinxiJson.addProperty("pci", getCellInfojson.get("getPciCellIdentityLte").getAsString());
            }else {
                xinxiJson.addProperty("pci", "");
            }
            if (getCellInfojson.has("getTacCellIdentityLte")) {
                xinxiJson.addProperty("tac", String.valueOf(getCellInfojson.get("getTacCellIdentityLte")));
            }else {
                xinxiJson.addProperty("tac", "");
            }
            if (mySignalStrengthJson.has("getCdmaDbmCellSignalStrengthCdma")) {
                xinxiJson.addProperty("dbm", mySignalStrengthJson.get("getCdmaDbmCellSignalStrengthCdma").getAsInt());
            }else {
                xinxiJson.addProperty("dbm", "");
            }
            if (mySignalStrengthJson.has("getCdmaEcioCellSignalStrengthCdma")) {
                xinxiJson.addProperty("ecio", (mySignalStrengthJson.get("getCdmaEcioCellSignalStrengthCdma").getAsInt()));
            }else {
                xinxiJson.addProperty("ecio", "");
            }
            if (getCellInfojson.has("getBasestationIdCellIdentityCdma")) {
                xinxiJson.addProperty("bid", String.valueOf(getCellInfojson.get("getBasestationIdCellIdentityCdma")));
            }else {
                xinxiJson.addProperty("bid", "");
            }
            if (getCellInfojson.has("getNetworkIdCellIdentityCdma")) {
                xinxiJson.addProperty("nid", String.valueOf(getCellInfojson.get("getNetworkIdCellIdentityCdma")));
            }else {
                xinxiJson.addProperty("nid", "");
            }
            if (getCellInfojson.has("getSystemIdCellIdentityCdma")) {
                xinxiJson.addProperty("sid", String.valueOf(getCellInfojson.get("getSystemIdCellIdentityCdma")));
            }else {
                xinxiJson.addProperty("sid", "");
            }
            if (getCellInfojson.has("ProvidersName")) {
                xinxiJson.addProperty("leixing", getCellInfojson.get("ProvidersName").getAsString());
            }else {
                xinxiJson.addProperty("leixing", "");
            }
            GPSTracker gps= new GPSTracker(c , m);
                xinxiJson.addProperty("weidu", gps.getLatitude());
                xinxiJson.addProperty("jingdu", gps.getLongitude() );

//            xinxiJson.addProperty("weidu", getCellInfo.myGps().get("getLatitude"));
//            xinxiJson.addProperty("jingdu", getCellInfo.myGps().get("getLongitude"));

            xinxiJson.addProperty("pingpai", android.os.Build.BRAND);
            xinxiJson.addProperty("xinghao", android.os.Build.MODEL);
            xinxiJson.addProperty("yingjian", android.os.Build.HARDWARE);
            xinxiJson.addProperty("anzhuo", Build.VERSION.RELEASE);
            xinxiJson.addProperty("wangluo", SystemUtil.getNetworkType(this.c, this.m));
            xinxiJson.addProperty("shuju", SystemUtil.getNetworkType(this.c, this.m));
            xinxiJson.addProperty("xiaoqu", SystemUtil.getNetworkType(this.c, this.m));
            xinxiJson.addProperty("time", getCunrTime());
            xinxiJson.addProperty("jingque", "300米");
            xinxiJson.addProperty("fangshi", "GPS");
            if (mySignalStrengthJson.has("getSinrCellInfoLte")) {
                xinxiJson.addProperty("sinr", (mySignalStrengthJson.get("getSinrCellInfoLte").getAsInt()));
            }else {
                xinxiJson.addProperty("sinr", "");
            }
            if (getCellInfojson.has("getEarfcnCellIdentityLte")) {
                xinxiJson.addProperty("band", getBand(getCellInfojson.get("getEarfcnCellIdentityLte").getAsInt()));
            }else {
                xinxiJson.addProperty("band", "");
            }
            if (getCellInfojson.has("getCiCellIdentityLte")) {
                xinxiJson.addProperty("enb", String.valueOf(getCellInfojson.get("getCiCellIdentityLte").getAsInt() / 256));
            }else {
                xinxiJson.addProperty("enb", "");
            }
            if (getCellInfojson.has("getCiCellIdentityLte")) {
                xinxiJson.addProperty("cellid", String.valueOf(getCellInfojson.get("getCiCellIdentityLte").getAsInt() % 256));
            }else {
                xinxiJson.addProperty("cellid", "");
            }
//            Log.e("保存小区信息json格式", String.valueOf(xinxiJson));
            databaseHelper.insertXinxi(xinxiJson.toString());
        } catch (Exception e) {
            Log.e("保存小区信息json格式失败", e.toString());
        }
    }

    private String getBand(int x) {
        switch (x) {
            case 100:
                return "2.1G";
            case 1825:
                return "1.8G";
        }
        return "800M";
    }

    private String getCunrTime() {
        Calendar now = Calendar.getInstance();
//        System.out.println("年: " + now.get(Calendar.YEAR));
//        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
//        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
//        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
//        System.out.println("分: " + now.get(Calendar.MINUTE));
//        System.out.println("秒: " + now.get(Calendar.SECOND));
//        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
//        System.out.println(now.getTime());

        Date d = new Date();
//        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
//        System.out.println("格式化后的日期：" + dateNowStr);

        String str = "2012-1-13 17:26:33";    //要跟上面sdf定义的格式一样
        Date today = null;
        try {
            today = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println("字符串转成日期：" + today);
        String result = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
        ;
        return result;
    }

    public String getXinxiJsonAll() {
        String json = "";
        try {
            List<Xinxi> notes = databaseHelper.getAllXinxi();
            boolean first = true;
            int i = 0;
            StringBuilder jsontmp = new StringBuilder();
            for (Xinxi note : notes) {
                i++;
                if (first) {
                    jsontmp.append(note.getNote());
                    first = false;
                } else {
                    jsontmp.append(",");
                    jsontmp.append(note.getNote());
                }
                if (i > 8) {
                    break;
                }
            }
            json = "[" + jsontmp.toString() + "]";
        } catch (Exception e) {
            Log.e("查询Xinxi数据库出错", e.toString());
        }
        return json;
    }

    public String getXinxiJsonOne() {
        String json = "";
        try {
            List<Xinxi> notes = databaseHelper.getAllXinxi();
            boolean first = true;
            StringBuilder jsontmp = new StringBuilder();
            for (Xinxi note : notes) {
                if (first) {
                    jsontmp.append(note.getNote());
                    first = false;
                } else {
                    break;
                }
            }
            json = "[" + jsontmp.toString() + "]";
        } catch (Exception e) {
            Log.e("查询第一个Xinxi数据库出错", e.toString());
        }
        Log.e("查询第一个Xinxi值", json);
        return json;
    }

}
