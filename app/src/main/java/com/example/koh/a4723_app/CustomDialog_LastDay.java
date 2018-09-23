package com.example.koh.a4723_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class CustomDialog_LastDay extends Dialog implements View.OnClickListener{

    private static final int LAYOUT = R.layout.activity_custom_dialog_lastday;

    private Context context;

    private TextInputEditText nameEt;

    private TextView cancelTv;
    private TextView searchTv;
    private String name;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;


    public CustomDialog_LastDay(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog_LastDay(Context context, String name){
        super(context);
        this.context = context;
        this.name = name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);


        cancelTv = (TextView) findViewById(R.id.cancel);
        searchTv = (TextView) findViewById(R.id.save_date);
        cancelTv.setOnClickListener(this);
        searchTv.setOnClickListener(this);



        spinner1 = (Spinner)findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(context,R.array.year, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner)findViewById(R.id.mySpinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(context,R.array.month, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner)findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(context,R.array.day, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);



        if(name != null){
            nameEt.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                cancel();
                break;
            case R.id.save_date:

                String myDate_year = (String) spinner1.getSelectedItem();
                String myDate_month = (String) spinner2.getSelectedItem();
                String myDate_day = (String) spinner3.getSelectedItem();

                int myDate_year_position = (int) spinner1.getSelectedItemPosition();
                int myDate_month_position = (int) spinner2.getSelectedItemPosition();
                int myDate_day_position = (int) spinner3.getSelectedItemPosition();

                savePreferences("년", Integer.toString(myDate_year_position)); //스피너 선택값 저장
                savePreferences("월", Integer.toString(myDate_month_position));
                savePreferences("일", Integer.toString(myDate_day_position));

                if(myDate_month.length() == 1){ //월,일이 한자리수일때 0을 덧붙임
                    myDate_month = "0"+myDate_month;
                }
                if(myDate_day.length() == 1){
                    myDate_day = "0"+myDate_day;
                }

                String myDate = myDate_year + myDate_month + myDate_day;

                String tmp = getPreferences("날짜");

                if(tmp.length() > 1) {

                }

                savePreferences("날짜",myDate);
                ((MainActivity)(MainActivity.mContext)).onResume();

                cancel();
                ((Activity) context).finish();
                Intent refresh =new Intent(context , My_Page.class);
                context.startActivity(refresh);
                break;
        }
    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }


    private String getPreferences(String code) { //데이터 불러오는 함수
        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code, "");
        return temp;

    }
}

