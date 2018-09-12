package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.koh.a4723_app.R;

import java.util.ArrayList;



public class Health_Service extends Activity {

    //implements ListViewBtnAdapter.ListBtnClickListener
    Button button;
    ArrayList cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__service);


        cityList = new ArrayList();

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
              /* int city = arg2;
                switch (city){
                    case 1:
                        tv.setText("선택한 지역은 " +parent.getItemAtPosition(position)+" 입니다");
                }
                tv.setText("선택한 지역은 " +arg0.getItemAtPosition(arg2)+" 입니다"); */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (select_item[0].equals("양양군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_yangyang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("속초시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Sokcho.class);
                        startActivity(intent);
                        finish();
                    }
                } else if (select_item[0].equals("평창군보건의료원")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Pyeongchang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("정선군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_jeongseon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("영월군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_youngweol.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("고성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_goseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("강릉시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_gangneug.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("인제군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_inje.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("양구군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_yanggu.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (select_item[0].equals("춘천시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chuncheon.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (select_item[0].equals("홍천군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hongcheon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("동해시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_donghae.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("횡성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("철원군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chulwon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (select_item[0].equals("원주시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_wonju.class);
                        startActivity(intent);
                        finish();
                    }
                }



            }
        });



    }
}