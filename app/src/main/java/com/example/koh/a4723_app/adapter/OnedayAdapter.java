package com.example.koh.a4723_app.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class OnedayAdapter extends BaseAdapter {
    ArrayList<String> items = new ArrayList<>();

    public OnedayAdapter() {}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String item){
        items.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnedayView view = null;
        Context context = parent.getContext();

        if (convertView == null){
            view = new OnedayView(context);
        }else{
            view = (OnedayView)convertView;
        }

        view.setStatus(items.get(position));

        return view;
    }
}
