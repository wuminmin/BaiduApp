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
            JsonObject xinxiJson = new JsonObject();
            if ( getCellInfojson.has("getRsrpCellInfoLte" )) {
                xinxiJson.addProperty("rsrp", String.valueOf(getCellInfojson.get("getRsrpCellInfoLte")));
            }
            if ( getCellInfojson.has("getRssnrCellInfoLte" ) ) {
                xinxiJson.addProperty("rssnr", String.valueOf(getCellInfojson.get("getRssnrCellInfoLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellInfoLte" ) ) {
                xinxiJson.addProperty("getAsuLevelCellInfoLte", String.valueOf(getCellInfojson.get("getAsuLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getLevelCellInfoLte" ) ) {
                xinxiJson.addProperty("getLevelCellInfoLte", String.valueOf(getCellInfojson.get("getLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getCqiCellInfoLte" ) ) {
                xinxiJson.addProperty("cqi", String.valueOf(getCellInfojson.get("getCqiCellInfoLte")));
            }
            if ( getCellInfojson.has("getDbmCellInfoLte" ) ) {
                xinxiJson.addProperty("getDbmCellInfoLte", String.valueOf(getCellInfojson.get("getDbmCellInfoLte")));
            }
            if ( getCellInfojson.has("getRsrqCellInfoLte" ) ) {
                xinxiJson.addProperty("rsrq", String.valueOf(getCellInfojson.get("getRsrqCellInfoLte")));
            }
            if ( getCellInfojson.has("getTimingAdvanceCellInfoLte" ) ) {
                xinxiJson.addProperty("getTimingAdvanceCellInfoLte", String.valueOf(getCellInfojson.get("getTimingAdvanceCellInfoLte")));
            }
            if ( getCellInfojson.has("getCiCellIdentityLte" ) ) {
                xinxiJson.addProperty("eci",  Integer.toHexString(getCellInfojson.get("getCiCellIdentityLte").getAsInt()));
            }
            if ( getCellInfojson.has("getEarfcnCellIdentityLte" ) ) {
                xinxiJson.addProperty("earf", String.valueOf(getCellInfojson.get("getEarfcnCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMccCellIdentityLte" ) ) {
                xinxiJson.addProperty("mcc", String.valueOf(getCellInfojson.get("getMccCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMncCellIdentityLte" ) ) {
                xinxiJson.addProperty("mnc", String.valueOf(getCellInfojson.get("getMncCellIdentityLte")));
            }
            if ( getCellInfojson.has("getPciCellIdentityLte" ) ) {
                xinxiJson.addProperty("pci", String.valueOf(getCellInfojson.get("getPciCellIdentityLte")));
            }
            if ( getCellInfojson.has("getTacCellIdentityLte" ) ) {
                xinxiJson.addProperty("tac", String.valueOf(getCellInfojson.get("getTacCellIdentityLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getAsuLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getAsuLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getCdmaLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getCdmaLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("dbm", String.valueOf(getCellInfojson.get("getCdmaDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaEcioCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("ecio", String.valueOf(getCellInfojson.get("getCdmaEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoEcioCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoEcioCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoSnrCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoSnrCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoSnrCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getBasestationIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("bid", String.valueOf(getCellInfojson.get("getBasestationIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getNetworkIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("nid", String.valueOf(getCellInfojson.get("getNetworkIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getSystemIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("sid", String.valueOf(getCellInfojson.get("getSystemIdCellIdentityCdma")));
            }
                xinxiJson.addProperty("weidu", getCellInfo.myGps().get("getLatitude"));
                xinxiJson.addProperty("jingdu", getCellInfo.myGps().get("getLongitude") );

            xinxiJson.addProperty("pingpai", android.os.Build.BRAND );
            xinxiJson.addProperty("xinghao", android.os.Build.MODEL );
            xinxiJson.addProperty("yingjian", android.os.Build.HARDWARE );
            xinxiJson.addProperty("anzhuo", Build.VERSION.RELEASE );
            xinxiJson.addProperty("wangluo", SystemUtil.getNetworkType(this.c,this.m) );
            xinxiJson.addProperty("shuju",SystemUtil.getNetworkType(this.c,this.m));
            xinxiJson.addProperty("xiaoqu",SystemUtil.getNetworkType(this.c,this.m));
            xinxiJson.addProperty("time", getCunrTime() );
            xinxiJson.addProperty("leixing","安卓");
            xinxiJson.addProperty("jingque","300米");
            xinxiJson.addProperty("fangshi","GPS");
            if(getCellInfojson.has("lte_sinr")) {
                xinxiJson.addProperty("sinr", getCellInfojson.get("lte_sinr").getAsInt());
            }
            if(getCellInfojson.has("getEarfcnCellIdentityLte")) {
                xinxiJson.addProperty("band", getBand(getCellInfojson.get("getEarfcnCellIdentityLte").getAsInt()));
            }
            if(getCellInfojson.has("getCiCellIdentityLte")) {
                xinxiJson.addProperty("enb", String.valueOf(getCellInfojson.get("getCiCellIdentityLte").getAsInt() / 256));
            }
            if(getCellInfojson.has("getCiCellIdentityLte")){
                xinxiJson.addProperty("cellid", String.valueOf( getCellInfojson.get("getCiCellIdentityLte").getAsInt()%256 ));
            }
            Log.e("保存小区信息json格式", String.valueOf(xinxiJson)) ;
            databaseHelper.insertXinxi(xinxiJson.toString());
        } catch (Exception e) {
            Log.e("保存小区信息json格式失败", e.toString());
        }
    }

    public void saveXinxiJson2(JsonObject getCellInfojson) {
        try {
            JsonObject xinxiJson = new JsonObject();
            if ( getCellInfojson.has("getRsrpCellInfoLte" )) {
                xinxiJson.addProperty("rsrp", String.valueOf(getCellInfojson.get("getRsrpCellInfoLte")));
            }
            if ( getCellInfojson.has("getRssnrCellInfoLte" ) ) {
                xinxiJson.addProperty("rssnr", String.valueOf(getCellInfojson.get("getRssnrCellInfoLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellInfoLte" ) ) {
                xinxiJson.addProperty("getAsuLevelCellInfoLte", String.valueOf(getCellInfojson.get("getAsuLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getLevelCellInfoLte" ) ) {
                xinxiJson.addProperty("getLevelCellInfoLte", String.valueOf(getCellInfojson.get("getLevelCellInfoLte")));
            }
            if ( getCellInfojson.has("getCqiCellInfoLte" ) ) {
                xinxiJson.addProperty("cqi", String.valueOf(getCellInfojson.get("getCqiCellInfoLte")));
            }
            if ( getCellInfojson.has("getDbmCellInfoLte" ) ) {
                xinxiJson.addProperty("getDbmCellInfoLte", String.valueOf(getCellInfojson.get("getDbmCellInfoLte")));
            }
            if ( getCellInfojson.has("getRsrqCellInfoLte" ) ) {
                xinxiJson.addProperty("rsrq", String.valueOf(getCellInfojson.get("getRsrqCellInfoLte")));
            }
            if ( getCellInfojson.has("getTimingAdvanceCellInfoLte" ) ) {
                xinxiJson.addProperty("getTimingAdvanceCellInfoLte", String.valueOf(getCellInfojson.get("getTimingAdvanceCellInfoLte")));
            }
            if ( getCellInfojson.has("getCiCellIdentityLte" ) ) {
                xinxiJson.addProperty("eci",  Integer.toHexString(getCellInfojson.get("getCiCellIdentityLte").getAsInt()));
            }
            if ( getCellInfojson.has("getEarfcnCellIdentityLte" ) ) {
                xinxiJson.addProperty("earf", String.valueOf(getCellInfojson.get("getEarfcnCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMccCellIdentityLte" ) ) {
                xinxiJson.addProperty("mcc", String.valueOf(getCellInfojson.get("getMccCellIdentityLte")));
            }
            if ( getCellInfojson.has("getMncCellIdentityLte" ) ) {
                xinxiJson.addProperty("mnc", String.valueOf(getCellInfojson.get("getMncCellIdentityLte")));
            }
            if ( getCellInfojson.has("getPciCellIdentityLte" ) ) {
                xinxiJson.addProperty("pci", String.valueOf(getCellInfojson.get("getPciCellIdentityLte")));
            }
            if ( getCellInfojson.has("getTacCellIdentityLte" ) ) {
                xinxiJson.addProperty("tac", String.valueOf(getCellInfojson.get("getTacCellIdentityLte")));
            }
            if ( getCellInfojson.has("getAsuLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getAsuLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getAsuLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getCdmaLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getCdmaLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoLevelCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoLevelCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoLevelCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("dbm", String.valueOf(getCellInfojson.get("getCdmaDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getCdmaEcioCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("ecio", String.valueOf(getCellInfojson.get("getCdmaEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoDbmCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoDbmCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoDbmCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoEcioCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoEcioCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoEcioCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getEvdoSnrCellSignalStrengthCdma" ) ) {
                xinxiJson.addProperty("getEvdoSnrCellSignalStrengthCdma", String.valueOf(getCellInfojson.get("getEvdoSnrCellSignalStrengthCdma")));
            }
            if ( getCellInfojson.has("getBasestationIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("bid", String.valueOf(getCellInfojson.get("getBasestationIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getNetworkIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("nid", String.valueOf(getCellInfojson.get("getNetworkIdCellIdentityCdma")));
            }
            if ( getCellInfojson.has("getSystemIdCellIdentityCdma" ) ) {
                xinxiJson.addProperty("sid", String.valueOf(getCellInfojson.get("getSystemIdCellIdentityCdma")));
            }
            xinxiJson.addProperty("weidu", getCellInfo.myGps().get("getLatitude"));
            xinxiJson.addProperty("jingdu", getCellInfo.myGps().get("getLongitude") );

            xinxiJson.addProperty("pingpai", android.os.Build.BRAND );
            xinxiJson.addProperty("xinghao", android.os.Build.MODEL );
            xinxiJson.addProperty("yingjian", android.os.Build.HARDWARE );
            xinxiJson.addProperty("anzhuo", Build.VERSION.RELEASE );
            xinxiJson.addProperty("wangluo", SystemUtil.getNetworkType(this.c,this.m) );
            xinxiJson.addProperty("shuju",SystemUtil.getNetworkType(this.c,this.m));
            xinxiJson.addProperty("xiaoqu",SystemUtil.getNetworkType(this.c,this.m));
            xinxiJson.addProperty("time", getCunrTime() );
            xinxiJson.addProperty("leixing","安卓");
            xinxiJson.addProperty("jingque","300米");
            xinxiJson.addProperty("fangshi","GPS");
            if(getCellInfojson.has("lte_sinr")) {
                xinxiJson.addProperty("sinr", getCellInfojson.get("lte_sinr").getAsInt());
            }
            if(getCellInfojson.has("getEarfcnCellIdentityLte")) {
                xinxiJson.addProperty("band", getBand(getCellInfojson.get("getEarfcnCellIdentityLte").getAsInt()));
            }
            if(getCellInfojson.has("getCiCellIdentityLte")) {
                xinxiJson.addProperty("enb", String.valueOf(getCellInfojson.get("getCiCellIdentityLte").getAsInt() / 256));
            }
            if(getCellInfojson.has("getCiCellIdentityLte")){
                xinxiJson.addProperty("cellid", String.valueOf( getCellInfojson.get("getCiCellIdentityLte").getAsInt()%256 ));
            }
            Log.e("保存小区信息json格式", String.valueOf(xinxiJson)) ;
            databaseHelper.insertXinxi(xinxiJson.toString());
        } catch (Exception e) {
            Log.e("保存小区信息json格式失败", e.toString());
        }
    }

    public void saveShiNeng(){
        try{
            Log.e("保存室内场景", "");
            databaseHelper.insertXinxi("");
        }catch (Exception e){
            Log.e("保存室内场景失败", e.toString());
        }
    }

    private int getSinr(int rsrq){
        return 1/( 1/(12*rsrq) - 2);
    }

    private int getBand(int x){
        switch (x) {
            case 100 : return 1;
            case 1825 : return 3;
        }
            return 0 ;
    }

    private String getCunrTime(){
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

        String str = "2012-1-13 17:26:33";	//要跟上面sdf定义的格式一样
        Date today = null;
        try {
            today = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println("字符串转成日期：" + today);
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
        return json;
    }

    public String getXinxiJsonOne(){
        String json = "";
        try {
            List<Xinxi> notes = databaseHelper.getAllXinxi();
            boolean first = true;
            StringBuilder jsontmp = new StringBuilder();
            for( Xinxi  note : notes  ){
                if(first){
                    jsontmp.append(note.getNote());
                    first=false;
                }else {
                    break;
                }
            }
            json = "["+jsontmp.toString()+"]";
        }catch (Exception e){
            Log.e("查询第一个Xinxi数据库出错",e.toString());
        }
        Log.e("查询第一个Xinxi值", json);
        return json;
    }

}
