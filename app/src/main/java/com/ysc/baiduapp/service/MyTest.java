package com.ysc.baiduapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {

    public String getSec() {
        java.util.Calendar can = java.util.Calendar.getInstance();
System.out.println(can.get(can.HOUR)); //时
System.out.println(can.get(can.MINUTE)); //分
System.out.println(can.get(can.SECOND)); //秒
        String sec =  Integer.toString( can.get(can.SECOND) );

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );

        return sec;

    }
}
