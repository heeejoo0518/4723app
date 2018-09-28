package com.example.koh.a4723_app.calendar;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.example.koh.a4723_app.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;


public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    String tableName;
    final String dbName = "Calendar_DB";
    SQLiteDatabase db=null;
    private final Calendar_OnedayDecorator oneDayDecorator = new Calendar_OnedayDecorator();
    Calendar_EventDecorator eventDecorator;

    Fragment_Oneday fragment0 = new Fragment_Oneday();
    Fragment_Schedule fragment1 = new Fragment_Schedule(); //tableName = "a"+날짜
    Fragment_Status fragment2 = new Fragment_Status(); //tableName = "Table_status"로 고정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Table_status (date VARCHAR, status VARCHAR);");

        fragment0.setDB(db);
        fragment1.setDB(db);
        fragment2.setDB(db);

        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("달력");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        tableName = format.format(new Date(System.currentTimeMillis()));
        tableName = "a" + tableName;
        fragment0.setTableName(tableName);
        fragment1.setTableName(tableName);

        String str = new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis()));
        fragment2.setDate(str);fragment0.setDate(str);

        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (time VARCHAR PRIMARY KEY,schedule VARCHAR);");//time,schedule 칼럼 있는 테이블 추가


        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(java.util.Calendar.SUNDAY)
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new Calendar_SundayDecorator(),
                new Calendar_SaturdayDecorator(),
                oneDayDecorator);

       if(fragment2.update()!=null){
           new ApiSimulator(fragment2.update()).executeOnExecutor(Executors.newSingleThreadExecutor());//저장된 상태 달력에 표시
       }

        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment0).commit(); //fragment_oneday:기본상태

        //이미지버튼 클릭--- 프래그먼트 전환
        ImageButton plus = (ImageButton) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

            }
        });

        ImageButton smile = (ImageButton) findViewById(R.id.smile);
        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
            }
        });

        //클릭 이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();

                //////테이블이름 생성/////////////////////
                String year_st, month_st, day_st;
                year_st = Integer.toString(year);
                if (month < 9) month_st = '0' + Integer.toString(month+1);//month+1해야 오늘 날짜로 선택했던 거랑 맞음
                else month_st = Integer.toString(month+1);
                if (day < 10) day_st = '0' + Integer.toString(day);
                else day_st = Integer.toString(day);
                tableName = year_st + month_st + day_st;
                tableName = "a" + tableName;

                //프래그먼트들에 tableName 넘김
                fragment0.setTableName(tableName);
                fragment1.setTableName(tableName);


                String str = Integer.toString(date.getYear())+"/"+month_st+"/"+day_st;
                fragment2.setDate(str);

                db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (time VARCHAR PRIMARY KEY,schedule VARCHAR);");//time,schedule 칼럼 있는 테이블 추가
                //fragment0에 tableName, date 전달
                fragment0.setT(tableName);fragment0.setTableName(tableName);
                fragment0.setS(str); fragment0.setDate(str);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment0).commit(); //날짜 새로 선택할 때마다 fragment_oneday로 교체

            }
        });

    }

    public InputMethodManager setIMM(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm;
    }

    public void setFragment0(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment0).commit();
    }

    public void setFragment2(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
    }
    public void clickStatus(){ // 상태 업데이트할 때마다 실행됨
        new ApiSimulator(fragment2.update()).executeOnExecutor(Executors.newSingleThreadExecutor());//저장된 상태 달력에 표시
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Updates = null;

        ApiSimulator(ArrayList<String> Updates){
            this.Updates = new String[Updates.size()];
            this.Updates = Updates.toArray(this.Updates);
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            for(int i = 0 ; i < Updates.length ; i ++){
                String[] update = Updates[i].split("/");
                int year = Integer.parseInt(update[0]);
                int month = Integer.parseInt(update[1]) - 1;
                int day = Integer.parseInt(update[2]);

                calendar.set(year,month,day); //month = 0 -> 1월
                CalendarDay c_day = CalendarDay.from(calendar);
                dates.add(c_day);

            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            if (isFinishing()) {
                return;
            }
            if(eventDecorator!=null) materialCalendarView.removeDecorator(eventDecorator);
            eventDecorator = new Calendar_EventDecorator(calendarDays,Calendar.this);
            materialCalendarView.addDecorator(eventDecorator);//yellow
        }
    }
}