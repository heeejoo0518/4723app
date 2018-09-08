package com.example.koh.a4723_app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.koh.a4723_app.R;

public class Schedule_View extends LinearLayout{
    TextView schedule;

    public Schedule_View(Context context) {
        super(context);
        init(context);
    }

    public Schedule_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item,this, true);

        schedule = (TextView)findViewById(R.id.schedule_);
        schedule.setTextColor(Color.BLACK);
    }

    public void setSchedule(String sche){
        schedule.setText(sche);
    }
}
