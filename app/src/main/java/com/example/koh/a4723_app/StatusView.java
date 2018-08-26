package com.example.koh.a4723_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class StatusView extends LinearLayout{
    TextView textView;
    public StatusView(Context context){
        super(context);
        init(context);
    }
    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.grid_item,this);
        textView = (TextView) findViewById(R.id.status_);
    }
    public void setStatus(String status){
        textView.setText(status);
    }
}
