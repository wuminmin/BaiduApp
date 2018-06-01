package com.ysc.baiduapp.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ysc.baiduapp.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

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
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    Toast.makeText(this.mycontext, "需要卫星和网络权限！！", Toast.LENGTH_LONG).show();
                    return "需要卫星和网络权限！！";
                }
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation)
                        telephonyManager.getCellLocation();
                int cid = cdmaCellLocation.getBaseStationId(); //获取cdma基站识别标号 BID
                int lac = cdmaCellLocation.getNetworkId(); //获取cdma网络编号NID
                int sid = cdmaCellLocation.getSystemId(); //用谷歌API的话cdma网络的mnc要用这个getSystemId()取得→SID
                strTmp = Integer.toString(cid) + Integer.toString(lac) + Integer.toString(sid);
            } else {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                int cid = gsmCellLocation.getCid(); //获取gsm基站识别标号
                int lac = gsmCellLocation.getLac(); //获取gsm网络编号
                strTmp = Integer.toString(cid) + Integer.toString(lac);
            }
        }
        return strTmp;
    }

    public String myCell() {
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                    return null;
                } else {
                    List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
                    try {
                        Gson gson = new Gson();
                        strCellInfo = gson.toJson(cellInfoList);
//                    System.out.print(strCellInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return strCellInfo;
                }
            }
        }
        return null;
    }

    public Map<String, Double> getLatitude() {
        Map<String, Double> map = new HashMap<String, Double>();
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                    return null;
                } else {
//                    List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
                    try {
                        // 获取位置管理服务
                        LocationManager locationManager;
                        String serviceName = Context.LOCATION_SERVICE;
                        locationManager = (LocationManager) mycontext.getSystemService(serviceName);
                        // 查找到服务信息
                        Criteria criteria = new Criteria();
                        criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
                        criteria.setAltitudeRequired(false);
                        criteria.setBearingRequired(false);
                        criteria.setCostAllowed(true);
                        criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
                        String provider = Objects.requireNonNull(locationManager).getBestProvider(criteria, true); // 获取GPS信息
                        Location location = locationManager.getLastKnownLocation(provider);// 通过GPS获取位置
                        String location_json = "{\"getLatitude\":\"\",\"getLongitude\":\"\"}";
                        map.put("getLatitude", location.getLatitude());
                        map.put("getLongitude", location.getLongitude());
                        return map;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private static boolean isLocationEnabled(Context mycontext) {
        //...............
        return true;
    }


    protected void getLocation() {
        if (isLocationEnabled(mymainActivity)) {
            LocationManager locationManager;
            locationManager = (LocationManager) mymainActivity.getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

            //You can still do this if you like, you might get lucky:
            if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                ActivityCompat.requestPermissions(mymainActivity,
                        new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Toast.makeText(mycontext, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
            }
            else{
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, (LocationListener) this);
            }
        }
        else
        {
            //prompt user to enable location....
            //.................
        }
    }

    public double getLongitude() {
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                    return 0;
                } else {
//                    List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
                    try {
                        // 获取位置管理服务
                        LocationManager locationManager;
                        String serviceName = Context.LOCATION_SERVICE;
                        locationManager = (LocationManager)mycontext.getSystemService(serviceName);
                        // 查找到服务信息
                        Criteria criteria =new Criteria();
                        criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
                        criteria.setAltitudeRequired(false);
                        criteria.setBearingRequired(false);
                        criteria.setCostAllowed(true);
                        criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
                        String provider =locationManager.getBestProvider(criteria,true); // 获取GPS信息
                        Location location =locationManager.getLastKnownLocation(provider);// 通过GPS获取位置
                        String location_json = "{\"getLatitude\":\"\",\"getLongitude\":\"\"}";
                        if(location != null) {
                            return location.getLongitude();
//                            double doublelongitude=location.getLongitude();
//                            location_json = "{\"getLatitude\":"+doublelatitude+",\"getLongitude\":"+doublelongitude+"}";
//                            return location_json;
                        }

//                        Gson gson = new Gson();
//                        CellInfoCdma cellInfoCdma = null;
//                        strCellInfo = gson.toJson(cellInfoList);
//                        int a = cellInfoList.size();
//                        for (CellInfo cellInfo : cellInfoList) {
////                        cellInfoCdma= (CellInfoCdma) cellInfo;
////                        cellInfoCdma.getCellIdentity();
////                        cellInfoCdma.getCellSignalStrength();
////                        cellInfoCdma.describeContents();
////                        System.out.println("System.out.println(cellInfo.describeContents());"+cellInfo.describeContents());
//                        }

//                    System.out.print(strCellInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }

}
