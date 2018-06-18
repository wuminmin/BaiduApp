package com.ysc.baiduapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ysc.baiduapp.R;
import com.ysc.baiduapp.service.GetCellInfo;
import com.ysc.baiduapp.service.PostExample;
import com.ysc.baiduapp.viewcustom.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;


/**
 * Created by wjx on 2016-1-12.
 */
public class OrderFragment extends BaseFragment {
    private ImageView iv_order;
    private View view;
    private GetCellInfo getCellInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, null, false);
        TelephonyManager telephonyManager = (TelephonyManager)  getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        getCellInfo = new GetCellInfo(getActivity().getApplicationContext(),telephonyManager,getActivity());
        init();
        return view;
    }

    private void init() {
        TextView order_upload_data = view.findViewById(R.id.order_upload_data);

        order_upload_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        //
                        // TODO: http request.

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
                                mCellIdentityCdma_mLatitude = Double.toString(map.get("getLatitude"));
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

                                PostExample example = new PostExample();
                                EditText order_Building_location = view.findViewById(R.id.order_Building_location);
                                EditText order_Building_layers = view.findViewById(R.id.order_Building_layers);
                                EditText order_Current_layer = view.findViewById(R.id.order_Current_layer);

                                EditText shi = view.findViewById(R.id.shi);
                                EditText quxian = view.findViewById(R.id.quxian);
                                EditText wanggeid = view.findViewById(R.id.wanggeid);
                                EditText wangedanyuanmingchen = view.findViewById(R.id.wangedanyuanmingchen);
                                EditText wanggedanyuanxiaolei = view.findViewById(R.id.wanggedanyuanxiaolei);
                                EditText wangluoredianleixing = view.findViewById(R.id.wangluoredianleixing);
                                EditText wanggesangaoleixing = view.findViewById(R.id.wanggesangaoleixing);
                                EditText wanggezhuhushu = view.findViewById(R.id.wanggezhuhushu);
                                EditText wanggedianxinyonghushu = view.findViewById(R.id.wanggedianxinyonghushu);
                                EditText wanggeruzhubili = view.findViewById(R.id.wanggeruzhubili);
                                EditText louyushifenfugaibili = view.findViewById(R.id.louyushifenfugaibili);
                                EditText mrshineifugaizhiliang = view.findViewById(R.id.mrshineifugaizhiliang);
                                EditText lteshineifugaizhiliang = view.findViewById(R.id.lteshineifugaizhiliang);
                                EditText xinxilaiyuan = view.findViewById(R.id.xinxilaiyuan);
                                EditText wanggejianzuwuleixing = view.findViewById(R.id.wanggejianzuwuleixing);
                                EditText shifouyiyoushifenguihua = view.findViewById(R.id.shifouyiyoushifenguihua);
                                EditText shifouyouyiwangshifen = view.findViewById(R.id.shifouyouyiwangshifen);
                                EditText shifouyoudianti = view.findViewById(R.id.shifouyoudianti);
                                EditText shifouyoudixiatingchechang = view.findViewById(R.id.shifouyoudixiatingchechang);
                                EditText gengxinriqi = view.findViewById(R.id.gengxinriqi);
                                EditText beizhu = view.findViewById(R.id.beizhu);

                                String json = "{"
                                        + "\"order_Building_location\":\""+order_Building_location.getText()+"\","
                                        + "\"order_Building_layers\":\""+order_Building_layers.getText()+"\","
                                        + "\"order_Current_layer\":\""+order_Current_layer.getText()+"\","
                                        + "\"shi\":\""+shi.getText()+"\","
                                        + "\"quxian\":\""+quxian.getText()+"\","
                                        + "\"wanggeid\":\""+wanggeid.getText()+"\","
                                        + "\"wangedanyuanmingchen\":\""+wangedanyuanmingchen.getText()+"\","
                                        + "\"wanggedanyuanxiaolei\":\""+wanggedanyuanxiaolei.getText()+"\","
                                        + "\"wangluoredianleixing\":\""+wangluoredianleixing.getText()+"\","
                                        + "\"wanggesangaoleixing\":\""+wanggesangaoleixing.getText()+"\","
                                        + "\"wanggezhuhushu\":\""+wanggezhuhushu.getText()+"\","
                                        + "\"wanggedianxinyonghushu\":\""+wanggedianxinyonghushu.getText()+"\","
                                        + "\"wanggeruzhubili\":\""+wanggeruzhubili.getText()+"\","
                                        + "\"louyushifenfugaibili\":\""+louyushifenfugaibili.getText()+"\","
                                        + "\"mrshineifugaizhiliang\":\""+mrshineifugaizhiliang.getText()+"\","
                                        + "\"lteshineifugaizhiliang\":\""+lteshineifugaizhiliang.getText()+"\","
                                        + "\"xinxilaiyuan\":\""+xinxilaiyuan.getText()+"\","
                                        + "\"wanggejianzuwuleixing\":\""+wanggejianzuwuleixing.getText()+"\","
                                        + "\"shifouyiyoushifenguihua\":\""+shifouyiyoushifenguihua.getText()+"\","
                                        + "\"shifouyouyiwangshifen\":\""+shifouyouyiwangshifen.getText()+"\","
                                        + "\"shifouyoudianti\":\""+shifouyoudianti.getText()+"\","
                                        + "\"shifouyoudixiatingchechang\":\""+shifouyoudixiatingchechang.getText()+"\","
                                        + "\"gengxinriqi\":\""+gengxinriqi.getText()+"\","
                                        + "\"beizhu\":\""+beizhu.getText()+"\","
                                        + "\"mCellIdentityCdma_mBasestationId\":\""+mCellIdentityCdma_mBasestationId+"\","
                                        + "\"mCellIdentityCdma_mLatitude\":\""+mCellIdentityCdma_mLatitude+"\","
                                        + "\"mCellIdentityCdma_mLongitude\":\""+mCellIdentityCdma_mLongitude+"\","
                                        + "\"mCellIdentityCdma_mNetworkId\":\""+mCellIdentityCdma_mNetworkId+"\","
                                        + "\"mCellIdentityCdma_mSystemId\":\""+mCellIdentityCdma_mSystemId+"\","
                                        + "\"mCellSignalStrengthCdma_mCdmaDbm\":\""+mCellSignalStrengthCdma_mCdmaDbm+"\","
                                        + "\"mCellSignalStrengthCdma_mCdmaEcio\":\""+mCellSignalStrengthCdma_mCdmaEcio+"\","
                                        + "\"mCellSignalStrengthCdma_mEvdoDbm\":\""+mCellSignalStrengthCdma_mEvdoDbm+"\","
                                        + "\"mCellSignalStrengthCdma_mEvdoEcio\":\""+mCellSignalStrengthCdma_mEvdoEcio+"\","
                                        + "\"mCellSignalStrengthCdma_mEvdoSnr\":\""+mCellSignalStrengthCdma_mEvdoSnr+"\","
                                        + "\"mCellIdentityCdma_mRegistered\":\""+mCellIdentityCdma_mRegistered+"\","
                                        + "\"mCellIdentityCdma_mTimeStamp\":\""+mCellIdentityCdma_mTimeStamp+"\","
                                        + "\"mCellIdentityCdma_mTimeStampType\":\""+mCellIdentityCdma_mTimeStampType+"\","
                                        + "\"mCellIdentityLte_mCi\":\""+mCellIdentityLte_mCi+"\","
                                        + "\"mCellIdentityLte_mEarfcn\":\""+mCellIdentityLte_mEarfcn+"\","
                                        + "\"mCellIdentityLte_mMcc\":\""+mCellIdentityLte_mMcc+"\","
                                        + "\"mCellIdentityLte_mMnc\":\""+mCellIdentityLte_mMnc+"\","
                                        + "\"mCellIdentityLte_mPci\":\""+mCellIdentityLte_mPci+"\","
                                        + "\"mCellIdentityLte_mTac\":\""+mCellIdentityLte_mTac+"\","
                                        + "\"mCellSignalStrengthLte_mCqi\":\""+mCellSignalStrengthLte_mCqi+"\","
                                        + "\"mCellSignalStrengthLte_mRsrp\":\""+mCellSignalStrengthLte_mRsrp+"\","
                                        + "\"mCellSignalStrengthLte_mRsrq\":\""+mCellSignalStrengthLte_mRsrq+"\","
                                        + "\"mCellSignalStrengthLte_mRssnr\":\""+mCellSignalStrengthLte_mRssnr+"\","
                                        + "\"mCellSignalStrengthLte_mSignalStrength\":\""+mCellSignalStrengthLte_mSignalStrength+"\","
                                        + "\"mCellSignalStrengthLte_mTimingAdvance\":\""+mCellSignalStrengthLte_mTimingAdvance+"\","
                                        + "\"mCellIdentityLte_mRegistered\":\""+mCellIdentityLte_mRegistered+"\","
                                        + "\"mCellIdentityLte_mTimeStamp\":\""+mCellIdentityLte_mTimeStamp+"\","
                                        + "\"mCellIdentityLte_mTimeStampType\":\""+mCellIdentityLte_mTimeStampType+"\""
                                        + "}";
                                String response = null;
                                try {
                                    response = example.post(json);
                                    System.out.println(response);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(getActivity().getApplicationContext(), "上传成功！！！！"+response, Toast.LENGTH_LONG).show();
                    }
                };
                new Thread(runnable).start();

                Toast.makeText(getActivity().getApplicationContext(), "上传成功！！！！", Toast.LENGTH_LONG).show();

            }
        });
    }
}
