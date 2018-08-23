package com.example.koh.a4723_app;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.ImageButton;

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

    Fragment_Schedule fragment1 = new Fragment_Schedule(); //tableName = "a"+날짜
    Fragment_Status fragment2 = new Fragment_Status(); //tableName = "Table_status"로 고정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        fragment1.setDB(db);
        fragment2.setDB(db);

        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setTitle("Calendar");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        tableName = format.format(new Date(System.currentTimeMillis()));
        tableName = "a" + tableName;
        fragment1.setTableName(tableName);
        fragment2.setDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));


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

        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment1).commit(); //fragment_schedule:기본상태

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

                int year = date.getYear() - 2000; //2000년부터 정상작동,,
                int month = date.getMonth();
                int day = date.getDay();

                //////테이블이름 생성/////////////////////
                String year_st, month_st, day_st;
                if (year < 10) year_st = '0' + Integer.toString(year);
                else year_st = Integer.toString(year);
                if (month < 10) month_st = '0' + Integer.toString(month);
                else month_st = Integer.toString(month);
                if (day < 10) day_st = '0' + Integer.toString(day);
                else day_st = Integer.toString(day);
                tableName = year_st + month_st + day_st;
                tableName = "a" + tableName;

                //프래그먼트에 tableName 넘김
                fragment1.setTableName(tableName);
                fragment2.setDate(Integer.toString(date.getYear())+"/"+month_st+"/"+day_st); //date 칼럼 값

                if (fragment1.listView != null) {
                    fragment1.adapter = new ScheduleAdapter();
                    fragment1.db.execSQL("CREATE TABLE IF NOT EXISTS " + fragment1.tableName + " (schedule VARCHAR);");//schedule 칼럼 1개 있는 테이블 추가
                    fragment1.DB_add();
                    fragment1.listView.setAdapter(fragment1.adapter);
                }
            }
        });

    }

    public void clickStatus(){ // 상태 업데이트할 때마다 실행됨
        new ApiSimulator(fragment2.update()).executeOnExecutor(Executors.newSingleThreadExecutor());//저장된 상태 달력에 표시
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(ArrayList<String> Time_Result){
            this.Time_Result = new String[Time_Result.size()];
            this.Time_Result = Time_Result.toArray(this.Time_Result);
            //this.Time_Result = Time_Result;
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
            for(int i = 0 ; i < Time_Result.length ; i ++){
                String[] time = Time_Result[i].split("/");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

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
            materialCalendarView.addDecorator(new Calendar_EventDecorator(Color.RED, calendarDays,Calendar.this));
        }
    }
}