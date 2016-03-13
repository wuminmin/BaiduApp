package com.ysc.baiduapp.viewcustom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysc.baiduapp.R;


/**
 * Created by ysc on 2016-1-20.
 */
public class PersonalInfoFragment extends Fragment{
    private String title;
    private int iconId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(getTitle());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("111");
            }
        });
        return view;
    }
}
