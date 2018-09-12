package com.example.koh.a4723_app;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class CustomDialog_HealthCenter extends Dialog implements View.OnClickListener{

    private static final int LAYOUT = R.layout.activity_custom_dialog__health_center;

    private Context context;

    private TextInputEditText nameEt;

    private TextView cancelTv;
    private TextView searchTv;
    private String name;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;


    public CustomDialog_HealthCenter(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog_HealthCenter(Context context, String name){
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
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(context,R.array.city, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


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

                String health_center = (String) spinner1.getSelectedItem();

                savePreferences("보건소",health_center);
                Toast.makeText(context.getApplicationContext() , getPreferences("보건소") + "" , Toast.LENGTH_SHORT).show();
                ((MainActivity)(MainActivity.mContext)).onResume();

                cancel();
                break;
        }
    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }
}

