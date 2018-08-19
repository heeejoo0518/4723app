package com.example.koh.a4723_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mContext = this;

        //툴바 설정
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setSubtitle("메인페이지^__________^"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.

        Button Pregnant_Week = (Button) findViewById(R.id.Pregnant_Week);
        Button Health_Service = (Button) findViewById(R.id.Health_Service);
        Button Find_Hospital = (Button) findViewById(R.id.Find_Hospital);
        Button Calendar = (Button) findViewById(R.id.Calendar);
        Button TEST = (Button) findViewById(R.id.testbutton);//테스트버튼

        final TextView text1 = (TextView) findViewById(R.id.text1);

        String my_date = getPreferences(); // 사용자가 저장한 마지막 생리 날짜 불러오기

        long now = System.currentTimeMillis();
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = new Date(now);
        String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환

        if (my_date.length() != 0 ){
            Date startDate = null;
            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            } //지금 날짜 형식 변환

            Date endDate = null;
            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            } //사용자가 저장한 날짜 불러온거 형식 변환

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
            if (diffDay < 0) {
                diffDay = 0;
            }

           final String tmp = diffDay / 7 + "주 " + diffDay % 7 + "일째";
            TextView my_weeks = (TextView) findViewById(R.id.my_weeks);
            my_weeks.setText(tmp);

        }

        Pregnant_Week.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Pregnant_Week.class);
            startActivity(intent);
        }
    });

        Health_Service.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Health_Service.class);
            startActivity(intent);
        }
    });

        Find_Hospital.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Find_Hospital.class);
            startActivity(intent);
        }
    });

        Calendar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Calendar.class);
            startActivity(intent);
        }
    });
        //테스트버튼-===========================
        TEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TEST.class);
                startActivity(intent);
            }
        });
        //==========================================

}
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.My_Page:
                Intent intent = new Intent(MainActivity.this,My_Page.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    static final int REQ_ADD_CONTACT = 1 ;

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                String data = intent.getStringExtra("날짜"); //마이 페이지로부터 날짜를 받음

                    savePreferences(data);  //받은 날짜를 앱에 저장

            }
        }
    }

    private void savePreferences(String str){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("날짜", str);
        editor.commit();
    }

    private String getPreferences(){ //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString("날짜", "");
        return temp;
    }

    public void onResume() {

        super.onResume();
        String my_date = getPreferences(); // 사용자가 저장한 마지막 생리 날짜 불러오기
        TextView my_weeks = (TextView) findViewById(R.id.my_weeks);
        long now = System.currentTimeMillis();
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = new Date(now);
        String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환

        if (my_date.length() != 0) {
            Date startDate = null;
            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            } //지금 날짜 형식 변환

            Date endDate = null;
            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            } //사용자가 저장한 날짜 불러온거 형식 변환

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
            if (diffDay < 0) {
                diffDay = 0;
            }

            String tmp = diffDay / 7 + "주 " + diffDay % 7 + "일째";
            my_weeks.setText(tmp);
        }
    }
}
