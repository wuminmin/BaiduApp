package com.ysc.baiduapp.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.ysc.baiduapp.GridViewAdapter;
import com.ysc.baiduapp.ListViewAdapter;
import com.ysc.baiduapp.MyWebViewClient;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.WebViewFragment;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment implements OnMapReadyCallback {
    MapView mMapView;
    GoogleMap map;
    private WebView cesuWebview;
    private WebView xinxiWebview;
    private ScrollView scrollXinxi;
    private com.google.android.gms.maps.MapView mapView;
    private GetCellInfo getCellInfo;
    private View view;
    private static final int REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private TextView msg;
    private Bundle bundle;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000 * 5);// 间隔120秒
        }
        void update() {
            //刷新msg的内容
//            init();
//            cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
        }
    };

    private void changeToAnotherFragment(){
        //如果是用的v4的包，则用getActivity().getSuppoutFragmentManager();
//        FragmentManager fm = getActivity().getFragmentManager();
        //注意v4包的配套使用
//        Fragment fragment = new Fragment();
//        fm.beginTransaction().replace(R.id.home_fragment,fragment).commit();

        Fragment fragment = new Fragment();
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.my_webview_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable); //停止刷新
        super.onDestroy();
    }

    private void cesuWebViewInit(){
//        cesuWebview =  view.findViewById(R.id.cesuWebview);
        // Force links and redirects to open in the WebView instead of in a browser
        cesuWebview.setWebViewClient(new WebViewClient());
        // Enable Javascript
        WebSettings webSettings = cesuWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // REMOTE RESOURCE
//        cesuWebview.loadUrl("file:///android_asset/indexBase.html?a=1");
        cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
//        cesuWebview.loadUrl("http://www.baidu.com/");
//        cesuWebview.setWebViewClient(new MyWebViewClient());
        // LOCAL RESOURCE
//        cesuWebview.loadUrl("file:///android_asset/index.html");
    }

    private void xinxiWebViewInit(){
//        cesuWebview =  view.findViewById(R.id.cesuWebview);
        // Force links and redirects to open in the WebView instead of in a browser
        xinxiWebview.setWebViewClient(new WebViewClient());
        // Enable Javascript
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String json="{title1:孔子,title2:孟子,title3:庄子}";
        xinxiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return json; // 把本地数据弄成json串，传给html
            }

        }, "MyBrowserAPI");//MyBrowserAPI:自定义的js函数名

        xinxiWebview.loadUrl("file:///android_asset/xinxi.html");
//        cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
//        cesuWebview.loadUrl("http://www.baidu.com/");
//        cesuWebview.setWebViewClient(new MyWebViewClient());
        // LOCAL RESOURCE
//        cesuWebview.loadUrl("file:///android_asset/index.html");
    }

    private void MapViewInit(Bundle savedInstanceState){
        try {
            MapsInitializer.initialize(this.getActivity());
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }
catch (Exception e){
        Log.e("地图报错", "Inflate exception");
    }
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(), telephonyManager, getActivity());
        cesuWebview = view.findViewById(R.id.cesuWebview);
        xinxiWebview = view.findViewById(R.id.xinxiWebview);
        scrollXinxi = view.findViewById(R.id.scorllXinxi);
        mapView = view.findViewById(R.id.mapView);
        bundle = savedInstanceState;

//        webViewInit();
        scrollXinxi.setVisibility(View.GONE);
        cesuWebview.setVisibility(View.GONE);
        mapView.setVisibility(View.GONE);
        ImageView buttonXinxi = view.findViewById(R.id.xinxi);
        ImageView buttonCesu = view.findViewById(R.id.cesu);
        ImageView buttonDitu = view.findViewById(R.id.ditu);
        buttonXinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changeToAnotherFragment();
//                Toast.makeText(getActivity().getApplicationContext(), "测速页面", Toast.LENGTH_LONG).show();
                cesuWebview.setVisibility(View.GONE);
                xinxiWebview.setVisibility(View.VISIBLE);
                mapView.setVisibility(View.GONE);
                xinxiWebViewInit();

            }
        });

        buttonCesu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cesuWebview.setVisibility(View.VISIBLE);
                xinxiWebview.setVisibility(View.GONE);
                mapView.setVisibility(View.GONE);
                cesuWebViewInit();
            }
        });

        buttonDitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cesuWebview.setVisibility(View.GONE);
                xinxiWebview.setVisibility(View.GONE);
                mapView.setVisibility(View.VISIBLE);
                MapViewInit(bundle);

            }
        });

        init();
        handler.postDelayed(runnable, 1000 * 5);
        return view;
    }

    private void init() {
        TextView textViewm_CellIdentityCdma_mBasestationId = view.findViewById(R.id.mCellIdentityCdma_mBasestationId);
        TextView textViewm_CellIdentityCdma_mLatitude = view.findViewById(R.id.mCellIdentityCdma_mLatitude);
        TextView textView_mCellIdentityCdma_mLongitude = view.findViewById(R.id.mCellIdentityCdma_mLongitude);
        TextView textView_mCellIdentityCdma_mNetworkId = view.findViewById(R.id.mCellIdentityCdma_mNetworkId);
        TextView textView_mCellIdentityCdma_mSystemId = view.findViewById(R.id.mCellIdentityCdma_mSystemId);
        TextView textView_mCellSignalStrengthCdma_mCdmaDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaDbm);
        TextView textView_mCellSignalStrengthCdma_mCdmaEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaEcio);
        TextView textView_mCellSignalStrengthCdma_mEvdoDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoDbm);
        TextView textView_mCellSignalStrengthCdma_mEvdoEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoEcio);
        TextView textView_mCellSignalStrengthCdma_mEvdoSnr = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoSnr);
        TextView textView_mCellIdentityLte_mCi = view.findViewById(R.id.mCellIdentityLte_mCi);
        TextView textView_mCellIdentityLte_mEarfcn = view.findViewById(R.id.mCellIdentityLte_mEarfcn);
        TextView textView_mCellIdentityLte_mMcc = view.findViewById(R.id.mCellIdentityLte_mMcc);
        TextView textView_mCellIdentityLte_mMnc = view.findViewById(R.id.mCellIdentityLte_mMnc);
        TextView textView_mCellIdentityLte_mPci = view.findViewById(R.id.mCellIdentityLte_mPci);
        TextView textView_mCellIdentityLte_mTac = view.findViewById(R.id.mCellIdentityLte_mTac);
//        TextView textView_mCellSignalStrengthLte_mCqi = view.findViewById(R.id.mCellSignalStrengthLte_mCqi);
        TextView textView_mCellSignalStrengthLte_mRsrp = view.findViewById(R.id.mCellSignalStrengthLte_mRsrp);
        TextView textView_mCellSignalStrengthLte_mRsrq = view.findViewById(R.id.mCellSignalStrengthLte_mRsrq);
//        TextView textView_mCellSignalStrengthLte_mRssnr = view.findViewById(R.id.mCellSignalStrengthLte_mRssnr);
        TextView textView_mCellSignalStrengthLte_mSignalStrength = view.findViewById(R.id.mCellSignalStrengthLte_mSignalStrength);
//        TextView textView_mCellSignalStrengthLte_mTimingAdvance = view.findViewById(R.id.mCellSignalStrengthLte_mTimingAdvance);
        try {
            String strGetCellInfo;
            String mCellIdentityCdma_mBasestationId;
            String mCellIdentityCdma_mLatitude;
            String mCellIdentityCdma_mLongitude;
            String mCellIdentityCdma_mNetworkId;
            String mCellIdentityCdma_mSystemId;
            String mCellSignalStrengthCdma_mCdmaDbm;
            String mCellSignalStrengthCdma_mCdmaEcio;
            String mCellSignalStrengthCdma_mEvdoDbm;
            String mCellSignalStrengthCdma_mEvdoEcio;
            String mCellSignalStrengthCdma_mEvdoSnr;
            String mCellIdentityCdma_mRegistered;
            String mCellIdentityCdma_mTimeStamp;
            String mCellIdentityCdma_mTimeStampType;
            String mCellIdentityLte_mCi;
            String mCellIdentityLte_mEarfcn;
            String mCellIdentityLte_mMcc;
            String mCellIdentityLte_mMnc;
            String mCellIdentityLte_mPci;
            String mCellIdentityLte_mTac;
            String mCellSignalStrengthLte_mCqi;
            String mCellSignalStrengthLte_mRsrp;
            String mCellSignalStrengthLte_mRsrq;
            String mCellSignalStrengthLte_mRssnr;
            String mCellSignalStrengthLte_mSignalStrength;
            String mCellSignalStrengthLte_mTimingAdvance;
            String mCellIdentityLte_mRegistered;
            String mCellIdentityLte_mTimeStamp;
            String mCellIdentityLte_mTimeStampType;
            strGetCellInfo = getCellInfo.myCell();
//            strGetCellInfo = "[]";
            Map<String, Double> map = getCellInfo.myGps();
            if (!"null".equals(strGetCellInfo) ) {
                String str = "0";
                JSONArray jsonarray = new JSONArray(strGetCellInfo);
                int length = jsonarray.length();
                if (length == 0) {
//                    JSONObject jsonobject0 = jsonarray.getJSONObject(0);

                    mCellIdentityCdma_mBasestationId = str;
                    mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                    mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                    mCellIdentityCdma_mNetworkId = str;
                    mCellIdentityCdma_mSystemId = str;
                    mCellSignalStrengthCdma_mCdmaDbm = str;
                    mCellSignalStrengthCdma_mCdmaEcio = str;
                    mCellSignalStrengthCdma_mEvdoDbm = str;
                    mCellSignalStrengthCdma_mEvdoEcio = str;
                    mCellSignalStrengthCdma_mEvdoSnr = str;
                    mCellIdentityCdma_mRegistered = str;
                    mCellIdentityCdma_mTimeStamp = str;
                    mCellIdentityCdma_mTimeStampType = str;
//                    JSONObject jsonobject1 = jsonarray.getJSONObject(1);
                    mCellIdentityLte_mCi = str;
                    mCellIdentityLte_mEarfcn = str;
                    mCellIdentityLte_mMcc = str;
                    mCellIdentityLte_mMnc = str;
                    mCellIdentityLte_mPci = str;
                    mCellIdentityLte_mTac = str;
                    mCellSignalStrengthLte_mCqi = str;
                    mCellSignalStrengthLte_mRsrp = str;
                    mCellSignalStrengthLte_mRsrq = str;
                    mCellSignalStrengthLte_mRssnr = str;
                    mCellSignalStrengthLte_mSignalStrength = str;
                    mCellSignalStrengthLte_mTimingAdvance = str;
                    mCellIdentityLte_mRegistered = str;
                    mCellIdentityLte_mTimeStamp = str;
                    mCellIdentityLte_mTimeStampType = str;
                } else if (length == 1) {
                    JSONObject jsonobject0 = jsonarray.getJSONObject(0);
                    if (jsonobject0.has("mCellIdentityCdma")) {
                        mCellIdentityCdma_mBasestationId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mBasestationId");
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mNetworkId");
                        mCellIdentityCdma_mSystemId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mSystemId");
                        mCellSignalStrengthCdma_mCdmaDbm = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mCdmaDbm");
                        mCellSignalStrengthCdma_mCdmaEcio = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mCdmaEcio");
                        mCellSignalStrengthCdma_mEvdoDbm = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoDbm");
                        mCellSignalStrengthCdma_mEvdoEcio = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoEcio");
                        mCellSignalStrengthCdma_mEvdoSnr = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoSnr");
                        mCellIdentityCdma_mRegistered = jsonobject0.getString("mRegistered");
                        mCellIdentityCdma_mTimeStamp = jsonobject0.getString("mTimeStamp");
                        mCellIdentityCdma_mTimeStampType = jsonobject0.getString("mTimeStampType");

                        mCellIdentityLte_mCi = str;
                        mCellIdentityLte_mEarfcn = str;
                        mCellIdentityLte_mMcc = str;
                        mCellIdentityLte_mMnc = str;
                        mCellIdentityLte_mPci = str;
                        mCellIdentityLte_mTac = str;
                        mCellSignalStrengthLte_mCqi = str;
                        mCellSignalStrengthLte_mRsrp = str;
                        mCellSignalStrengthLte_mRsrq = str;
                        mCellSignalStrengthLte_mRssnr = str;
                        mCellSignalStrengthLte_mSignalStrength = str;
                        mCellSignalStrengthLte_mTimingAdvance = str;
                        mCellIdentityLte_mRegistered = str;
                        mCellIdentityLte_mTimeStamp = str;
                        mCellIdentityLte_mTimeStampType = str;
                    } else if (jsonobject0.has("mCellIdentityLte")) {
                        mCellIdentityCdma_mBasestationId = str;
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = str;
                        mCellIdentityCdma_mSystemId = str;
                        mCellSignalStrengthCdma_mCdmaDbm = str;
                        mCellSignalStrengthCdma_mCdmaEcio = str;
                        mCellSignalStrengthCdma_mEvdoDbm = str;
                        mCellSignalStrengthCdma_mEvdoEcio = str;
                        mCellSignalStrengthCdma_mEvdoSnr = str;
                        mCellIdentityCdma_mRegistered = str;
                        mCellIdentityCdma_mTimeStamp = str;
                        mCellIdentityCdma_mTimeStampType = str;

                        mCellIdentityLte_mCi = jsonobject0.getJSONObject("mCellIdentityLte").getString("mCi");
                        mCellIdentityLte_mEarfcn = jsonobject0.getJSONObject("mCellIdentityLte").getString("mEarfcn");
                        mCellIdentityLte_mMcc = jsonobject0.getJSONObject("mCellIdentityLte").getString("mMcc");
                        mCellIdentityLte_mMnc = jsonobject0.getJSONObject("mCellIdentityLte").getString("mMnc");
                        mCellIdentityLte_mPci = jsonobject0.getJSONObject("mCellIdentityLte").getString("mPci");
                        mCellIdentityLte_mTac = jsonobject0.getJSONObject("mCellIdentityLte").getString("mTac");
                        mCellSignalStrengthLte_mCqi = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mCqi");
                        mCellSignalStrengthLte_mRsrp = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRsrp");
                        mCellSignalStrengthLte_mRsrq = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRsrq");
                        mCellSignalStrengthLte_mRssnr = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRssnr");
                        mCellSignalStrengthLte_mSignalStrength = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mSignalStrength");
                        mCellSignalStrengthLte_mTimingAdvance = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mTimingAdvance");
                        mCellIdentityLte_mRegistered = jsonobject0.getString("mRegistered");
                        mCellIdentityLte_mTimeStamp = jsonobject0.getString("mTimeStamp");
                        mCellIdentityLte_mTimeStampType = jsonobject0.getString("mTimeStampType");
                    } else {
                        mCellIdentityCdma_mBasestationId = str;
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = str;
                        mCellIdentityCdma_mSystemId = str;
                        mCellSignalStrengthCdma_mCdmaDbm = str;
                        mCellSignalStrengthCdma_mCdmaEcio = str;
                        mCellSignalStrengthCdma_mEvdoDbm = str;
                        mCellSignalStrengthCdma_mEvdoEcio = str;
                        mCellSignalStrengthCdma_mEvdoSnr = str;
                        mCellIdentityCdma_mRegistered = str;
                        mCellIdentityCdma_mTimeStamp = str;
                        mCellIdentityCdma_mTimeStampType = str;
//                    JSONObject jsonobject1 = jsonarray.getJSONObject(1);
                        mCellIdentityLte_mCi = str;
                        mCellIdentityLte_mEarfcn = str;
                        mCellIdentityLte_mMcc = str;
                        mCellIdentityLte_mMnc = str;
                        mCellIdentityLte_mPci = str;
                        mCellIdentityLte_mTac = str;
                        mCellSignalStrengthLte_mCqi = str;
                        mCellSignalStrengthLte_mRsrp = str;
                        mCellSignalStrengthLte_mRsrq = str;
                        mCellSignalStrengthLte_mRssnr = str;
                        mCellSignalStrengthLte_mSignalStrength = str;
                        mCellSignalStrengthLte_mTimingAdvance = str;
                        mCellIdentityLte_mRegistered = str;
                        mCellIdentityLte_mTimeStamp = str;
                        mCellIdentityLte_mTimeStampType = str;
                    }
                } else {
                    JSONObject jsonobject0 = jsonarray.getJSONObject(0);
                    JSONObject jsonobject1 = jsonarray.getJSONObject(1);
                    if(jsonobject0.has("mCellIdentityCdma")) {

                        mCellIdentityCdma_mBasestationId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mBasestationId");
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mNetworkId");
                        mCellIdentityCdma_mSystemId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mSystemId");
                        mCellSignalStrengthCdma_mCdmaDbm = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mCdmaDbm");
                        mCellSignalStrengthCdma_mCdmaEcio = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mCdmaEcio");
                        mCellSignalStrengthCdma_mEvdoDbm = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoDbm");
                        mCellSignalStrengthCdma_mEvdoEcio = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoEcio");
                        mCellSignalStrengthCdma_mEvdoSnr = jsonobject0.getJSONObject("mCellSignalStrengthCdma").getString("mEvdoSnr");
                        mCellIdentityCdma_mRegistered = jsonobject0.getString("mRegistered");
                        mCellIdentityCdma_mTimeStamp = jsonobject0.getString("mTimeStamp");
                        mCellIdentityCdma_mTimeStampType = jsonobject0.getString("mTimeStampType");

                        if(jsonobject1.has("mCellIdentityLte")){
                            mCellIdentityLte_mCi = jsonobject1.getJSONObject("mCellIdentityLte").getString("mCi");
                            mCellIdentityLte_mEarfcn = jsonobject1.getJSONObject("mCellIdentityLte").getString("mEarfcn");
                            mCellIdentityLte_mMcc = jsonobject1.getJSONObject("mCellIdentityLte").getString("mMcc");
                            mCellIdentityLte_mMnc = jsonobject1.getJSONObject("mCellIdentityLte").getString("mMnc");
                            mCellIdentityLte_mPci = jsonobject1.getJSONObject("mCellIdentityLte").getString("mPci");
                            mCellIdentityLte_mTac = jsonobject1.getJSONObject("mCellIdentityLte").getString("mTac");
                            mCellSignalStrengthLte_mCqi = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mCqi");
                            mCellSignalStrengthLte_mRsrp = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRsrp");
                            mCellSignalStrengthLte_mRsrq = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRsrq");
                            mCellSignalStrengthLte_mRssnr = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRssnr");
                            mCellSignalStrengthLte_mSignalStrength = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mSignalStrength");
                            mCellSignalStrengthLte_mTimingAdvance = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mTimingAdvance");
                            mCellIdentityLte_mRegistered = jsonobject1.getString("mRegistered");
                            mCellIdentityLte_mTimeStamp = jsonobject1.getString("mTimeStamp");
                            mCellIdentityLte_mTimeStampType = jsonobject1.getString("mTimeStampType");
                        }else {
                            mCellIdentityLte_mCi = str;
                            mCellIdentityLte_mEarfcn = str;
                            mCellIdentityLte_mMcc = str;
                            mCellIdentityLte_mMnc = str;
                            mCellIdentityLte_mPci = str;
                            mCellIdentityLte_mTac = str;
                            mCellSignalStrengthLte_mCqi = str;
                            mCellSignalStrengthLte_mRsrp = str;
                            mCellSignalStrengthLte_mRsrq = str;
                            mCellSignalStrengthLte_mRssnr = str;
                            mCellSignalStrengthLte_mSignalStrength = str;
                            mCellSignalStrengthLte_mTimingAdvance = str;
                            mCellIdentityLte_mRegistered = str;
                            mCellIdentityLte_mTimeStamp = str;
                            mCellIdentityLte_mTimeStampType = str;
                        }
                    }else if(jsonobject0.has("mCellIdentityLte")){
                        mCellIdentityCdma_mBasestationId = str;
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = str;
                        mCellIdentityCdma_mSystemId = str;
                        mCellSignalStrengthCdma_mCdmaDbm = str;
                        mCellSignalStrengthCdma_mCdmaEcio = str;
                        mCellSignalStrengthCdma_mEvdoDbm = str;
                        mCellSignalStrengthCdma_mEvdoEcio = str;
                        mCellSignalStrengthCdma_mEvdoSnr = str;
                        mCellIdentityCdma_mRegistered = str;
                        mCellIdentityCdma_mTimeStamp = str;
                        mCellIdentityCdma_mTimeStampType = str;

                        mCellIdentityLte_mCi = jsonobject0.getJSONObject("mCellIdentityLte").getString("mCi");
                        mCellIdentityLte_mEarfcn = jsonobject0.getJSONObject("mCellIdentityLte").getString("mEarfcn");
                        mCellIdentityLte_mMcc = jsonobject0.getJSONObject("mCellIdentityLte").getString("mMcc");
                        mCellIdentityLte_mMnc = jsonobject0.getJSONObject("mCellIdentityLte").getString("mMnc");
                        mCellIdentityLte_mPci = jsonobject0.getJSONObject("mCellIdentityLte").getString("mPci");
                        mCellIdentityLte_mTac = jsonobject0.getJSONObject("mCellIdentityLte").getString("mTac");
                        mCellSignalStrengthLte_mCqi = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mCqi");
                        mCellSignalStrengthLte_mRsrp = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRsrp");
                        mCellSignalStrengthLte_mRsrq = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRsrq");
                        mCellSignalStrengthLte_mRssnr = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mRssnr");
                        mCellSignalStrengthLte_mSignalStrength = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mSignalStrength");
                        mCellSignalStrengthLte_mTimingAdvance = jsonobject0.getJSONObject("mCellSignalStrengthLte").getString("mTimingAdvance");
                        mCellIdentityLte_mRegistered = jsonobject0.getString("mRegistered");
                        mCellIdentityLte_mTimeStamp = jsonobject0.getString("mTimeStamp");
                        mCellIdentityLte_mTimeStampType = jsonobject0.getString("mTimeStampType");
                    } else {
                        mCellIdentityCdma_mBasestationId = str;
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
                        mCellIdentityCdma_mLongitude = Double.toString(map.get("getLongitude"));
                        mCellIdentityCdma_mNetworkId = str;
                        mCellIdentityCdma_mSystemId = str;
                        mCellSignalStrengthCdma_mCdmaDbm = str;
                        mCellSignalStrengthCdma_mCdmaEcio = str;
                        mCellSignalStrengthCdma_mEvdoDbm = str;
                        mCellSignalStrengthCdma_mEvdoEcio = str;
                        mCellSignalStrengthCdma_mEvdoSnr = str;
                        mCellIdentityCdma_mRegistered = str;
                        mCellIdentityCdma_mTimeStamp = str;
                        mCellIdentityCdma_mTimeStampType = str;
//                    JSONObject jsonobject1 = jsonarray.getJSONObject(1);
                        mCellIdentityLte_mCi = str;
                        mCellIdentityLte_mEarfcn = str;
                        mCellIdentityLte_mMcc = str;
                        mCellIdentityLte_mMnc = str;
                        mCellIdentityLte_mPci = str;
                        mCellIdentityLte_mTac = str;
                        mCellSignalStrengthLte_mCqi = str;
                        mCellSignalStrengthLte_mRsrp = str;
                        mCellSignalStrengthLte_mRsrq = str;
                        mCellSignalStrengthLte_mRssnr = str;
                        mCellSignalStrengthLte_mSignalStrength = str;
                        mCellSignalStrengthLte_mTimingAdvance = str;
                        mCellIdentityLte_mRegistered = str;
                        mCellIdentityLte_mTimeStamp = str;
                        mCellIdentityLte_mTimeStampType = str;
                    }

                }

                textViewm_CellIdentityCdma_mBasestationId.setText(mCellIdentityCdma_mBasestationId);
                textViewm_CellIdentityCdma_mLatitude.setText(mCellIdentityCdma_mLatitude);
                textView_mCellIdentityCdma_mLongitude.setText(mCellIdentityCdma_mLongitude);
                textView_mCellIdentityCdma_mNetworkId.setText(mCellIdentityCdma_mNetworkId);
                textView_mCellIdentityCdma_mSystemId.setText(mCellIdentityCdma_mSystemId);
                textView_mCellSignalStrengthCdma_mCdmaDbm.setText(mCellSignalStrengthCdma_mCdmaDbm);
                textView_mCellSignalStrengthCdma_mCdmaEcio.setText(modiferEcio(mCellSignalStrengthCdma_mCdmaEcio));
                textView_mCellSignalStrengthCdma_mEvdoDbm.setText(mCellSignalStrengthCdma_mEvdoDbm);
                textView_mCellSignalStrengthCdma_mEvdoEcio.setText(modiferEcio(mCellSignalStrengthCdma_mEvdoEcio));
                textView_mCellSignalStrengthCdma_mEvdoSnr.setText(mCellSignalStrengthCdma_mEvdoSnr);
                textView_mCellIdentityLte_mCi.setText(mCellIdentityLte_mCi);
                textView_mCellIdentityLte_mEarfcn.setText(mCellIdentityLte_mEarfcn);
                textView_mCellIdentityLte_mMcc.setText(mCellIdentityLte_mMcc);
                textView_mCellIdentityLte_mMnc.setText(mCellIdentityLte_mMnc);
                textView_mCellIdentityLte_mPci.setText(mCellIdentityLte_mPci);
                textView_mCellIdentityLte_mTac.setText(mCellIdentityLte_mTac);
//                textView_mCellSignalStrengthLte_mCqi.setText(mCellSignalStrengthLte_mCqi);
                textView_mCellSignalStrengthLte_mRsrp.setText(mCellSignalStrengthLte_mRsrp);
                textView_mCellSignalStrengthLte_mRsrq.setText(mCellSignalStrengthLte_mRsrq);
//                textView_mCellSignalStrengthLte_mRssnr.setText(mCellSignalStrengthLte_mRssnr);
                textView_mCellSignalStrengthLte_mSignalStrength.setText(mCellSignalStrengthLte_mSignalStrength);
//                textView_mCellSignalStrengthLte_mTimingAdvance.setText(mCellSignalStrengthLte_mTimingAdvance);
            } else {
                textViewm_CellIdentityCdma_mBasestationId.setText("无法识别小区信息，可能需要授权");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    //Ecio 除以10
    @NonNull
    private String modiferEcio(String ecio) {
        try {
            int ecio_int = Integer.parseInt(ecio);
            int ecio_int_result = ecio_int / 10;
            return String.valueOf(ecio_int_result);
        }
        catch (Exception e){
            return ecio;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
