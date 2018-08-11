package com.ysc.baiduapp.service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ysc.baiduapp.database.DatabaseHelper;
import com.ysc.baiduapp.database.model.Note;
import com.ysc.baiduapp.database.model.Xinxi;

import java.util.List;

public class XinxiJson {

    private GetCellInfo getCellInfo;
    private DatabaseHelper databaseHelper;

    public XinxiJson(Context c, TelephonyManager t, FragmentActivity m) {
        this.getCellInfo = new GetCellInfo(c, t, m);
        this.databaseHelper = new DatabaseHelper(c);
    }

    public void saveXinxiJson() {
        try {
            JsonObject getCellInfojson = getCellInfo.getRsrpCellSignalStrengthLte() ;
            JsonObject XinxiJson = new JsonObject();
            if ( getCellInfojson.has("getRsrpCellInfoLte" )){
                XinxiJson.addProperty("rsrp", String.valueOf(getCellInfojson.get("getRsrpCellInfoLte")));
            }
            Log.e("手机小区信息json格式", String.valueOf(XinxiJson)) ;
            databaseHelper.insertXinxi(XinxiJson.toString());
        } catch (Exception e) {
            Log.e("获取手机信息拼装json失败", e.toString());
        }
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
