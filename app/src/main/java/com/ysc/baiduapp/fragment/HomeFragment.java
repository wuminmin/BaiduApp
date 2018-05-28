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
import android.widget.Toast;

import com.ysc.baiduapp.GridViewAdapter;
import com.ysc.baiduapp.ImageAdapter;
import com.ysc.baiduapp.ListViewAdapter;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

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
    private TextView textViewBase;

    private String strGetCellInfo;

    private String mCellIdentityCdma_mBasestationId;
    private String mCellIdentityCdma_mLatitude;
    private String mCellIdentityCdma_mLongitude;
    private String mCellIdentityCdma_mNetworkId;
    private String mCellIdentityCdma_mSystemId;
    private String mCellSignalStrengthCdma_mCdmaDbm;
    private String mCellSignalStrengthCdma_mCdmaEcio;
    private String mCellSignalStrengthCdma_mEvdoDbm;
    private String mCellSignalStrengthCdma_mEvdoEcio;
    private String mCellSignalStrengthCdma_mEvdoSnr;
    private String mCellIdentityCdma_mRegistered;
    private String mCellIdentityCdma_mTimeStamp;
    private String mCellIdentityCdma_mTimeStampType;

    private String mCellIdentityLte_mCi;
    private String mCellIdentityLte_mEarfcn;
    private String mCellIdentityLte_mMcc;
    private String mCellIdentityLte_mMnc;
    private String mCellIdentityLte_mPci;
    private String mCellIdentityLte_mTac;

    private String mCellSignalStrengthLte_mCqi;
    private String mCellSignalStrengthLte_mRsrp;
    private String mCellSignalStrengthLte_mRsrq;
    private String mCellSignalStrengthLte_mRssnr;
    private String mCellSignalStrengthLte_mSignalStrength;
    private String mCellSignalStrengthLte_mTimingAdvance;

    private String mCellIdentityLte_mRegistered;
    private String mCellIdentityLte_mTimeStamp;
    private String mCellIdentityLte_mTimeStampType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);

        TelephonyManager telephonyManager = (TelephonyManager)  getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(),telephonyManager,getActivity());

//        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_home,null);
        init();
        return view;
    }
    private TextView textView;
    private Button button;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;

    private TextView textView13;
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;
    private TextView textView17;
    private TextView textView18;
    private TextView textView19;
    private TextView textView20;
    private TextView textView21;
    private TextView textView22;
    private TextView textView23;
    private TextView textView24;

    private void init() {
        button = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);
        textView5 = view.findViewById(R.id.textView5);
        textView6 = view.findViewById(R.id.textView6);
        textView7 = view.findViewById(R.id.textView7);
        textView8 = view.findViewById(R.id.textView8);
        textView9 = view.findViewById(R.id.textView9);
        textView10 = view.findViewById(R.id.textView10);
        textView11 = view.findViewById(R.id.textView11);
        textView12 = view.findViewById(R.id.textView12);
        textView13 = view.findViewById(R.id.textView13);
        textView14 = view.findViewById(R.id.textView14);
        textView15 = view.findViewById(R.id.textView15);
        textView16 = view.findViewById(R.id.textView16);
        textView17 = view.findViewById(R.id.textView17);
        textView18 = view.findViewById(R.id.textView18);
        textView19 = view.findViewById(R.id.textView19);
        textView20 = view.findViewById(R.id.textView20);
        textView21 = view.findViewById(R.id.textView21);
        textView22 = view.findViewById(R.id.textView22);
        textView23 = view.findViewById(R.id.textView23);
        textView24 = view.findViewById(R.id.textView24);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    textViewBase.setText(getCellInfo.myBase());
//                    textViewCell.setText(getCellInfo.myCell());
                    strGetCellInfo = getCellInfo.myCell();
                    if(strGetCellInfo != null){
                        JSONArray jsonarray = new JSONArray(strGetCellInfo);
                        JSONObject jsonobject0 = jsonarray.getJSONObject(0);
                        mCellIdentityCdma_mBasestationId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mBasestationId");
                        mCellIdentityCdma_mLatitude = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mLatitude");
                        mCellIdentityCdma_mLongitude = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mLongitude");
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

                        JSONObject jsonobject1 = jsonarray.getJSONObject(1);
                        mCellIdentityLte_mCi = jsonobject1.getJSONObject("mCellIdentityLte").getString("mCi");
                        mCellIdentityLte_mEarfcn = jsonobject1.getJSONObject("mCellIdentityLte").getString("mEarfcn");
                        mCellIdentityLte_mMcc = jsonobject1.getJSONObject("mCellIdentityLte").getString("mMcc");
                        mCellIdentityLte_mMnc = jsonobject1.getJSONObject("mCellIdentityLte").getString("mMnc");
                        mCellIdentityLte_mPci = jsonobject1.getJSONObject("mCellIdentityLte").getString("mPci");
                        mCellIdentityLte_mTac = jsonobject1.getJSONObject("mCellIdentityLte").getString("mTac");
                        mCellSignalStrengthLte_mCqi = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mCqi");
                        mCellSignalStrengthLte_mRsrp = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRsrp");
                        mCellSignalStrengthLte_mRsrq = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRsrq");
                        mCellSignalStrengthLte_mRssnr = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mRssnr");
                        mCellSignalStrengthLte_mSignalStrength = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mSignalStrength");
                        mCellSignalStrengthLte_mTimingAdvance = jsonobject1.getJSONObject("mCellSignalStrengthLte").getString("mTimingAdvance");
                        mCellIdentityLte_mRegistered = jsonobject1.getString("mRegistered");
                        mCellIdentityLte_mTimeStamp = jsonobject1.getString("mTimeStamp");
                        mCellIdentityLte_mTimeStampType = jsonobject1.getString("mTimeStampType");

                        textView.setText("mCellIdentityCdma_mLatitude:"+mCellIdentityCdma_mBasestationId);
                        textView2.setText("mCellIdentityCdma_mLatitude:"+mCellIdentityCdma_mLatitude);
                        textView3.setText("mCellIdentityCdma_mLongitude:"+mCellIdentityCdma_mLongitude);
                        textView4.setText("mCellIdentityCdma_mNetworkId:"+mCellIdentityCdma_mNetworkId);
                        textView5.setText("mCellIdentityCdma_mSystemId:"+mCellIdentityCdma_mSystemId);
                        textView6.setText("mCellSignalStrengthCdma_mCdmaDbm:"+mCellSignalStrengthCdma_mCdmaDbm);
                        textView7.setText("mCellSignalStrengthCdma_mCdmaEcio:"+mCellSignalStrengthCdma_mCdmaEcio);
                        textView8.setText("mCellSignalStrengthCdma_mEvdoDbm:"+mCellSignalStrengthCdma_mEvdoDbm);
                        textView9.setText("mCellSignalStrengthCdma_mEvdoEcio:"+mCellSignalStrengthCdma_mEvdoEcio);
                        textView10.setText("mCellSignalStrengthCdma_mEvdoSnr:"+mCellSignalStrengthCdma_mEvdoSnr);
                        textView11.setText("mCellIdentityCdma_mRegistered:"+mCellIdentityCdma_mRegistered);
                        textView12.setText("mCellIdentityCdma_mTimeStamp:"+mCellIdentityCdma_mTimeStamp);
                        textView13.setText("mCellIdentityCdma_mTimeStampType:"+mCellIdentityCdma_mTimeStampType);

                        textView14.setText("mCellIdentityLte_mCi:"+mCellIdentityLte_mCi);
                        textView15.setText("mCellIdentityLte_mEarfcn:"+mCellIdentityLte_mEarfcn);
                        textView16.setText("mCellIdentityLte_mMcc:"+mCellIdentityLte_mMcc);
                        textView17.setText("mCellIdentityLte_mMnc:"+mCellIdentityLte_mMnc);
                        textView18.setText("mCellIdentityLte_mPci:"+mCellIdentityLte_mPci);
                        textView19.setText("mCellIdentityLte_mTac:"+mCellIdentityLte_mTac);
                        textView20.setText("mCellSignalStrengthLte_mCqi:"+mCellSignalStrengthLte_mCqi);
                        textView21.setText("mCellSignalStrengthLte_mRsrp:"+mCellSignalStrengthLte_mRsrp);
                        textView22.setText("mCellSignalStrengthLte_mRssnr:"+mCellSignalStrengthLte_mRssnr);
                        textView23.setText("mCellSignalStrengthLte_mSignalStrength:"+mCellSignalStrengthLte_mSignalStrength);
                        textView24.setText("mCellSignalStrengthLte_mTimingAdvance:"+mCellSignalStrengthLte_mTimingAdvance);

                    }




                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
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
