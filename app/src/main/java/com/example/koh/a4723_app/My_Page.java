package com.example.koh.a4723_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button btn = (Button) findViewById(R.id.save_data);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                Intent intent = new Intent();
                intent.putExtra("날짜", myDate) ; //MainActivity로 값을 넘김
                setResult(RESULT_OK, intent) ;
                Toast.makeText(getApplicationContext() , "저장 완료", Toast.LENGTH_SHORT).show();

                ((MainActivity)(MainActivity.mContext)).onResume(); //MainActivity 새로고침
            }
        });


    }
}
