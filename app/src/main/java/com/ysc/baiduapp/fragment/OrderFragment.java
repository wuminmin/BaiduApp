package com.ysc.baiduapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysc.baiduapp.R;
import com.ysc.baiduapp.viewcustom.BaseFragment;

/**
 * Created by wjx on 2016-1-12.
 */
public class OrderFragment extends BaseFragment {
    private ImageView iv_order;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, null, false);
        init();
        return view;
    }

    private void init() {
    }
}
