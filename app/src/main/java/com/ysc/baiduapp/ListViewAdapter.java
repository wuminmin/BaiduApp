package com.ysc.baiduapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private String[] sell = {"已售444份", "已售555份", "已售666份", "已售777份", "已售888份", "已售999份"};
    private String[] titles = {"正宗重庆麻辣烫", "一洋码头", "鲜花圣地", "丁丁洋自助火锅", "什锦面香面", "百变猪排"};
    private String[] distance = {"990m", "1.45km", "3km", "10m", "2.5km", "2km"};
    private String[] way = {"起送20¥|配送5¥|平均45分钟", "起送10¥|配送8¥|平均15分钟", "起送0¥|配送20¥|平均1小时", "起送8¥|配送5¥|平均25分钟", "起送20¥|配送5¥|平均45分钟", "起送50¥|配送0¥|平均2小时"};
    private int[] other = {3, 1, 0, 0, 0, 2};
    private int[] right_cor = {4, 4, 2, 3, 4, 3};

    private int[] star = {7, 8, 10, 9, 8, 10};
    private int[] resIds = {R.drawable.shopone, R.drawable.shoptwo, R.drawable.shopthree, R.drawable.fire, R.drawable.noodle, R.drawable.pig};
    private Context mContext;
    private static final int droidGreen = Color.parseColor("#A4C639");

    private LayoutInflater listContainer;           //视图容器

    public final class ListItemView {                //自定义控件集合
        public TextView tv_title, tv_way, tv_distance, tv_sell;
        public ImageView iv_pic, iv_star1, iv_star2, iv_star3, iv_star4, iv_star5, iv_star6, iv_star7, iv_star8, iv_star9, iv_star10, iv_star012, iv_star034, iv_star056, iv_star078, iv_star0910;
        public ImageView iv_quan, iv_piao, iv_fu, iv_pei;
        public LinearLayout ly_1, ly_2, ly_3;
    }


    public ListViewAdapter(Context pContext) {

        this.mContext = pContext;
        listContainer = LayoutInflater.from(this.mContext);
    }


    @Override
    public int getCount() {
        return sell.length;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.item, null);
            //获取控件对象
            listItemView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            listItemView.tv_way = (TextView) convertView.findViewById(R.id.tv_way);
            listItemView.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            listItemView.tv_sell = (TextView) convertView.findViewById(R.id.tv_sell);

            listItemView.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            listItemView.iv_star1 = (ImageView) convertView.findViewById(R.id.iv_star1);
            listItemView.iv_star2 = (ImageView) convertView.findViewById(R.id.iv_star2);
            listItemView.iv_star3 = (ImageView) convertView.findViewById(R.id.iv_star3);
            listItemView.iv_star4 = (ImageView) convertView.findViewById(R.id.iv_star4);
            listItemView.iv_star5 = (ImageView) convertView.findViewById(R.id.iv_star5);
            listItemView.iv_star6 = (ImageView) convertView.findViewById(R.id.iv_star6);
            listItemView.iv_star7 = (ImageView) convertView.findViewById(R.id.iv_star7);
            listItemView.iv_star8 = (ImageView) convertView.findViewById(R.id.iv_star8);
            listItemView.iv_star9 = (ImageView) convertView.findViewById(R.id.iv_star9);
            listItemView.iv_star012 = (ImageView) convertView.findViewById(R.id.iv_star012);
            listItemView.iv_star034 = (ImageView) convertView.findViewById(R.id.iv_star034);
            listItemView.iv_star056 = (ImageView) convertView.findViewById(R.id.iv_star056);
            listItemView.iv_star078 = (ImageView) convertView.findViewById(R.id.iv_star078);
            listItemView.iv_star0910 = (ImageView) convertView.findViewById(R.id.iv_star0910);
            listItemView.iv_star10 = (ImageView) convertView.findViewById(R.id.iv_star10);
            listItemView.iv_quan = (ImageView) convertView.findViewById(R.id.iv_quan);
            listItemView.iv_piao = (ImageView) convertView.findViewById(R.id.iv_piao);
            listItemView.iv_fu = (ImageView) convertView.findViewById(R.id.iv_fu);
            listItemView.iv_pei = (ImageView) convertView.findViewById(R.id.iv_pei);
            listItemView.ly_1 = (LinearLayout) convertView.findViewById(R.id.ly_1);
            listItemView.ly_2 = (LinearLayout) convertView.findViewById(R.id.ly_2);
            listItemView.ly_3 = (LinearLayout) convertView.findViewById(R.id.ly_3);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
        listItemView.tv_title.setText(titles[position]);
        listItemView.tv_way.setText(way[position]);
        listItemView.tv_distance.setText(distance[position]);
        listItemView.tv_sell.setText(sell[position]);
        listItemView.iv_pic.setImageResource(resIds[position]);
        int star_num = star[position];
        int right = right_cor[position];
        int ly = other[position];
        System.out.println("star_num:" + star_num + ",position:" + position);

        listItemView.iv_star1.setVisibility(View.GONE);
        listItemView.iv_star2.setVisibility(View.GONE);
        listItemView.iv_star3.setVisibility(View.GONE);
        listItemView.iv_star4.setVisibility(View.GONE);
        listItemView.iv_star5.setVisibility(View.GONE);
        listItemView.iv_star6.setVisibility(View.GONE);
        listItemView.iv_star7.setVisibility(View.GONE);
        listItemView.iv_star8.setVisibility(View.GONE);
        listItemView.iv_star9.setVisibility(View.GONE);
        listItemView.iv_star012.setVisibility(View.GONE);
        listItemView.iv_star034.setVisibility(View.GONE);
        listItemView.iv_star056.setVisibility(View.GONE);
        listItemView.iv_star078.setVisibility(View.GONE);
        listItemView.iv_star0910.setVisibility(View.GONE);
        listItemView.iv_star10.setVisibility(View.GONE);

        listItemView.iv_quan.setVisibility(View.GONE);
        listItemView.iv_piao.setVisibility(View.GONE);
        listItemView.iv_fu.setVisibility(View.GONE);
        listItemView.iv_pei.setVisibility(View.GONE);

        listItemView.ly_1.setVisibility(View.GONE);
        listItemView.ly_2.setVisibility(View.GONE);
        listItemView.ly_3.setVisibility(View.GONE);
        switch (star_num) {
            case 0:
                listItemView.iv_star012.setVisibility(View.VISIBLE);
                listItemView.iv_star034.setVisibility(View.VISIBLE);
                listItemView.iv_star056.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 1:
                listItemView.iv_star1.setVisibility(View.VISIBLE);
                listItemView.iv_star034.setVisibility(View.VISIBLE);
                listItemView.iv_star056.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 2:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star034.setVisibility(View.VISIBLE);
                listItemView.iv_star056.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 3:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star3.setVisibility(View.VISIBLE);
                listItemView.iv_star056.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 4:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star056.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 5:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star5.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 6:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star6.setVisibility(View.VISIBLE);
                listItemView.iv_star078.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 7:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star6.setVisibility(View.VISIBLE);
                listItemView.iv_star7.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 8:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star6.setVisibility(View.VISIBLE);
                listItemView.iv_star8.setVisibility(View.VISIBLE);
                listItemView.iv_star0910.setVisibility(View.VISIBLE);
                break;
            case 9:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star6.setVisibility(View.VISIBLE);
                listItemView.iv_star8.setVisibility(View.VISIBLE);
                listItemView.iv_star9.setVisibility(View.VISIBLE);
                break;
            case 10:
                listItemView.iv_star2.setVisibility(View.VISIBLE);
                listItemView.iv_star4.setVisibility(View.VISIBLE);
                listItemView.iv_star6.setVisibility(View.VISIBLE);
                listItemView.iv_star8.setVisibility(View.VISIBLE);
                listItemView.iv_star10.setVisibility(View.VISIBLE);
                break;

        }

        switch (right) {
            case 0:
                break;
            case 1:
                listItemView.iv_quan.setVisibility(View.GONE);
                listItemView.iv_piao.setVisibility(View.GONE);
                listItemView.iv_fu.setVisibility(View.GONE);
                listItemView.iv_pei.setVisibility(View.VISIBLE);
                break;
            case 2:
                listItemView.iv_quan.setVisibility(View.GONE);
                listItemView.iv_piao.setVisibility(View.GONE);
                listItemView.iv_fu.setVisibility(View.VISIBLE);
                listItemView.iv_pei.setVisibility(View.VISIBLE);
                break;
            case 3:
                listItemView.iv_quan.setVisibility(View.GONE);
                listItemView.iv_piao.setVisibility(View.VISIBLE);
                listItemView.iv_fu.setVisibility(View.VISIBLE);
                listItemView.iv_pei.setVisibility(View.VISIBLE);
                break;
            case 4:
                listItemView.iv_quan.setVisibility(View.VISIBLE);
                listItemView.iv_piao.setVisibility(View.VISIBLE);
                listItemView.iv_fu.setVisibility(View.VISIBLE);
                listItemView.iv_pei.setVisibility(View.VISIBLE);
                break;
        }

        switch (ly){
            case 0:
                break;
            case 1:
                listItemView.ly_1.setVisibility(View.VISIBLE);
                break;
            case 2:
                listItemView.ly_2.setVisibility(View.VISIBLE);
                break;
            case 3:
                listItemView.ly_3.setVisibility(View.VISIBLE);
                break;
        }

        return convertView;
    }
}
