package com.ysc.baiduapp;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ImageView;

import com.ysc.baiduapp.fragment.HomeFragment;


/**
 * Created by wuminmin on 3/5/16.
 */
public class ImageAdapter extends BaseAdapter {
//    private Context myContext;
//
//    public ImageAdapter(Context context){
//        myContext = context;
//    }
//    @Override
//    public int getCount() {
//
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }

    private Context context;
    private final String[] mobileValues;

    public ImageAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = new GridView(context);

        if (convertView == null) {

            //gridView = new View(context);

            // get layout from mobile.xml
//            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
//            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

//            if (mobile.equals("buy")) {
//                imageView.setImageResource(R.drawable.buy);
//            } else if (mobile.equals("deliver")) {
//                imageView.setImageResource(R.drawable.deliver);
//            } else if (mobile.equals("fruit")) {
//                imageView.setImageResource(R.drawable.fruit);
//            } else {
//                imageView.setImageResource(R.drawable.medicine);
//            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
