package com.example.koh.a4723_app;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class Health_Service extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__service);


        final TextView tv = (TextView)findViewById(R.id.textView1);
        Spinner s = (Spinner)findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
               int city = position;
               /* switch (city){
                    case 1:
                        tv.setText("선택한 지역은 " +parent.getItemAtPosition(position)+" 입니다");
                }*/
                tv.setText("선택한 지역은 " +parent.getItemAtPosition(position)+" 입니다");

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
