package com.example.koh.a4723_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class My_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__page);

        final Spinner spinner1 = (Spinner)findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        final Spinner spinner2 = (Spinner)findViewById(R.id.mySpinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.month, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner)findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.day, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Button save_data = (Button) findViewById(R.id.save_data);
        final EditText name_text = (EditText) findViewById(R.id.baby_name);

        String get_spinner1_position = getPreferences("년");
        String get_spinner2_position = getPreferences("월");
        String get_spinner3_position = getPreferences("일");

        if(get_spinner1_position.length() != 0){                            //마지막으로 저장된 날짜로 셋팅
            int position =   Integer.parseInt(get_spinner1_position);
            spinner1.setSelection(position);
        }

        if(get_spinner2_position.length() != 0){
            int position =   Integer.parseInt(get_spinner2_position);
            spinner2.setSelection(position);
        }

        if(get_spinner3_position.length() != 0){
            int position =   Integer.parseInt(get_spinner3_position);
            spinner3.setSelection(position);
        }


        Button weight = (Button) findViewById(R.id.weight);

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String baby_name = name_text.getText().toString();


                Intent intent = new Intent();
                intent.putExtra("날짜", myDate) ; //MainActivity로 값을 넘김
                intent.putExtra("아기이름",baby_name);
                setResult(RESULT_OK, intent) ;
                ((MainActivity)(MainActivity.mContext)).onResume();

                Toast.makeText(getApplicationContext() , baby_name+ " 저장 완료", Toast.LENGTH_SHORT).show();

            }
        });

       weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(My_Page.this,Weight_Graph.class);
               // startActivity(intent);

                String myDate_year = (String) spinner1.getSelectedItem();
                String myDate_month = (String) spinner2.getSelectedItem();
                String myDate_day = (String) spinner3.getSelectedItem();

                if(myDate_month.length() == 1){ //월,일이 한자리수일때 0을 덧붙임
                    myDate_month = "0"+myDate_month;
                }
                if(myDate_day.length() == 1){
                    myDate_day = "0"+myDate_day;
                }

                String myDate = myDate_year + myDate_month + myDate_day;
                String baby_name = name_text.getText().toString();

                Intent intent = new Intent(My_Page.this,Weight_Graph.class);
                //intent.putExtra("날짜", myDate);
                startActivity(intent);


            }
        });

    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }
}
