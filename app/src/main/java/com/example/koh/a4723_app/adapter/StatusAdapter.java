package com.example.koh.a4723_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class StatusAdapter extends BaseAdapter {
    ArrayList<String> items = new ArrayList<>();

    public StatusAdapter(){}

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
        this.notifyDataSetChanged();
    }
    public void addItems(String[] item){
        for(int i=0;i<item.length;i++){
            items.add(item[i]);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        StatusView view = null;
        Context context = parent.getContext();

        if (convertView == null){
            view = new StatusView(context);
        }else{
            view = (StatusView)convertView;
        }

        view.setStatus(items.get(position));

        return view;

    }
}
