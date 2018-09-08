package com.example.koh.a4723_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TEST_adapter extends BaseAdapter {
    ArrayList<String> items = new ArrayList<>();

    public TEST_adapter() {}

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
        TEST_View view = null;
        Context context = parent.getContext();

        if (convertView == null){
            view = new TEST_View(context);
        }else{
            view = (TEST_View)convertView;
        }

        view.setStatus(items.get(position));

        return view;
    }
}


class TEST_View extends LinearLayout {
    TextView textView;
    public TEST_View(Context context){
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.list_item,this);
        textView = (TextView) findViewById(R.id.schedule_);
    }
    public void setStatus(String item){
        textView.setText(item);
    }
}