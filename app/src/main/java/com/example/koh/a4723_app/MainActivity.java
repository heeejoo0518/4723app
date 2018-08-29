package com.example.koh.a4723_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.Timestamp;
import java.text.Format;
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

        String my_date = getPreferences("날짜"); // 사용자가 저장한 마지막 생리 날짜 불러오기

        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(now);
        String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환

        if (my_date.length() != 0 ) {
            Date startDate = new Date();
            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();

            }
            Date endDate = new Date();
            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
                long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
                final String tmp = diffDay / 7 + "주 " + diffDay % 7 + "일째";
                TextView my_weeks = (TextView) findViewById(R.id.my_weeks);
                my_weeks.setText(tmp);

                long test1 = endDate.getTime()/1000;
                long test2 = 280 * 24*  60 * 60 ;
                long test3 = test1 + test2;
                String test4 = Long.toString(test3);

                Date date3 = new Date(test3);
                Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

           // Toast.makeText(getApplicationContext() , test3+ " 저장 완료", Toast.LENGTH_SHORT).show();

        }


        String baby_name = getPreferences("아기이름");

        if(baby_name.length()!=0){
            TextView text1 = (TextView) findViewById(R.id.text1);
            text1.setText("오늘 나의 " + baby_name +"는");

        }



        Pregnant_Week.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Weight_Graph.class);
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
                savePreferences("날짜",data);  //받은 날짜를 앱에 저장

               String baby_name = intent.getStringExtra("아기이름");
                savePreferences("아기이름",baby_name);
            }
        }
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

    public void onResume() {
        super.onResume();
        String my_date = getPreferences("날짜"); // 사용자가 저장한 마지막 생리 날짜 불러오기
        TextView my_weeks = (TextView) findViewById(R.id.my_weeks);
        TextView delivery_day = (TextView) findViewById(R.id.delivery_day);

        long now = System.currentTimeMillis();
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = new Date(now);
        String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환


       if (my_date.length() != 0) {
            Date startDate = new Date();
            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            } //지금 날짜 형식 변환

            Date endDate = new Date();
            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            } //사용자가 저장한 날짜 불러온거 형식 변환


            long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);

            if (diffDay < 0) {
                diffDay = 0;
            }

            String tmp = diffDay / 7 + "주 " + diffDay % 7 + "일째";
            my_weeks.setText(tmp);

           //Toast.makeText(getApplicationContext() , " 저장 완료", Toast.LENGTH_SHORT).show();

           long test1 = endDate.getTime()/1000;
           long test2 = 279*24*60*60;

           String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date((test1+test2)*1000L));

           if(timeStamp.length()!=0){

               String year = timeStamp.substring(0,4);
               String month = null;

               if(timeStamp.substring(4,5).equals("1")){
                   month = timeStamp.substring(4,6);
                   //Toast.makeText(getApplicationContext() , " ㅋ", Toast.LENGTH_SHORT).show();
               }
               else{
                   month = timeStamp.substring(5,6);
               }
               String day = timeStamp.substring(6,8);
               delivery_day.setText("출산 예정일은 "+year + "년"+month+"월"+day+"일");

           }

       }

        String baby_name = getPreferences("아기이름");

        TextView text1 = (TextView) findViewById(R.id.text1);


        if(baby_name.length()!=0){

            text1.setText("오늘 나의 " + baby_name +"는");

        }
        if(baby_name.length()==0){
            text1.setText("오늘 나의 아이는");
        }




    }






}
