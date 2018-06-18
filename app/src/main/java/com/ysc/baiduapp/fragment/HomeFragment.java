package com.ysc.baiduapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.Map;

/**
 * Created by wjx on 2016-1-12.
 */
public class HomeFragment extends BaseFragment {
    private GetCellInfo getCellInfo;
    private View view;
    private static final int REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
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
    private TextView textView_mCellIdentityCdma_mRegistered;
    private TextView textView_mCellIdentityCdma_mTimeStamp;
    private TextView textView_mCellIdentityCdma_mTimeStampType;
    private TextView textView_mCellIdentityLte_mCi;
    private TextView textView_mCellIdentityLte_mEarfcn;
    private TextView textView_mCellIdentityLte_mMcc;
    private TextView textView_mCellIdentityLte_mMnc;
    private TextView textView_mCellIdentityLte_mPci;
    private TextView textView_mCellIdentityLte_mTac;
    private TextView textView_mCellSignalStrengthLte_mCqi;
    private TextView textView_mCellSignalStrengthLte_mRsrp;
    private TextView textView_mCellSignalStrengthLte_mRsrq;
    private TextView textView_mCellSignalStrengthLte_mRssnr;
    private TextView textView_mCellSignalStrengthLte_mSignalStrength;
    private TextView textView_mCellSignalStrengthLte_mTimingAdvance;
    private TextView textView_mCellIdentityCdma_mSystemId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        TelephonyManager telephonyManager = (TelephonyManager)  getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(),telephonyManager,getActivity());
        init();
        return view;
    }
    private void init() {
        button = view.findViewById(R.id.button);
        textViewm_CellIdentityCdma_mBasestationId = view.findViewById(R.id.mCellIdentityCdma_mBasestationId);
        textViewm_CellIdentityCdma_mLatitude = view.findViewById(R.id.mCellIdentityCdma_mLatitude);
        textView_mCellIdentityCdma_mLongitude = view.findViewById(R.id.mCellIdentityCdma_mLongitude);
        textView_mCellIdentityCdma_mNetworkId = view.findViewById(R.id.mCellIdentityCdma_mNetworkId);
        textView_mCellIdentityCdma_mSystemId = view.findViewById(R.id.mCellIdentityCdma_mSystemId);
        textView_mCellSignalStrengthCdma_mCdmaDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaDbm);
        textView_mCellSignalStrengthCdma_mCdmaEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaEcio);
        textView_mCellSignalStrengthCdma_mEvdoDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoDbm);
        textView_mCellSignalStrengthCdma_mEvdoEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoEcio);
        textView_mCellSignalStrengthCdma_mEvdoSnr = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoSnr);
//        textView_mCellIdentityCdma_mRegistered = view.findViewById(R.id.mCellIdentityCdma_mRegistered);
//        textView_mCellIdentityCdma_mTimeStamp = view.findViewById(R.id.mCellIdentityCdma_mTimeStamp);
//        textView_mCellIdentityCdma_mTimeStampType = view.findViewById(R.id.mCellIdentityCdma_mTimeStampType);
        textView_mCellIdentityLte_mCi = view.findViewById(R.id.mCellIdentityLte_mCi);
        textView_mCellIdentityLte_mEarfcn = view.findViewById(R.id.mCellIdentityLte_mEarfcn);
        textView_mCellIdentityLte_mMcc = view.findViewById(R.id.mCellIdentityLte_mMcc);
        textView_mCellIdentityLte_mMnc = view.findViewById(R.id.mCellIdentityLte_mMnc);
        textView_mCellIdentityLte_mPci = view.findViewById(R.id.mCellIdentityLte_mPci);
        textView_mCellIdentityLte_mTac = view.findViewById(R.id.mCellIdentityLte_mTac);
        textView_mCellSignalStrengthLte_mCqi = view.findViewById(R.id.mCellSignalStrengthLte_mCqi);
        textView_mCellSignalStrengthLte_mRsrp = view.findViewById(R.id.mCellSignalStrengthLte_mRsrp);
        textView_mCellSignalStrengthLte_mRsrq = view.findViewById(R.id.mCellSignalStrengthLte_mRsrq);
        textView_mCellSignalStrengthLte_mRssnr = view.findViewById(R.id.mCellSignalStrengthLte_mRssnr);
        textView_mCellSignalStrengthLte_mSignalStrength = view.findViewById(R.id.mCellSignalStrengthLte_mSignalStrength);
        textView_mCellSignalStrengthLte_mTimingAdvance = view.findViewById(R.id.mCellSignalStrengthLte_mTimingAdvance);
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
                    Map<String,Double> map = getCellInfo.myGps();
                    if(strGetCellInfo != null){
                        JSONArray jsonarray = new JSONArray(strGetCellInfo);
                        JSONObject jsonobject0 = jsonarray.getJSONObject(0);
                        mCellIdentityCdma_mBasestationId = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mBasestationId");
//                        mCellIdentityCdma_mLatitude = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mLatitude");
                        mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
//                        mCellIdentityCdma_mLongitude = jsonobject0.getJSONObject("mCellIdentityCdma").getString("mLongitude");
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
                        textView_mCellIdentityCdma_mSystemId.setText(mCellIdentityCdma_mSystemId);
                        textView_mCellSignalStrengthCdma_mCdmaDbm.setText(mCellSignalStrengthCdma_mCdmaDbm);
                        textView_mCellSignalStrengthCdma_mCdmaEcio.setText(modiferEcio(mCellSignalStrengthCdma_mCdmaEcio));
                        textView_mCellSignalStrengthCdma_mEvdoDbm.setText(mCellSignalStrengthCdma_mEvdoDbm);
                        textView_mCellSignalStrengthCdma_mEvdoEcio.setText(modiferEcio(mCellSignalStrengthCdma_mEvdoEcio));
                        textView_mCellSignalStrengthCdma_mEvdoSnr.setText(mCellSignalStrengthCdma_mEvdoSnr);
//                        textView_mCellIdentityCdma_mRegistered.setText(mCellIdentityCdma_mRegistered);
//                        textView_mCellIdentityCdma_mTimeStamp.setText(mCellIdentityCdma_mTimeStamp);
//                        textView_mCellIdentityCdma_mTimeStampType.setText(mCellIdentityCdma_mTimeStampType);
                        textView_mCellIdentityLte_mCi.setText(mCellIdentityLte_mCi);
                        textView_mCellIdentityLte_mEarfcn.setText(mCellIdentityLte_mEarfcn);
                        textView_mCellIdentityLte_mMcc.setText(mCellIdentityLte_mMcc);
                        textView_mCellIdentityLte_mMnc.setText(mCellIdentityLte_mMnc);
                        textView_mCellIdentityLte_mPci.setText(mCellIdentityLte_mPci);
                        textView_mCellIdentityLte_mTac.setText(mCellIdentityLte_mTac);
                        textView_mCellSignalStrengthLte_mCqi.setText(mCellSignalStrengthLte_mCqi);
                        textView_mCellSignalStrengthLte_mRsrp.setText(mCellSignalStrengthLte_mRsrp);
                        textView_mCellSignalStrengthLte_mRsrq.setText(mCellSignalStrengthLte_mRsrq);
                        textView_mCellSignalStrengthLte_mRssnr.setText(mCellSignalStrengthLte_mRssnr);
                        textView_mCellSignalStrengthLte_mSignalStrength.setText(mCellSignalStrengthLte_mSignalStrength);
                        textView_mCellSignalStrengthLte_mTimingAdvance.setText(mCellSignalStrengthLte_mTimingAdvance);
//                        textView24.setText(mCellSignalStrengthLte_mTimingAdvance);
                    }else{
                        textViewm_CellIdentityCdma_mBasestationId.setText("无法识别小区信息，可能需要授权");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Ecio 除以10
    @NonNull
    private String modiferEcio(String ecio){
        int ecio_int = Integer.parseInt(ecio);
        int ecio_int_result = ecio_int/10;
        return String.valueOf(ecio_int_result);
    }

}
