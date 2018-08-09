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
import com.ysc.baiduapp.service.MyTest;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment implements OnMapReadyCallback {
    private WebView cesuWebview;
    private WebView xinxiWebview;
    private GetCellInfo getCellInfo;
    private View view;
    private static final int REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private TextView msg;
    private Bundle bundle;
    private Handler handler = new Handler();
    private WebView mapWebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(), telephonyManager, getActivity());
        xinxiWebview = view.findViewById(R.id.xinxiWebview);
        cesuWebview = view.findViewById(R.id.cesuWebview);
        mapWebview = view.findViewById(R.id.mapWebview);
        xinxiInit(xinxiWebview);
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
                xinxiWebViewInit( xinxiWebview );

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
            xinxiWebViewInit(xinxiWebview);
            //刷新msg的内容
//            init();
//            cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
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

    private void xinxiInit(WebView xinxiWebview){
        xinxiWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        xinxiWebview.loadUrl("file:///android_asset/xinxi.html");
    }

    private void xinxiWebViewInit(WebView xinxiWebview ){
//        cesuWebview =  view.findViewById(R.id.cesuWebview);
        // Force links and redirects to open in the WebView instead of in a browser
        xinxiWebview.setWebViewClient(new WebViewClient());
        // Enable Javascript
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String rsrp = getCellInfo.getRsrp();
        MyTest myTest = new MyTest();

        String rsrpstirng = "[1, -90], [2, -98],  [3, -80],  [4, -85],  [5, "+myTest.getSec()+"], [6, "+rsrp+"]";
//        final String json = "[1, 34], [2, 0],  [3, 0],    [4, 34],    [5, 32],   [6, 0]";
        final String json = rsrpstirng;
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

    private void MapViewInit(WebView xinxiWebview ){
        //        cesuWebview =  view.findViewById(R.id.cesuWebview);
        // Force links and redirects to open in the WebView instead of in a browser
        xinxiWebview.setWebViewClient(new WebViewClient());
        // Enable Javascript
        WebSettings webSettings = xinxiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String rsrp = getCellInfo.getRsrp();
        MyTest myTest = new MyTest();

        String rsrpstirng = "[1, -90], [2, -98],  [3, -80],  [4, -85],  [5, "+myTest.getSec()+"], [6, "+rsrp+"]";
//        final String json = "[1, 34], [2, 0],  [3, 0],    [4, 34],    [5, 32],   [6, 0]";
        final String json = rsrpstirng;
        xinxiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return json; // 把本地数据弄成json串，传给html
            }

        }, "MyBrowserAPI");//MyBrowserAPI:自定义的js函数名

        xinxiWebview.loadUrl("file:///android_asset/map.html");
//        cesuWebview.loadUrl("http://ahdx.speedtestcustom.com/");
//        cesuWebview.loadUrl("http://www.baidu.com/");
//        cesuWebview.setWebViewClient(new MyWebViewClient());
        // LOCAL RESOURCE
//        cesuWebview.loadUrl("file:///android_asset/index.html");
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
