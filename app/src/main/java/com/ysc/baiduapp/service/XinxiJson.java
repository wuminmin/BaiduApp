package com.ysc.baiduapp.service;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ysc.baiduapp.database.DatabaseHelper;
import com.ysc.baiduapp.database.model.Note;
import com.ysc.baiduapp.database.model.Xinxi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
            JsonObject getCellInfojson = getCellInfo.getRsrpCellSignalStrengthLte() ;
            JsonObject XinxiJson = new JsonObject();
            if ( getCellInfojson.has("getRsrpCellInfoLte" )) {
                XinxiJson.addProperty("rsrp", String.valueOf(getCellInfojson.get("getRsrpCellInfoLte")));
            }
            if ( getCellInfojson.has("getRssnrCellInfoLte" ) ) {
                XinxiJson.addProperty("rssnr", String.valueOf(getCellInfojson.get("getRssnrCellInfoLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellInfoLte" ) ) {
                XinxiJson.addProperty("getAsuLevelCellInfoLte", String.valueOf(getCellInfojson.get("getAsuLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getLevelCellInfoLte" ) ) {
                XinxiJson.addProperty("band", String.valueOf(getCellInfojson.get("getLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getCqiCellInfoLte" ) ) {
                XinxiJson.addProperty("cqi", String.valueOf(getCellInfojson.get("getCqiCellInfoLte")));
            }
            if ( getCellInfojson.has("getDbmCellInfoLte" ) ) {
                XinxiJson.addProperty("getDbmCellInfoLte", String.valueOf(getCellInfojson.get("getDbmCellInfoLte")));
            }
            if ( getCellInfojson.has("getRsrqCellInfoLte" ) ) {
                XinxiJson.addProperty("rsrq", String.valueOf(getCellInfojson.get("getRsrqCellInfoLte")));
            }
            if ( getCellInfojson.has("getTimingAdvanceCellInfoLte" ) ) {
                XinxiJson.addProperty("getTimingAdvanceCellInfoLte", String.valueOf(getCellInfojson.get("getTimingAdvanceCellInfoLte")));
            }
            if ( getCellInfojson.has("getCiCellIdentityLte" ) ) {
                XinxiJson.addProperty("eci",  Integer.toHexString(getCellInfojson.get("getCiCellIdentityLte").getAsInt()));
            }
            if ( getCellInfojson.has("getEarfcnCellIdentityLte" ) ) {
                XinxiJson.addProperty("earf", String.valueOf(getCellInfojson.get("getEarfcnCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMccCellIdentityLte" ) ) {
                XinxiJson.addProperty("mcc", String.valueOf(getCellInfojson.get("getMccCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMncCellIdentityLte" ) ) {
                XinxiJson.addProperty("mnc", String.valueOf(getCellInfojson.get("getMncCellIdentityLte")));
            }
            if ( getCellInfojson.has("getPciCellIdentityLte" ) ) {
                XinxiJson.addProperty("pci", String.valueOf(getCellInfojson.get("getPciCellIdentityLte")));
            }
            if ( getCellInfojson.has("getTacCellIdentityLte" ) ) {
                XinxiJson.addProperty("tac", String.valueOf(getCellInfojson.get("getTacCellIdentityLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getAsuLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getAsuLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getLevelCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaLevelCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getCdmaLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getCdmaLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoLevelCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getEvdoLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaDbmCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("dbm", String.valueOf(getCellInfojson.get("getCdmaDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaEcioCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("ecio", String.valueOf(getCellInfojson.get("getCdmaEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getDbmCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoDbmCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getEvdoDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoEcioCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getEvdoEcioCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoSnrCellSignalStrengthCdma" ) ) {
                XinxiJson.addProperty("getEvdoSnrCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoSnrCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getBasestationIdCellIdentityCdma" ) ) {
                XinxiJson.addProperty("bid", String.valueOf(getCellInfojson.get("getBasestationIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getNetworkIdCellIdentityCdma" ) ) {
                XinxiJson.addProperty("nid", String.valueOf(getCellInfojson.get("getNetworkIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getSystemIdCellIdentityCdma" ) ) {
                XinxiJson.addProperty("sid", String.valueOf(getCellInfojson.get("getSystemIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getLatitudeCellIdentityCdma" ) ) {
                XinxiJson.addProperty("weidu", getCellInfo.myGps().get("getLatitude"));
            }
            if ( getCellInfojson.has("getLongitudeCellIdentityCdma" ) ) {
                XinxiJson.addProperty("jingdu", getCellInfo.myGps().get("getLongitude") );
            }

            XinxiJson.addProperty("pingpai", android.os.Build.BRAND );
            XinxiJson.addProperty("xinghao", android.os.Build.MODEL );
            XinxiJson.addProperty("yingjian", android.os.Build.HARDWARE );
            XinxiJson.addProperty("anzhuo", Build.VERSION.RELEASE );
            XinxiJson.addProperty("wangluo", SystemUtil.getNetworkType(this.c,this.m) );
            XinxiJson.addProperty("time", getCunrTime() );
            XinxiJson.addProperty("leixing","安卓");
            XinxiJson.addProperty("jingque","300米");
            XinxiJson.addProperty("fangshi","GPS");
            XinxiJson.addProperty("enb", String.valueOf( getCellInfojson.get("getCiCellIdentityLte").getAsInt()/256 ));
            XinxiJson.addProperty("cellid", String.valueOf( getCellInfojson.get("getCiCellIdentityLte").getAsInt()%256 ));
            Log.e("手机小区信息json格式", String.valueOf(XinxiJson)) ;
            databaseHelper.insertXinxi(XinxiJson.toString());
        } catch (Exception e) {
            Log.e("获取手机信息拼装json失败", e.toString());
        }
    }

    private String getCunrTime(){
        Calendar now = Calendar.getInstance();
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + now.get(Calendar.MINUTE));
        System.out.println("秒: " + now.get(Calendar.SECOND));
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
        System.out.println(now.getTime());

        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);

        String str = "2012-1-13 17:26:33";	//要跟上面sdf定义的格式一样
        Date today = null;
        try {
            today = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("字符串转成日期：" + today);
        String result = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);;
        return result;
    }

    public String getXinxiJsonAll(){
        String json = "";
        try {
            List<Xinxi> notes = databaseHelper.getAllXinxi();
            boolean first = true;
            int i = 0 ;
            StringBuilder jsontmp = new StringBuilder();
            for( Xinxi  note : notes  ){
                i++;
                if(first){
                    jsontmp.append(note.getNote());
                    first=false;
                }else {
                    jsontmp.append(",");
                    jsontmp.append(note.getNote());
                }
                if(i>8){
                    break;
                }
            }
             json = "["+jsontmp.toString()+"]";
        }catch (Exception e){
            Log.e("查询Xinxi数据库出错",e.toString());
        }
        Log.e("查询全部Xinxi值", json);
        return json;
    }

}
