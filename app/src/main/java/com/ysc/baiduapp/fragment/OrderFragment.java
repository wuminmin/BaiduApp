package com.ysc.baiduapp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsStatus;
import android.net.Uri;
import android.net.sip.SipSession;
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
import com.ysc.baiduapp.service.XinxiJson;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private Activity activity;
    private Context context;
    private Bundle bundle;
    private DatabaseHelper databaseHelper;
    private XinxiJson xinxiJson;
    private Button shinengBtn;
    private Button shiwaiBtn;
    private int BUTTONSTAT;

    final int SELECT_PHOTO = 1;
     List<String> imagelist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order3, null, false);
        activity = getActivity();
        context = getActivity().getApplicationContext();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        xinxiJson = new XinxiJson(  getActivity().getApplicationContext(), telephonyManager, getActivity()  );
        BUTTONSTAT = 1 ;
        databaseHelper = new DatabaseHelper(context);
        shinengWebview = view.findViewById(R.id.shinengWebview);
        shiwaiWebview = view.findViewById(R.id.shiwaiWebview);
        getCellInfo = new GetCellInfo( context , telephonyManager, getActivity());
        shiwaiWebview = view.findViewById(R.id.shiwaiWebview);
        bundle = savedInstanceState;

        shinengBtn = view.findViewById(R.id.shineng);
        shiwaiBtn = view.findViewById(R.id.shiwai);
        shinengBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                shinengBtn.setBackgroundColor(R.color.white);
                shiwaiBtn.setBackgroundColor(R.color.black);
                shiwaiWebview.setVisibility(View.GONE);
                shinengWebview.setVisibility(View.VISIBLE);
                BUTTONSTAT = 1;
                shinengWebviewInit(shinengWebview);
            }
        });
        shiwaiBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                shinengBtn.setBackgroundColor(R.color.black);
                shiwaiBtn.setBackgroundColor(R.color.white);
                shiwaiWebview.setVisibility(View.VISIBLE);
                shinengWebview.setVisibility(View.GONE);
                BUTTONSTAT = 2;
                shiwaiWebviewInit(shiwaiWebview);
            }
        });
        shinengInit(shinengWebview ,shinengBtn ,shiwaiBtn);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void shinengInit(WebView shinengWebview , Button shinengBtn , Button shiwaiBtn ){
        shinengBtn.setBackgroundColor(R.color.white);
        shiwaiBtn.setBackgroundColor(R.color.black);
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

        final String jsonXinxi = xinxiJson.getXinxiJsonOne();
        shinengWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return jsonXinxi; // 把本地数据弄成json串，传给html
            }
        }, "getXinxiJsonOne");//MyBrowserAPI:自定义的js函数名

        shinengWebview.loadUrl("file:///android_asset/shineng.html");
    }

     private void shinengWebviewInit(WebView shinengWebview ){
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

        final String jsonXinxi = xinxiJson.getXinxiJsonOne();
        shinengWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return jsonXinxi; // 把本地数据弄成json串，传给html
            }
        }, "getXinxiJsonOne");//MyBrowserAPI:自定义的js函数名

        shinengWebview.loadUrl("file:///android_asset/shineng.html");
    }

    private void shiwaiWebviewInit(WebView shiwaiWebview){
        shiwaiWebview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = shiwaiWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        shiwaiWebview.addJavascriptInterface(new MyJavascriptInterface(activity), "Android");
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
        shiwaiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return json; // 把本地数据弄成json串，传给html
            }
        }, "MyBrowserAPI");//MyBrowserAPI:自定义的js函数名


        final String jsonXinxi = xinxiJson.getXinxiJsonOne();
        shiwaiWebview.addJavascriptInterface(new Object() {
            //@param message:  html页面传进来的数据
            @JavascriptInterface
            public String getLocationData(String message) {
                return jsonXinxi; // 把本地数据弄成json串，传给html
            }
        }, "getXinxiJsonOne");//MyBrowserAPI:自定义的js函数名

        shiwaiWebview.loadUrl("file:///android_asset/shiwai.html");
    }

    class MyJavascriptInterface {
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
        @JavascriptInterface
        public void selectPhoto()
        {
            // 3、调用从图库选取图片方法
            PermissionGen.needPermission(OrderFragment.this,
                    LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}
            );
            // TODO Auto-generated method stub
//            String file = "test";
//            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//            photoPickerIntent.setType("image/*");
//            startActivityForResult(photoPickerIntent, SELECT_PHOTO);
//            return file;
        }

        @JavascriptInterface
        public void deletePhoto(){
            databaseHelper.deleteallnote();
//            resetcaiji();
            Log.e("删除所有照片","");
        }

        @JavascriptInterface
        public void saveDizhi(String dizhi){
            Log.e("测试web android 传递参数",dizhi);

        }
    }

    @SuppressLint("ResourceAsColor")
    public void resetcaiji(){

                if (BUTTONSTAT == 1){
                    shinengBtn.setBackgroundColor(R.color.white);
                    shiwaiBtn.setBackgroundColor(R.color.black);
                    shiwaiWebview.setVisibility(View.GONE);
                    shinengWebview.setVisibility(View.VISIBLE);
                    BUTTONSTAT = 1;
                    shinengWebviewInit(shinengWebview);
                }else {
                    shinengBtn.setBackgroundColor(R.color.black);
                    shiwaiBtn.setBackgroundColor(R.color.white);
                    shiwaiWebview.setVisibility(View.VISIBLE);
                    shinengWebview.setVisibility(View.GONE);
                    BUTTONSTAT = 2;
                    shiwaiWebviewInit(shiwaiWebview);
                }


    }

    private void initLQRPhotoSelectUtils() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(activity, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                Log.e("initLQRPhotoSelectUtils",outputFile.getAbsolutePath());
                databaseHelper.insertNote(outputUri.toString());
                if (BUTTONSTAT == 1){
                    shinengWebviewInit(shinengWebview);
                }else {
                    shiwaiWebviewInit(shiwaiWebview);
                }
                // 4、当拍照或从图库选取图片成功后回调
//                mTvPath.setText(outputFile.getAbsolutePath());
//                mTvUri.setText(outputUri.toString());
//                imageMap.put("url",outputUri.toString());
//                Glide.with(OrderFragment.this).load(outputUri).into(mIvPic);
            }
        }, false);
        //true裁剪，false不裁剪
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

    }

}
