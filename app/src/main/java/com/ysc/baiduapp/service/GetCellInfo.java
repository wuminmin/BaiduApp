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
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
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
    private List<CellInfo> cellInfoList = null;


    public static void main(String[] args){
        String jsonstr = "[{\"mCellIdentityCdma\":{\"mBasestationId\":60621,\"mLatitude\":441299,\"mLongitude\":1691876,\"mNetworkId\":18,\"mSystemId\":14166},\"mCellSignalStrengthCdma\":{\"mCdmaDbm\":-92,\"mCdmaEcio\":-70,\"mEvdoDbm\":2147483647,\"mEvdoEcio\":2147483647,\"mEvdoSnr\":2147483647},\"mRegistered\":true,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":114555190,\"mEarfcn\":1825,\"mMcc\":460,\"mMnc\":11,\"mPci\":178,\"mTac\":27963},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-99,\"mRsrq\":-8,\"mRssnr\":2147483647,\"mSignalStrength\":21,\"mTimingAdvance\":2147483647},\"mRegistered\":true,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":72,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-113,\"mRsrq\":-20,\"mRssnr\":2147483647,\"mSignalStrength\":15,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":73,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-110,\"mRsrq\":-12,\"mRssnr\":2147483647,\"mSignalStrength\":15,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":341,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-110,\"mRsrq\":-16,\"mRssnr\":2147483647,\"mSignalStrength\":14,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":353,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-109,\"mRsrq\":-19,\"mRssnr\":2147483647,\"mSignalStrength\":17,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":48,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-113,\"mRsrq\":-18,\"mRssnr\":2147483647,\"mSignalStrength\":14,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":1825,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":249,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-112,\"mRsrq\":-14,\"mRssnr\":2147483647,\"mSignalStrength\":14,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-118,\"mRsrq\":-9,\"mRssnr\":2147483647,\"mSignalStrength\":6,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-111,\"mRsrq\":-20,\"mRssnr\":2147483647,\"mSignalStrength\":16,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-105,\"mRsrq\":-16,\"mRssnr\":2147483647,\"mSignalStrength\":16,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3},{\"mCellIdentityLte\":{\"mCi\":2147483647,\"mEarfcn\":2147483647,\"mMcc\":2147483647,\"mMnc\":2147483647,\"mPci\":2147483647,\"mTac\":2147483647},\"mCellSignalStrengthLte\":{\"mCqi\":2147483647,\"mRsrp\":-101,\"mRsrq\":-12,\"mRssnr\":2147483647,\"mSignalStrength\":16,\"mTimingAdvance\":2147483647},\"mRegistered\":false,\"mTimeStamp\":178344181197155,\"mTimeStampType\":3}]\n";
//        JsonParser parser = new JsonParser();
//        JsonObject o = parser.parse(jsonstr).getAsJsonObject();
//        System.out.print(o);
        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(jsonstr);
        JsonArray jsonArray = tradeElement.getAsJsonArray();
        for(int i=0; i<jsonArray.size(); i++){
//            System.out.println(jsonArray.get(i));
            try {
                JSONObject json = new JSONObject (String.valueOf(jsonArray.get(i)));
                Object r = json.get("mCellIdentityCdma");
                System.out.println(r);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public GetCellInfo(Context c, TelephonyManager t, FragmentActivity m) {
        mycontext = c;
        telephonyManager = t;
        mymainActivity = m;
    }

    private List<CellInfo> getList(){
        if (telephonyManager == null) {
            new AlertDialog.Builder(mycontext).setTitle("错误").setMessage("内部错误 telephonyManager").setPositiveButton("确定", null).show();
        } else {
            if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                if (ActivityCompat.checkSelfPermission(mycontext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mymainActivity,
                            new String[]{ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                    Toast.makeText(this.mycontext, "请求卫星和网络权限！！", Toast.LENGTH_LONG).show();
                } else {
                    cellInfoList = telephonyManager.getAllCellInfo();
                    return cellInfoList ;
                }
            }
        }
        return new List<CellInfo>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<CellInfo> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(CellInfo cellInfo) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends CellInfo> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends CellInfo> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public CellInfo get(int i) {
                return null;
            }

            @Override
            public CellInfo set(int i, CellInfo cellInfo) {
                return null;
            }

            @Override
            public void add(int i, CellInfo cellInfo) {

            }

            @Override
            public CellInfo remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<CellInfo> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<CellInfo> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<CellInfo> subList(int i, int i1) {
                return null;
            }
        };
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
                     cellInfoList = telephonyManager.getAllCellInfo();
                    try {
                        Gson gson = new Gson();
                        strCellInfo = gson.toJson(cellInfoList);
                        Log.e("strCellInfo", strCellInfo);
//                        JsonParser parser = new JsonParser();
//                        JsonObject o = parser.parse(strCellInfo).getAsJsonObject();
//                        Log.e("JsonObject", o.toString());

                        } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return strCellInfo;
                }
            }
        }
        return null;
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
                            map.put("getLatitude",   Double.valueOf(getLatitude)   );
                            map.put("getLongitude",  Double.valueOf(getLongitude)    );
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

    public String getRsrp(){
        String rsrp  = "";
        cellInfoList = this.getList();
        for (CellInfo cellInfo : cellInfoList)
        {
            //获取所有Lte网络信息
            if (cellInfo instanceof CellInfoLte)
            {
//                str.append("["+index+"]==CellInfoLte"+"\n");
                if(cellInfo.isRegistered()){
//                    str.append("isRegistered=YES"+"\n");
                    CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte)cellInfo).getCellSignalStrength();
                    int intRsrp = cellSignalStrengthLte.getRsrp();
                    rsrp = Integer.toString(intRsrp);
                }
            }
            //获取所有的cdma网络信息
//            if(cellInfo instanceof CellInfoCdma){
//                str.append("["+index+"]==CellInfoCdma"+"\n");
//                if(cellInfo.isRegistered()){
//                    str.append("isRegistered=YES"+"\n");
//                }
//                str.append("TimeStamp:"+cellInfo.getTimeStamp()+"\n");
//                str.append(((CellInfoCdma)cellInfo).getCellIdentity().toString()+"\n");
//                str.append(((CellInfoCdma)cellInfo).getCellSignalStrength().toString()+"\n");
//            }
//            //获取所有的Gsm网络
//            if(cellInfo instanceof CellInfoGsm){
//                str.append("["+index+"]==CellInfoGsm"+"\n");
//                if(cellInfo.isRegistered()){
//                    str.append("isRegistered=YES"+"\n");
//                }
//                str.append("TimeStamp:"+cellInfo.getTimeStamp()+"\n");
//                str.append(((CellInfoGsm)cellInfo).getCellIdentity().toString()+"\n");
//                str.append(((CellInfoGsm)cellInfo).getCellSignalStrength().toString()+"\n");
//            }
//            //获取所有的Wcdma网络
//            if(cellInfo instanceof CellInfoWcdma){
//                str.append("["+index+"]==CellInfoWcdma"+"\n");
//                if(cellInfo.isRegistered()){
//                    str.append("isRegistered=YES"+"\n");
//                }
//                str.append("TimeStamp:"+cellInfo.getTimeStamp()+"\n");
//                str.append(((CellInfoWcdma)cellInfo).getCellIdentity().toString()+"\n");
//                str.append(((CellInfoWcdma)cellInfo).getCellSignalStrength().toString()+"\n");
//            }
//            index++;
        }
        return rsrp;
    }
}
