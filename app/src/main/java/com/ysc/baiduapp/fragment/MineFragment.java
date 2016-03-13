package com.ysc.baiduapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.viewcustom.BaseFragment;

/**
 * Created by wjx on 2016-1-12.
 */
public class MineFragment extends BaseFragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null, false);
        return view;
    }
}
