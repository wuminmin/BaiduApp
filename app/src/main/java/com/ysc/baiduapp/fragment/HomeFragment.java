package com.ysc.baiduapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.service.MyTest;
import com.ysc.baiduapp.service.SignalMethod;
import com.ysc.baiduapp.service.SignalStrengths;
import com.ysc.baiduapp.service.XinxiJson;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import java.util.Arrays;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment implements OnMapReadyCallback {
    private WebView cesuWebview;
    private WebView xinxiWebview;
    private View view;
    private static final int REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private TextView msg;
    private Bundle bundle;
    private Handler handler = new Handler();
    private WebView mapWebview;
    private XinxiJson xinxiJson;
    private Context mycontext;
    private FragmentActivity mymainActivity;
    private TelephonyManager telephonyManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        mycontext = getActivity().getApplicationContext();
        mymainActivity = getActivity();
         telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        xinxiJson = new XinxiJson(  getActivity().getApplicationContext(), telephonyManager, getActivity()  );
        xinxiWebview = view.findViewById(R.id.xinxiWebview);
        cesuWebview = view.findViewById(R.id.cesuWebview);
        mapWebview = view.findViewById(R.id.mapWebview);
        xinxiInit(xinxiWebview ,  telephonyManager);
        bundle = savedInstanceState;
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
                mapWebview.setVisibility(View.GONE);
            }
        });

        buttonCesu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cesuWebview.setVisibility(View.VISIBLE);
                xinxiWebview.setVisibility(View.GONE);
                mapWebview.setVisibility(View.GONE);
                cesuWebViewInit( cesuWebview );
            }
        });

        buttonDitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cesuWebview.setVisibility(View.GONE);
                xinxiWebview.setVisibility(View.GONE);
                mapWebview.setVisibility(View.VISIBLE);
                MapViewInit( mapWebview );

            }
        });
        handler.postDelayed(runnable, 1000 * 5);
        return view;
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000 * 5);// 间隔120秒
        }
        void update() {
            GetCellInfo getCellInfo = new GetCellInfo(mycontext,telephonyManager,mymainActivity);
            getCellInfo.saveCel();
            xinxiJson.saveXinxiJson();
            xinxiWebViewInit(xinxiWebview );
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable); //停止刷新
        super.onDestroy();
    }

    private void cesuWebViewInit(WebView cesuWebview){
        cesuWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = cesuWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
    }

    private void xinxiInit(WebView xinxiWebview  , TelephonyManager telephonyManager ){

        telephonyManager.listen(new PhoneStateListener() {
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);


                for (SignalMethod method : SignalStrengths.getMethods()) {
//                    double level = 0;
                    try {
//                        level = method.getLevel(signalStrength);
                        String ssignal = signalStrength.toString();

                        String[] parts = ssignal.split(" ");

                        Log.e("SignalMethod测试", Arrays.toString(parts));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        xinxiWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        xinxiWebview.loadUrl("file:///android_asset/xinxi.html");
    }

    private void xinxiWebViewInit(WebView xinxiWebview ){



        xinxiWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final String json = xinxiJson.getXinxiJsonAll();
        xinxiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return json; // 把本地数据弄成json串，传给html
            }

        }, "MyBrowserAPI");//MyBrowserAPI:自定义的js函数名
        xinxiWebview.loadUrl("file:///android_asset/xinxi.html");
    }

    private void MapViewInit(WebView xinxiWebview ){
        //        cesuWebview =  view.findViewById(R.id.cesuWebview);
        // Force links and redirects to open in the WebView instead of in a browser
        xinxiWebview.setWebViewClient(new WebViewClient());
        // Enable Javascript
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String jsonXinxi = xinxiJson.getXinxiJsonOne();
        xinxiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return jsonXinxi; // 把本地数据弄成json串，传给html
            }
        }, "getXinxiJsonOne");//MyBrowserAPI:自定义的js函数名

        xinxiWebview.loadUrl("file:///android_asset/map.html");
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
