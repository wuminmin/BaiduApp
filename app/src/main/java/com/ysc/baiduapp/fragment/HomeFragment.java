package com.ysc.baiduapp.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.ysc.baiduapp.GridViewAdapter;
import com.ysc.baiduapp.ImageAdapter;
import com.ysc.baiduapp.ListViewAdapter;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.viewcustom.BaseFragment;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment {
    private View view;
    private EditText et_search;
    private ListView lv_market;
    private ListViewAdapter listViewAdapter;
    private GridView grid;
    private GridViewAdapter gridViewAdapter;
    private ArrayAdapter<View> gridAdapter;
    private String[] MOBILE_OS = new String[]{"buy","deliver","fruit","medicine"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_home,null);
        init();
        return view;
    }

    private void init() {
        et_search = (EditText) view.findViewById(R.id.et_search);
        Drawable drawable = null;
        drawable = getResources().getDrawable(R.drawable.bai);
        drawable.setBounds(2, 0, 75, 40);
        et_search.setCompoundDrawables(drawable, null, null, null);

        lv_market = (ListView) view.findViewById(R.id.lv_market);
        listViewAdapter = new ListViewAdapter(view.getContext());
        lv_market.setAdapter(listViewAdapter);


        grid = (GridView)view.findViewById(R.id.gridView);
        gridViewAdapter = new GridViewAdapter(view.getContext());
        grid.setAdapter(new ImageAdapter(getActivity(),MOBILE_OS));
    }
}
