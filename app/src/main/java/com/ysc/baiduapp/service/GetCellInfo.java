package com.ysc.baiduapp.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthLte;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ysc.baiduapp.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

public class GetCellInfo {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private FragmentActivity mymainActivity;
    private Context mycontext;
    private TelephonyManager telephonyManager;
    private String strTmp = "";
    private String strCellInfo = "";
    private JSONObject jsonObject = null;

    public GetCellInfo(Context c, TelephonyManager t, FragmentActivity m) {
        mycontext = c;
        telephonyManager = t;
        mymainActivity = m;
    }

    public Map<String, Double> myGps() {
        Map<String, Double> map = new HashMap<String, Double>();
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                    map.put("getLatitude", (double) 0);
                    map.put("getLongitude", (double) 0);
                    return map;
                } else {
//                    List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
                    try {
                        // 获取位置管理服务
                        LocationManager locationManager;
                        locationManager = (LocationManager) mycontext.getSystemService(LOCATION_SERVICE);
                        // 查找到服务信息
                        Criteria criteria = new Criteria();
                        criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
                        criteria.setAltitudeRequired(false);
                        criteria.setBearingRequired(false);
                        criteria.setCostAllowed(true);
                        criteria.setPowerRequirement(Criteria.POWER_HIGH);// 低功耗
                        String provider = Objects.requireNonNull(locationManager).getBestProvider(criteria, true); // 获取GPS信息
                        Location location = getLastKnownLocation();
                        if (location != null) {
                            Log.e("TAG", "GPS is on");
                            DecimalFormat df = new DecimalFormat("#.000000");
                            String getLatitude = df.format(location.getLatitude());
                            String getLongitude = df.format(location.getLongitude());
                            map.put("getLatitude", Double.valueOf(getLatitude));
                            map.put("getLongitude", Double.valueOf(getLongitude));
                            return map;
                        } else {
                            //This is what you need:
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
                                @Override
                                public void onLocationChanged(Location location) {
                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {
                                }

                                @Override
                                public void onProviderEnabled(String provider) {
                                }

                                @Override
                                public void onProviderDisabled(String provider) {
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put("getLatitude", (double) 0);
        map.put("getLongitude", (double) 0);
        return map;
    }

    private Location getLastKnownLocation() {
        LocationManager mLocationManager;
        mLocationManager = (LocationManager) mycontext.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(mymainActivity,
                        new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                return null;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    public JsonObject getRsrpCellSignalStrengthLte() {
        JsonObject cellJson = new JsonObject();
        try {
            if (telephonyManager == null) {
                new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
            } else {
                if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                    if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(mymainActivity,
                                new String[]{ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                        Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                        return cellJson;
                    } else {
                        List<CellInfo> myCellInfoList = telephonyManager.getAllCellInfo();
                        for (CellInfo cellInfo : myCellInfoList) {
                            //获取所有Lte网络信息
                            if ( cellInfo instanceof CellInfoLte ) {
                                if (cellInfo.isRegistered()) {
                                    CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                                    int getRsrp = cellSignalStrengthLte.getRsrp() == 2147483647 ? 0 : cellSignalStrengthLte.getRsrp();
                                    int getRssnr = cellSignalStrengthLte.getRssnr() == 2147483647 ? 0 : (cellSignalStrengthLte.getRssnr());
                                    int getAsuLevel = cellSignalStrengthLte.getAsuLevel() == 2147483647 ? 0 : (cellSignalStrengthLte.getAsuLevel());
                                    int getLevel = cellSignalStrengthLte.getLevel() == 2147483647 ? 0 : (cellSignalStrengthLte.getLevel());
                                    int getCqi = cellSignalStrengthLte.getCqi() == 2147483647 ? 0 : (cellSignalStrengthLte.getCqi());
                                    int getDbm = cellSignalStrengthLte.getDbm() == 2147483647 ? 0 : (cellSignalStrengthLte.getDbm());
                                    int getRsrq = cellSignalStrengthLte.getRsrq() == 2147483647 ? 0 : (cellSignalStrengthLte.getRsrq());
                                    int getTimingAdvance = cellSignalStrengthLte.getTimingAdvance() == 2147483647 ? 0 : (cellSignalStrengthLte.getTimingAdvance());

                                    cellJson.addProperty("getRsrpCellInfoLte",getRsrp);
                                    cellJson.addProperty("getRssnrCellInfoLte",getRssnr);
                                    cellJson.addProperty("getAsuLevelCellInfoLte",getAsuLevel);
                                    cellJson.addProperty("getLevelCellInfoLte",getLevel);
                                    cellJson.addProperty("getCqiCellInfoLte",getCqi);
                                    cellJson.addProperty("getDbmCellInfoLte",getDbm);
                                    cellJson.addProperty("getRsrqCellInfoLte",getRsrq);
                                    cellJson.addProperty("getTimingAdvanceCellInfoLte",getTimingAdvance);

                                    CellIdentityLte cellIdentityLte = ((CellInfoLte) cellInfo).getCellIdentity();
                                    int getCi = cellIdentityLte.getCi() == 2147483647 ? 0 : ( cellIdentityLte.getCi() );
                                    int getEarfcn = cellIdentityLte.getEarfcn() == 2147483647 ? 0 : ( cellIdentityLte.getEarfcn() );
                                    int getMcc = cellIdentityLte.getMcc() == 2147483647 ? 0 : ( cellIdentityLte.getMcc() );
                                    int getMnc = cellIdentityLte.getMnc() == 2147483647 ? 0 : ( cellIdentityLte.getMnc() );
                                    int getPci = cellIdentityLte.getPci() == 2147483647 ? 0 : ( cellIdentityLte.getPci() );
                                    int getTac = cellIdentityLte.getTac() == 2147483647 ? 0 : ( cellIdentityLte.getTac() );

                                    cellJson.addProperty("getCiCellIdentityLte",getCi);
                                    cellJson.addProperty("getEarfcnCellIdentityLte",getEarfcn);
                                    cellJson.addProperty("getMccCellIdentityLte",getMcc);
                                    cellJson.addProperty("getMncCellIdentityLte",getMnc);
                                    cellJson.addProperty("getPciCellIdentityLte",getPci);
                                    cellJson.addProperty("getTacCellIdentityLte",getTac);
                                }
                            }

                            if ( cellInfo instanceof CellInfoCdma ) {
                                if (cellInfo.isRegistered()) {
                                    CellSignalStrengthCdma cellSignalStrengthCdma = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                                    int getAsuLevel = cellSignalStrengthCdma.getAsuLevel() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getAsuLevel() );
                                    int getLevel = cellSignalStrengthCdma.getLevel() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getLevel() );
                                    int getCdmaLevel = cellSignalStrengthCdma.getCdmaLevel() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getCdmaLevel() );
                                    int getEvdoLevel = cellSignalStrengthCdma.getEvdoLevel() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getEvdoLevel() );
                                    int getCdmaDbm = cellSignalStrengthCdma.getCdmaDbm() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getCdmaDbm() );
                                    int getCdmaEcio = cellSignalStrengthCdma.getCdmaEcio() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getCdmaEcio() );
                                    int getDbm = cellSignalStrengthCdma.getDbm() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getDbm() );
                                    int getEvdoDbm = cellSignalStrengthCdma.getEvdoDbm() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getEvdoDbm() );
                                    int getEvdoEcio = cellSignalStrengthCdma.getEvdoEcio() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getEvdoEcio() );
                                    int getEvdoSnr = cellSignalStrengthCdma.getEvdoSnr() == 2147483647 ? 0 : ( cellSignalStrengthCdma.getEvdoSnr() );
                                    cellJson.addProperty("getAsuLevelCellSignalStrengthCdma",getAsuLevel);
                                    cellJson.addProperty("getLevelCellSignalStrengthCdma",getLevel);
                                    cellJson.addProperty("getCdmaLevelCellSignalStrengthCdma",getCdmaLevel);
                                    cellJson.addProperty("getEvdoLevelCellSignalStrengthCdma",getEvdoLevel);
                                    cellJson.addProperty("getCdmaDbmCellSignalStrengthCdma",getCdmaDbm);
                                    cellJson.addProperty("getCdmaEcioCellSignalStrengthCdma",getCdmaEcio);
                                    cellJson.addProperty("getDbmCellSignalStrengthCdma",getDbm);
                                    cellJson.addProperty("getEvdoDbmCellSignalStrengthCdma",getEvdoDbm);
                                    cellJson.addProperty("getEvdoEcioCellSignalStrengthCdma",getEvdoEcio);
                                    cellJson.addProperty("getEvdoSnrCellSignalStrengthCdma",getEvdoSnr);

                                    CellIdentityCdma cellIdentityCdma = ((CellInfoCdma) cellInfo).getCellIdentity();
                                    int getBasestationId = cellIdentityCdma.getBasestationId() == 2147483647 ? 0 : ( cellIdentityCdma.getBasestationId() );
                                    int getNetworkId = cellIdentityCdma.getNetworkId() == 2147483647 ? 0 : ( cellIdentityCdma.getNetworkId() );
                                    int getSystemId = cellIdentityCdma.getSystemId() == 2147483647 ? 0 : ( cellIdentityCdma.getSystemId() );
                                    int getLatitude = cellIdentityCdma.getLatitude() == 2147483647 ? 0 : ( cellIdentityCdma.getLatitude() );
                                    int getLongitude = cellIdentityCdma.getLongitude() == 2147483647 ? 0 : ( cellIdentityCdma.getLongitude() );
                                    cellJson.addProperty("getBasestationIdCellIdentityCdma",getBasestationId);
                                    cellJson.addProperty("getNetworkIdCellIdentityCdma",getNetworkId);
                                    cellJson.addProperty("getSystemIdCellIdentityCdma",getSystemId);
                                    cellJson.addProperty("getLatitudeCellIdentityCdma",getLatitude);
                                    cellJson.addProperty("getLongitudeCellIdentityCdma",getLongitude);

                                }
                            }

                            if ( cellInfo instanceof CellInfoGsm) {
                            }

                            if ( cellInfo instanceof CellInfoWcdma) {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("getRsrpCellSignalStrengthLte", e.toString());
        }
        return cellJson;
    }



}
