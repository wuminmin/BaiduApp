package com.ysc.baiduapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ysc.baiduapp.R;
import com.ysc.baiduapp.database.DatabaseHelper;
import com.ysc.baiduapp.database.model.Note;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.service.LQRPhotoSelectUtils;
import com.ysc.baiduapp.service.PostExample;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by wjx on 2016-1-12.
 */
public class OrderFragment extends BaseFragment {
    private ImageView iv_order;
    private View view;
    private GetCellInfo getCellInfo;
    private WebView shinengWebview;
    private WebView shiwaiWebview;
    private Button mBtnTakePhoto;
    private Button mBtnSelectPhoto;
    private TextView mTvPath;
    private TextView mTvUri;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private ImageView mIvPic;
    private Activity activity;
    private Context context;
    private ImageView imageButton1;
    private Switch switch1;
    private LinearLayout switchLiner1;
    private LinearLayout switchLiner2;
    private LinearLayout switchLiner3;
    private LinearLayout switchLiner4;
    private Map  imageMap;
    private Bundle bundle;
    private DatabaseHelper databaseHelper;


    final int SELECT_PHOTO = 1;
     List<String> imagelist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order3, null, false);
        activity = getActivity();
        context = getActivity().getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
        shinengWebview = view.findViewById(R.id.shinengWebview);
        shiwaiWebview = view.findViewById(R.id.shiwaiWebview);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo( context , telephonyManager, getActivity());
        shiwaiWebview = view.findViewById(R.id.shiwaiWebview);
        bundle = savedInstanceState;


        Button shinengBtn = view.findViewById(R.id.shineng);
        Button shiwaiBtn = view.findViewById(R.id.shiwai);
        shinengBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shiwaiWebview.setVisibility(View.GONE);
                shinengWebview.setVisibility(View.VISIBLE);
                shinengWebviewInit(shinengWebview);
            }
        });

        shiwaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiwaiWebview.setVisibility(View.VISIBLE);
                shinengWebview.setVisibility(View.GONE);
                shiwaiWebviewInit(shiwaiWebview);
            }
        });
        shinengWebviewInit(shinengWebview);
        return view;
    }

    private void shinengWebviewInit(WebView shinengWebview){
        shinengWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = shinengWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        shinengWebview.addJavascriptInterface(new MyJavascriptInterface(activity), "Android");

        initLQRPhotoSelectUtils();

        StringBuilder jsontmp = new StringBuilder();
        List<Note> notes = databaseHelper.getAllNotes();
        boolean first = true;
        for( Note  note : notes  ){
            if(first){
                jsontmp.append("\"").append(note.getNote()).append("\"");
                first=false;
            }else {
                jsontmp.append(",");
                jsontmp.append("\"").append(note.getNote()).append("\"");
            }

        }

        final String json = "["+jsontmp.toString()+"]";
        Log.e("传递给webview的note",json);
        shinengWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return json; // 把本地数据弄成json串，传给html
            }

        }, "MyBrowserAPI");//MyBrowserAPI:自定义的js函数名
        shinengWebview.loadUrl("file:///android_asset/shineng.html");
    }

    private void shiwaiWebviewInit(WebView shiwaiWebview){
        shiwaiWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = shiwaiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        shiwaiWebview.addJavascriptInterface(new MyJavascriptInterface(activity), "Android");

        shiwaiWebview.loadUrl("file:///android_asset/shiwai.html");
    }

    class MyJavascriptInterface
    {
        Context myContext;
        /** Instantiate the interface and set the context */
        MyJavascriptInterface(Context c)
        {
            myContext = c;
        }
        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast)
        {
            Toast.makeText(myContext, toast, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void choosePhoto()
        {
            PermissionGen.with(OrderFragment.this)
                    .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                    .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    ).request();
            // TODO Auto-generated method stub
//            String file = "test";
//            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//            photoPickerIntent.setType("image/*");
//            startActivityForResult(photoPickerIntent, SELECT_PHOTO);
//            return file;
        }
    }

    private void initLQRPhotoSelectUtils() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(activity, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                Log.e("initLQRPhotoSelectUtils",outputFile.getAbsolutePath());
                databaseHelper.insertNote(outputUri.toString());

                // 4、当拍照或从图库选取图片成功后回调
//                mTvPath.setText(outputFile.getAbsolutePath());
//                mTvUri.setText(outputUri.toString());
//                imageMap.put("url",outputUri.toString());
//                Glide.with(OrderFragment.this).load(outputUri).into(mIvPic);
            }
        }, false);//true裁剪，false不裁剪
        //        mLqrPhotoSelectUtils.setAuthorities("com.lqr.lqrnativepicselect.fileprovider");
        //        mLqrPhotoSelectUtils.setImgPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg");
    }

    private void initListener() {
        mBtnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3、调用拍照方法
                PermissionGen.with(OrderFragment.this)
                        .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();

            }
        });

        mBtnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3、调用从图库选取图片方法
                PermissionGen.needPermission(OrderFragment.this,
                        LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}
                );
            }
        });
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto(OrderFragment.this);
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto(OrderFragment.this);
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);

    }

    public void showDialog() {
        //创建对话框创建器
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-虎嗅-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //使用构建器创建出对话框对象
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }

    private void psottoweb() {
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
            Map<String, Double> map = getCellInfo.myGps();

            if (strGetCellInfo != null) {
                String str = "无法获取基站信息";
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
                PostExample example = new PostExample();
                EditText order_Building_location = view.findViewById(R.id.order_Building_location);
                EditText order_Building_layers = view.findViewById(R.id.order_Building_layers);
                EditText order_Current_layer = view.findViewById(R.id.order_Current_layer);

                EditText shi = view.findViewById(R.id.shi);
                EditText quxian = view.findViewById(R.id.quxian);
                EditText wanggeid = view.findViewById(R.id.wanggeid);
                EditText wangedanyuanmingchen = view.findViewById(R.id.wangedanyuanmingchen);
                EditText wanggedanyuanxiaolei = view.findViewById(R.id.wanggedanyuanxiaolei);
                EditText wangluoredianleixing = view.findViewById(R.id.wangluoredianleixing);
                EditText wanggesangaoleixing = view.findViewById(R.id.wanggesangaoleixing);
                EditText wanggezhuhushu = view.findViewById(R.id.wanggezhuhushu);
                EditText wanggedianxinyonghushu = view.findViewById(R.id.wanggedianxinyonghushu);
                EditText wanggeruzhubili = view.findViewById(R.id.wanggeruzhubili);
                EditText louyushifenfugaibili = view.findViewById(R.id.louyushifenfugaibili);
                EditText mrshineifugaizhiliang = view.findViewById(R.id.mrshineifugaizhiliang);
                EditText lteshineifugaizhiliang = view.findViewById(R.id.lteshineifugaizhiliang);
                EditText xinxilaiyuan = view.findViewById(R.id.xinxilaiyuan);
                EditText wanggejianzuwuleixing = view.findViewById(R.id.wanggejianzuwuleixing);
                EditText shifouyiyoushifenguihua = view.findViewById(R.id.shifouyiyoushifenguihua);
                EditText shifouyouyiwangshifen = view.findViewById(R.id.shifouyouyiwangshifen);
                EditText shifouyoudianti = view.findViewById(R.id.shifouyoudianti);
                EditText shifouyoudixiatingchechang = view.findViewById(R.id.shifouyoudixiatingchechang);
                EditText gengxinriqi = view.findViewById(R.id.gengxinriqi);
                EditText beizhu = view.findViewById(R.id.beizhu);


                String json = "{"
                        + "\"order_Building_location\":\"" + order_Building_location.getText() + "\","
                        + "\"order_Building_layers\":\"" + order_Building_layers.getText() + "\","
                        + "\"order_Current_layer\":\"" + order_Current_layer.getText() + "\","
                        + "\"shi\":\"" + shi.getText() + "\","
                        + "\"quxian\":\"" + quxian.getText() + "\","
                        + "\"wanggeid\":\"" + wanggeid.getText() + "\","
                        + "\"wangedanyuanmingchen\":\"" + wangedanyuanmingchen.getText() + "\","
                        + "\"wanggedanyuanxiaolei\":\"" + wanggedanyuanxiaolei.getText() + "\","
                        + "\"wangluoredianleixing\":\"" + wangluoredianleixing.getText() + "\","
                        + "\"wanggesangaoleixing\":\"" + wanggesangaoleixing.getText() + "\","
                        + "\"wanggezhuhushu\":\"" + wanggezhuhushu.getText() + "\","
                        + "\"wanggedianxinyonghushu\":\"" + wanggedianxinyonghushu.getText() + "\","
                        + "\"wanggeruzhubili\":\"" + wanggeruzhubili.getText() + "\","
                        + "\"louyushifenfugaibili\":\"" + louyushifenfugaibili.getText() + "\","
                        + "\"mrshineifugaizhiliang\":\"" + mrshineifugaizhiliang.getText() + "\","
                        + "\"lteshineifugaizhiliang\":\"" + lteshineifugaizhiliang.getText() + "\","
                        + "\"xinxilaiyuan\":\"" + xinxilaiyuan.getText() + "\","
                        + "\"wanggejianzuwuleixing\":\"" + wanggejianzuwuleixing.getText() + "\","
                        + "\"shifouyiyoushifenguihua\":\"" + shifouyiyoushifenguihua.getText() + "\","
                        + "\"shifouyouyiwangshifen\":\"" + shifouyouyiwangshifen.getText() + "\","
                        + "\"shifouyoudianti\":\"" + shifouyoudianti.getText() + "\","
                        + "\"shifouyoudixiatingchechang\":\"" + shifouyoudixiatingchechang.getText() + "\","
                        + "\"gengxinriqi\":\"" + gengxinriqi.getText() + "\","
                        + "\"beizhu\":\"" + beizhu.getText() + "\","
                        + "\"mCellIdentityCdma_mBasestationId\":\"" + mCellIdentityCdma_mBasestationId + "\","
                        + "\"mCellIdentityCdma_mLatitude\":\"" + mCellIdentityCdma_mLatitude + "\","
                        + "\"mCellIdentityCdma_mLongitude\":\"" + mCellIdentityCdma_mLongitude + "\","
                        + "\"mCellIdentityCdma_mNetworkId\":\"" + mCellIdentityCdma_mNetworkId + "\","
                        + "\"mCellIdentityCdma_mSystemId\":\"" + mCellIdentityCdma_mSystemId + "\","
                        + "\"mCellSignalStrengthCdma_mCdmaDbm\":\"" + mCellSignalStrengthCdma_mCdmaDbm + "\","
                        + "\"mCellSignalStrengthCdma_mCdmaEcio\":\"" + mCellSignalStrengthCdma_mCdmaEcio + "\","
                        + "\"mCellSignalStrengthCdma_mEvdoDbm\":\"" + mCellSignalStrengthCdma_mEvdoDbm + "\","
                        + "\"mCellSignalStrengthCdma_mEvdoEcio\":\"" + mCellSignalStrengthCdma_mEvdoEcio + "\","
                        + "\"mCellSignalStrengthCdma_mEvdoSnr\":\"" + mCellSignalStrengthCdma_mEvdoSnr + "\","
                        + "\"mCellIdentityCdma_mRegistered\":\"" + mCellIdentityCdma_mRegistered + "\","
                        + "\"mCellIdentityCdma_mTimeStamp\":\"" + mCellIdentityCdma_mTimeStamp + "\","
                        + "\"mCellIdentityCdma_mTimeStampType\":\"" + mCellIdentityCdma_mTimeStampType + "\","
                        + "\"mCellIdentityLte_mCi\":\"" + mCellIdentityLte_mCi + "\","
                        + "\"mCellIdentityLte_mEarfcn\":\"" + mCellIdentityLte_mEarfcn + "\","
                        + "\"mCellIdentityLte_mMcc\":\"" + mCellIdentityLte_mMcc + "\","
                        + "\"mCellIdentityLte_mMnc\":\"" + mCellIdentityLte_mMnc + "\","
                        + "\"mCellIdentityLte_mPci\":\"" + mCellIdentityLte_mPci + "\","
                        + "\"mCellIdentityLte_mTac\":\"" + mCellIdentityLte_mTac + "\","
                        + "\"mCellSignalStrengthLte_mCqi\":\"" + mCellSignalStrengthLte_mCqi + "\","
                        + "\"mCellSignalStrengthLte_mRsrp\":\"" + mCellSignalStrengthLte_mRsrp + "\","
                        + "\"mCellSignalStrengthLte_mRsrq\":\"" + mCellSignalStrengthLte_mRsrq + "\","
                        + "\"mCellSignalStrengthLte_mRssnr\":\"" + mCellSignalStrengthLte_mRssnr + "\","
                        + "\"mCellSignalStrengthLte_mSignalStrength\":\"" + mCellSignalStrengthLte_mSignalStrength + "\","
                        + "\"mCellSignalStrengthLte_mTimingAdvance\":\"" + mCellSignalStrengthLte_mTimingAdvance + "\","
                        + "\"mCellIdentityLte_mRegistered\":\"" + mCellIdentityLte_mRegistered + "\","
                        + "\"mCellIdentityLte_mTimeStamp\":\"" + mCellIdentityLte_mTimeStamp + "\","
                        + "\"mCellIdentityLte_mTimeStampType\":\"" + mCellIdentityLte_mTimeStampType + "\""
                        + "}";
                String response = null;
                try {
                    response = example.post(json);
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        TextView order_upload_data = view.findViewById(R.id.order_upload_data);
        order_upload_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        psottoweb();
//                        Toast.makeText(getActivity().getApplicationContext(), "上传成功！！！！"+response, Toast.LENGTH_LONG).show();
                    }
                };
                new Thread(runnable).start();
                Log.e("imageUrlMap",imageMap.toString());
                Toast.makeText(context, "上传成功！！！！", Toast.LENGTH_LONG).show();
            }
        });

        Button btn_shineng = view.findViewById(R.id.shineng);
        Button btn_shiwai = view.findViewById(R.id.shiwai);
        btn_shineng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLiner1.setVisibility(View.VISIBLE);
                switchLiner2.setVisibility(View.VISIBLE);
                switchLiner3.setVisibility(View.GONE);
                switchLiner4.setVisibility(View.GONE);
            }
        });
        btn_shiwai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLiner1.setVisibility(View.GONE);
                switchLiner2.setVisibility(View.GONE);
                switchLiner3.setVisibility(View.VISIBLE);
                switchLiner4.setVisibility(View.VISIBLE);
            }
        });

    }

}
