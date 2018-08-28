package com.example.koh.a4723_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class OnedayView extends LinearLayout{
    TextView textView;
    public OnedayView(Context context){
        super(context);
        init(context);
    }
    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.list_item,this);//재탕
        textView = (TextView) findViewById(R.id.schedule_);
    }
    public void setStatus(String status){
        textView.setText(status);
    }
}
