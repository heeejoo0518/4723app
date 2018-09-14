package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.*;
import com.example.koh.a4723_app.R;

import java.util.ArrayList;



public class Health_Service extends AppCompatActivity {

    //implements ListViewBtnAdapter.ListBtnClickListener
    Button button;
    ArrayList cityList;
    String health_center ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_health__service);

        //SharedPreferences sp = getSharedPreferences("보건소",MODE_PRIVATE);
        //health_center = sp.getString("보건소","");
        //String temp = getSharedPreferences("보건소", );


        String health_center = getPreferences("보건소");
       // Toast.makeText(getApplicationContext(),health_center, Toast.LENGTH_LONG).show();


        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setTitle("보건 사업");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        /*cityList = new ArrayList();
        cityList.add("양양군보건소");
        cityList.add("정선군보건소");
        cityList.add("평창군보건의료원");
        cityList.add("영월군보건소");
        cityList.add("고성군보건소");
        cityList.add("춘천시남면보건지소");
        cityList.add("강릉시보건소");
        cityList.add("인제군보건소");
        cityList.add("정선군보건소");
        cityList.add("양구군보건소");
        cityList.add("원주시보건소");
        cityList.add("춘천시보건소");
        cityList.add("속초시보건소");
        cityList.add("홍천군보건소");
        cityList.add("동해시보건소");
        cityList.add("횡성군보건소");
        cityList.add("철원군보건소");
        cityList.add("주문진보건출장소");
        final String[] select_item = {""};
        final TextView tv = (TextView) findViewById(R.id.textView1);
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, cityList);
        s.setAdapter(adapter);
        Button button = (Button) findViewById(R.id.button);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                select_item[0] = String.valueOf(cityList.get(arg2));
              int city = arg2;
                switch (city){
                    case 1:
                        tv.setText("선택한 지역은 " +parent.getItemAtPosition(position)+" 입니다");
                }
                tv.setText("선택한 지역은 " +arg0.getItemAtPosition(arg2)+" 입니다");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {*/
                if (health_center.equals("양양군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_yangyang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("속초시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Sokcho.class);
                        startActivity(intent);
                        finish();
                    }
                } else if (health_center.equals("평창군보건의료원")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Pyeongchang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("정선군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_jeongseon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("영월군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_youngweol.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("고성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_goseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("강릉시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_gangneug.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("인제군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_inje.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("양구군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_yanggu.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (health_center.equals("춘천시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chuncheon.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (health_center.equals("홍천군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hongcheon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("동해시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_donghae.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("횡성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("철원군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chulwon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("원주시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_wonju.class);
                        startActivity(intent);
                        finish();
                    }
                }
/*
            }
        });*/

    }

    private void savePreferences(String code, String str) { //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }
    private String getPreferences(String code) { //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code, "");
        return temp;
    }


}