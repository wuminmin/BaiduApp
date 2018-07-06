package com.ysc.baiduapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.bugly.crashreport.CrashReport;
import com.ysc.baiduapp.fragment.OrderFragment;
import com.ysc.baiduapp.fragment.MineFragment;
import com.ysc.baiduapp.fragment.HomeFragment;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.service.LQRPhotoSelectUtils;
import com.ysc.baiduapp.viewcustom.BaseFragment;
import com.ysc.baiduapp.viewcustom.IconPagerAdapter;
import com.ysc.baiduapp.viewcustom.IconTabPageIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DoublePicker;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


public class MainActivity extends FragmentActivity {
    public GetCellInfo getCellInfo;
    private ViewPager mViewPager;
    private IconTabPageIndicator mIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CrashReport.initCrashReport(getApplicationContext(), "5d728085f5", true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activityfragement_main);
        initViews();
        initData();



        /**
         * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }


    private void initViews(){
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);

//        GridView gridview = (GridView) findViewById(R.id.gridView);
//        gridview.setAdapter(new ImageAdapter(this));

    }

    public void initData(){
        List<BaseFragment> fragments = initFragments();
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();

        HomeFragment homeFragment = new HomeFragment();
//        homeFragment.setGetCellInfo(getCellInfo);
        homeFragment.setTitle("小区信息");
        homeFragment.setIconId(R.drawable.tab_home_selector);
        fragments.add(homeFragment);

        OrderFragment classFragment = new OrderFragment();
        classFragment.setTitle("场景采集");
        classFragment.setIconId(R.drawable.tab_class_selector);
        fragments.add(classFragment);

        MineFragment grabOrderFragment = new MineFragment();
        grabOrderFragment.setTitle("我的");
        grabOrderFragment.setIconId(R.drawable.tab_grab_selector);
//        fragments.add(grabOrderFragment);


        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<BaseFragment> mFragments;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            mFragments = fragments;
        }


        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }
    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK ) {
//            if (begin_time == 0) {
//                begin_time = System.currentTimeMillis();
////
//                ToastUtil.toast(MainActivity.this, this.getResources().getString(R.string.exit_again));
//                return false;
//            } else {
//                end_time = System.currentTimeMillis();
//                long time = end_time - begin_time;
//                if (time < 2000) {
////
//                    ManageActivity.getInstance().exit();
//                } else {
//                    begin_time = end_time;
////
//                    ToastUtil.toast(MainActivity.this, this.getResources().getString(R.string.exit_again));
//                    return false;
//                }
//            }
////
//
//        }
//        return false;
//    }

    @SuppressLint("ServiceCast")
    private void popPrompt(String msg){
        new AlertDialog.Builder(this).setTitle("提示框").setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }})
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void onDoublePicker(View view) {
        final ArrayList<String> firstData = new ArrayList<>();
        firstData.add("-1");
        firstData.add("1");
        firstData.add("2");
        firstData.add("3");
        firstData.add("4");
        firstData.add("5");
        firstData.add("6");
        firstData.add("7");
        firstData.add("8");
        firstData.add("9");
        firstData.add("10");
        firstData.add("11");
        firstData.add("12");
        firstData.add("13");
        firstData.add("14");
        firstData.add("15");
        final ArrayList<String> secondData = new ArrayList<>();
        secondData.add("-1");
        secondData.add("1");
        secondData.add("2");
        secondData.add("3");
        secondData.add("4");
        secondData.add("5");
        secondData.add("6");
        secondData.add("7");
        secondData.add("8");
        secondData.add("9");
        secondData.add("10");
        secondData.add("11");
        secondData.add("12");
        secondData.add("13");
        secondData.add("14");
        secondData.add("15");
        final DoublePicker picker = new DoublePicker(this, firstData, secondData);
        picker.setDividerVisible(true);
        picker.setCycleDisable(false);
        picker.setSelectedIndex(0, 0);
        picker.setFirstLabel("当前层数", null);
        picker.setSecondLabel("总层数", "");
        picker.setTextSize(12);
        picker.setContentPadding(15, 10);
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
//                showToast(firstData.get(selectedFirstIndex) + " " + secondData.get(selectedSecondIndex));
                Toast.makeText(MainActivity.this, firstData.get(selectedFirstIndex) + " " + secondData.get(selectedSecondIndex) , Toast.LENGTH_LONG).show();
            }
        });
        picker.show();
    }


}
