package com.ysc.baiduapp.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.ysc.baiduapp.GridViewAdapter;
import com.ysc.baiduapp.ImageAdapter;
import com.ysc.baiduapp.ListViewAdapter;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.viewcustom.BaseFragment;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment {
    public GetCellInfo getCellInfo;
    private View view;
    private EditText et_search;
    private ListView lv_market;
    private ListViewAdapter listViewAdapter;
    private GridView grid;
    private GridViewAdapter gridViewAdapter;
    private ArrayAdapter<View> gridAdapter;
    private String[] MOBILE_OS = new String[]{"buy","deliver","fruit","medicine"};

    private static final int REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private Button btn;
    private TextView textViewBase;
    private TextView textViewCell;

//    @SuppressLint("ValidFragment")
//    public void setGetCellInfo(GetCellInfo g){
//        this.getCellInfo = g;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);

        TelephonyManager telephonyManager = (TelephonyManager)  getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(),telephonyManager,getActivity());

//        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_home,null);
        init();
        return view;
    }

    private void init() {

        btn = (Button) view.findViewById(R.id.button);
        textViewBase = (TextView) view.findViewById(R.id.textView);
        textViewBase.setMovementMethod(new ScrollingMovementMethod());
        textViewCell = (TextView) view.findViewById(R.id.editText);
        textViewCell.setMovementMethod(new ScrollingMovementMethod());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
                    textViewBase.setText(getCellInfo.myBase());
                    textViewCell.setText(getCellInfo.myCell());
//                }catch (Exception e){
////                    new AlertDialog.Builder(Context).setTitle("内部错误") .setMessage(e.toString()).setPositiveButton("确定", null)  .show();
//                    System.out.print("System.out.print(e.toString());------------------------------------"+e.toString());
//                }
            }
        });



//        et_search = (EditText) view.findViewById(R.id.et_search);
//        Drawable drawable = null;
//        drawable = getResources().getDrawable(R.drawable.bai);
//        drawable.setBounds(2, 0, 75, 40);
//        et_search.setCompoundDrawables(drawable, null, null, null);
//
//        lv_market = (ListView) view.findViewById(R.id.lv_market);
//        listViewAdapter = new ListViewAdapter(view.getContext());
//        lv_market.setAdapter(listViewAdapter);
//
//
//        grid = (GridView)view.findViewById(R.id.gridView);
//        gridViewAdapter = new GridViewAdapter(view.getContext());
//        grid.setAdapter(new ImageAdapter(getActivity(),MOBILE_OS));
    }
}
