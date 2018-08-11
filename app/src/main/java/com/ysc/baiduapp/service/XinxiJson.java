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
            JsonArray arr = new JsonArray();
            JsonObject object2 = new JsonObject();
            object2.addProperty("反贼", "宋江");
            object2.addProperty("反贼年龄", 45);
            arr.add(object2);
            JsonObject object3 = new JsonObject();
            object3.addProperty("反贼", "武松");
            object3.addProperty("反贼年龄", 23);
            arr.add(object3);
            System.out.println(arr.toString());

            JsonObject getCellInfojson = new JsonObject();
            getCellInfojson.addProperty("rsrp", getCellInfo.getRsrpCellSignalStrengthLte());
            databaseHelper.insertXinxi(getCellInfojson.toString());
            Log.e("保持手机信息", getCellInfojson.toString());
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
                if(i>6){
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
