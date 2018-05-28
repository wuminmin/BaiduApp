package com.ysc.baiduapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
import com.ysc.baiduapp.ListViewAdapter;
import com.ysc.baiduapp.R;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment {
    private GetCellInfo getCellInfo;
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);

        TelephonyManager telephonyManager = (TelephonyManager)  getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(),telephonyManager,getActivity());

//        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_home,null);
        init();
        return view;
    }
    private TextView textViewm_CellIdentityCdma_mBasestationId;
    private Button button;
    private TextView textViewm_CellIdentityCdma_mLatitude;
    private TextView textView_mCellIdentityCdma_mLongitude;
    private TextView textView_mCellIdentityCdma_mNetworkId;
    private TextView textView_mCellSignalStrengthCdma_mCdmaDbm;
    private TextView textView_mCellSignalStrengthCdma_mCdmaEcio;
    private TextView textView_mCellSignalStrengthCdma_mEvdoDbm;
    private TextView textView_mCellSignalStrengthCdma_mEvdoEcio;
    private TextView textView_mCellSignalStrengthCdma_mEvdoSnr;
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
        textViewm_CellIdentityCdma_mBasestationId = view.findViewById(R.id.mCellIdentityCdma_mBasestationId);
        textViewm_CellIdentityCdma_mLatitude = view.findViewById(R.id.mCellIdentityCdma_mLatitude);
        textView_mCellIdentityCdma_mLongitude = view.findViewById(R.id.mCellIdentityCdma_mLongitude);
        textView_mCellIdentityCdma_mNetworkId = view.findViewById(R.id.mCellIdentityCdma_mNetworkId);
        textView_mCellSignalStrengthCdma_mCdmaDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaDbm);
        textView_mCellSignalStrengthCdma_mCdmaEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaEcio);
        textView_mCellSignalStrengthCdma_mEvdoDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoDbm);
        textView_mCellSignalStrengthCdma_mEvdoEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoEcio);
        textView_mCellSignalStrengthCdma_mEvdoSnr = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoSnr);
        textView10 = view.findViewById(R.id.mCellIdentityCdma_mRegistered);
        textView11 = view.findViewById(R.id.mCellIdentityCdma_mTimeStamp);
        textView12 = view.findViewById(R.id.mCellIdentityCdma_mTimeStampType);
        textView13 = view.findViewById(R.id.mCellIdentityLte_mCi);
        textView14 = view.findViewById(R.id.mCellIdentityLte_mEarfcn);
        textView15 = view.findViewById(R.id.mCellIdentityLte_mMcc);
        textView16 = view.findViewById(R.id.mCellIdentityLte_mMnc);
        textView17 = view.findViewById(R.id.mCellIdentityLte_mPci);
        textView18 = view.findViewById(R.id.mCellIdentityLte_mTac);
        textView19 = view.findViewById(R.id.mCellSignalStrengthLte_mCqi);
        textView20 = view.findViewById(R.id.mCellSignalStrengthLte_mRsrp);
        textView21 = view.findViewById(R.id.mCellSignalStrengthLte_mRssnr);
        textView22 = view.findViewById(R.id.mCellSignalStrengthLte_mSignalStrength);
        textView23 = view.findViewById(R.id.mCellSignalStrengthLte_mTimingAdvance);
//        textView24 = view.findViewById(R.id.textView24);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                        textViewm_CellIdentityCdma_mBasestationId.setText(mCellIdentityCdma_mBasestationId);
                        textViewm_CellIdentityCdma_mLatitude.setText(mCellIdentityCdma_mLatitude);
                        textView_mCellIdentityCdma_mLongitude.setText(mCellIdentityCdma_mLongitude);
                        textView_mCellIdentityCdma_mNetworkId.setText(mCellIdentityCdma_mNetworkId);
                        textView_mCellSignalStrengthCdma_mCdmaDbm.setText(mCellIdentityCdma_mSystemId);
                        textView_mCellSignalStrengthCdma_mCdmaEcio.setText(mCellSignalStrengthCdma_mCdmaDbm);
                        textView_mCellSignalStrengthCdma_mEvdoDbm.setText(mCellSignalStrengthCdma_mCdmaEcio);
                        textView_mCellSignalStrengthCdma_mEvdoEcio.setText(mCellSignalStrengthCdma_mEvdoDbm);
                        textView_mCellSignalStrengthCdma_mEvdoSnr.setText(mCellSignalStrengthCdma_mEvdoEcio);
                        textView10.setText(mCellSignalStrengthCdma_mEvdoSnr);
                        textView11.setText(mCellIdentityCdma_mRegistered);
                        textView12.setText(mCellIdentityCdma_mTimeStamp);
                        textView13.setText(mCellIdentityCdma_mTimeStampType);

                        textView14.setText(mCellIdentityLte_mCi);
                        textView15.setText(mCellIdentityLte_mEarfcn);
                        textView16.setText(mCellIdentityLte_mMcc);
                        textView17.setText(mCellIdentityLte_mMnc);
                        textView18.setText(mCellIdentityLte_mPci);
                        textView19.setText(mCellIdentityLte_mTac);
                        textView20.setText(mCellSignalStrengthLte_mCqi);
                        textView21.setText(mCellSignalStrengthLte_mRsrp);
                        textView22.setText(mCellSignalStrengthLte_mRssnr);
                        textView23.setText(mCellSignalStrengthLte_mSignalStrength);
//                        textView24.setText(mCellSignalStrengthLte_mTimingAdvance);
                    }else{
                        textViewm_CellIdentityCdma_mBasestationId.setText("无法识别小区信息，可能需要您给APP授权！！");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
