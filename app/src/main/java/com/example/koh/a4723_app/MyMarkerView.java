package com.example.koh.a4723_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class MyMarkerView extends MarkerView {

    private TextView tvContent;
    Context mContext;
    private Context context;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView)findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        this.mContext = context;

        float weight = e.getY();
        String test_2 = Float.toString(weight); //몸무게

        float getX = e.getX();
        int getX_ = (int) getX; // 며칠차를 선택했는지

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String diff_str = getPreferences("날짜");

        Date startDate = new Date();
        try {
            startDate = sdf.parse(diff_str);
        } catch (ParseException e1) {
            e1.printStackTrace();
        } // 사용자가 등록한 마지막 생리 날짜 변환

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(java.util.Calendar.DATE, getX_ -1);

        // 특정 형태의 날짜로 값을 뽑기
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = df.format(cal.getTime());

        tvContent.setText(strDate + "\n" + test_2 + "kg"  );


        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 50);
    }


    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }

}
