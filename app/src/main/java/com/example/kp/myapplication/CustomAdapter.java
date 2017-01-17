package com.example.kp.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by kp on 11/01/2017.
 */

public class CustomAdapter extends SimpleAdapter {


    public CustomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);

        if (position % 2 == 0) {

            view.setBackgroundColor(Color.LTGRAY);

        } else {

            view.setBackgroundColor(Color.WHITE);

        }

        return view;

    }



    @Override

    public void setViewBinder(ViewBinder viewBinder) {

        super.setViewBinder(viewBinder);

    }
}


