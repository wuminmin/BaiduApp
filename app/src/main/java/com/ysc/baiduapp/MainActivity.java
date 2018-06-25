package com.ysc.baiduapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.Window;
import android.widget.GridView;

import com.tencent.bugly.crashreport.CrashReport;
import com.ysc.baiduapp.fragment.OrderFragment;
import com.ysc.baiduapp.fragment.MineFragment;
import com.ysc.baiduapp.fragment.HomeFragment;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.viewcustom.BaseFragment;
import com.ysc.baiduapp.viewcustom.IconPagerAdapter;
import com.ysc.baiduapp.viewcustom.IconTabPageIndicator;

import java.util.ArrayList;
import java.util.List;

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
}
