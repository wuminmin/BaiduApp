package com.ysc.baiduapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wuminmin on 3/5/16.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context myContext;
    private LayoutInflater gridContainer;
    private String[] name;

    public final class GridItemView{
        public TextView grid_tittle;
        public ImageView grid_icon;
    }



    public GridViewAdapter(Context context) {
        this.myContext = context;
        gridContainer = LayoutInflater.from(myContext);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
