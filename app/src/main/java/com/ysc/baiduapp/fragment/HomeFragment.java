package com.ysc.baiduapp.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.ysc.baiduapp.WebViewFragment;
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
    private TextView msg;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000 * 5);// 间隔120秒
        }
        void update() {
            //刷新msg的内容
            init();
        }
    };

    private void changeToAnotherFragment(){
        //如果是用的v4的包，则用getActivity().getSuppoutFragmentManager();
//        FragmentManager fm = getActivity().getFragmentManager();
        //注意v4包的配套使用
//        Fragment fragment = new Fragment();
//        fm.beginTransaction().replace(R.id.home_fragment,fragment).commit();

        Fragment fragment = new Fragment();
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.my_webview_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable); //停止刷新
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(), telephonyManager, getActivity());
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changeToAnotherFragment();
                Toast.makeText(getActivity().getApplicationContext(), "测速页面", Toast.LENGTH_LONG).show();
            }
        });
        init();
        handler.postDelayed(runnable, 1000 * 5);
        return view;
    }

    private void init() {
        TextView textViewm_CellIdentityCdma_mBasestationId = view.findViewById(R.id.mCellIdentityCdma_mBasestationId);
        TextView textViewm_CellIdentityCdma_mLatitude = view.findViewById(R.id.mCellIdentityCdma_mLatitude);
        TextView textView_mCellIdentityCdma_mLongitude = view.findViewById(R.id.mCellIdentityCdma_mLongitude);
        TextView textView_mCellIdentityCdma_mNetworkId = view.findViewById(R.id.mCellIdentityCdma_mNetworkId);
        TextView textView_mCellIdentityCdma_mSystemId = view.findViewById(R.id.mCellIdentityCdma_mSystemId);
        TextView textView_mCellSignalStrengthCdma_mCdmaDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaDbm);
        TextView textView_mCellSignalStrengthCdma_mCdmaEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mCdmaEcio);
        TextView textView_mCellSignalStrengthCdma_mEvdoDbm = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoDbm);
        TextView textView_mCellSignalStrengthCdma_mEvdoEcio = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoEcio);
        TextView textView_mCellSignalStrengthCdma_mEvdoSnr = view.findViewById(R.id.mCellSignalStrengthCdma_mEvdoSnr);
        TextView textView_mCellIdentityLte_mCi = view.findViewById(R.id.mCellIdentityLte_mCi);
        TextView textView_mCellIdentityLte_mEarfcn = view.findViewById(R.id.mCellIdentityLte_mEarfcn);
        TextView textView_mCellIdentityLte_mMcc = view.findViewById(R.id.mCellIdentityLte_mMcc);
        TextView textView_mCellIdentityLte_mMnc = view.findViewById(R.id.mCellIdentityLte_mMnc);
        TextView textView_mCellIdentityLte_mPci = view.findViewById(R.id.mCellIdentityLte_mPci);
        TextView textView_mCellIdentityLte_mTac = view.findViewById(R.id.mCellIdentityLte_mTac);
        TextView textView_mCellSignalStrengthLte_mCqi = view.findViewById(R.id.mCellSignalStrengthLte_mCqi);
        TextView textView_mCellSignalStrengthLte_mRsrp = view.findViewById(R.id.mCellSignalStrengthLte_mRsrp);
        TextView textView_mCellSignalStrengthLte_mRsrq = view.findViewById(R.id.mCellSignalStrengthLte_mRsrq);
        TextView textView_mCellSignalStrengthLte_mRssnr = view.findViewById(R.id.mCellSignalStrengthLte_mRssnr);
        TextView textView_mCellSignalStrengthLte_mSignalStrength = view.findViewById(R.id.mCellSignalStrengthLte_mSignalStrength);
        TextView textView_mCellSignalStrengthLte_mTimingAdvance = view.findViewById(R.id.mCellSignalStrengthLte_mTimingAdvance);
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
//            strGetCellInfo = "[]";
            Map<String, Double> map = getCellInfo.myGps();
            if (strGetCellInfo != null ) {
                if("[]".equals(strGetCellInfo)){
                    textViewm_CellIdentityCdma_mBasestationId.setText("无法获取手机参数信息");
                }else {
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
                }

            } else {
                textViewm_CellIdentityCdma_mBasestationId.setText("无法识别小区信息，可能需要授权");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    //Ecio 除以10
    @NonNull
    private String modiferEcio(String ecio) {
        int ecio_int = Integer.parseInt(ecio);
        int ecio_int_result = ecio_int / 10;
        return String.valueOf(ecio_int_result);
    }

}
