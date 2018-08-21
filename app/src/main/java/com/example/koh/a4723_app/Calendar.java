package com.example.koh.a4723_app;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.CalendarView;
import android.widget.ImageButton;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Calendar extends AppCompatActivity {
    CalendarView simpleCalendarView;
    String tableName;
    final String dbName = "Calendar_DB";
    SQLiteDatabase db=null;


    Fragment_Schedule fragment1 = new Fragment_Schedule(); //tableName = "a"+날짜
    Fragment_Status fragment2 = new Fragment_Status(); //tableName = "Table_status"로 고정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        fragment1.setDB(db);

        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View inflate = inflater.inflate(R.layout.list_item,null);
        //fragment1.listView = inflate.findViewById(R.id.listview);

        //getFragmentManager().findFragmentById(R.id.listview);///////
        //ListView listView = (ListView)getFragmentManager().findFragmentById(R.id.listview);
        //fragment1.setListView(listView);
        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        //toolbar.setTitle("Calendar");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        tableName = format.format(new Date(System.currentTimeMillis()));
        tableName = "a" + tableName;
        fragment1.setTableName(tableName);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView

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



        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //adapter = new ScheduleAdapter();

                //////테이블이름 생성/////////////////////
                String year_st, month_st, day_st;
                int year__int = year - 2000; //2000년부터 정상작동,,
                if (year__int < 10) year_st = '0' + Integer.toString(year__int);
                else year_st = Integer.toString(year__int);
                if (month+1 < 10) month_st = '0' + Integer.toString(month+1);
                else month_st = Integer.toString(month+1);
                if (dayOfMonth < 10) day_st = '0' + Integer.toString(dayOfMonth);
                else day_st = Integer.toString(dayOfMonth);
                tableName = year_st + month_st + day_st;
                tableName = "a" + tableName;

                fragment1.setTableName(tableName);
                if(fragment1.listView!=null){
                    fragment1.adapter = new ScheduleAdapter();
                    fragment1.db.execSQL("CREATE TABLE IF NOT EXISTS " + fragment1.tableName + " (schedule VARCHAR);");//schedule 칼럼 1개 있는 테이블 추가
                    fragment1.DB_add();
                    fragment1.listView.setAdapter(fragment1.adapter);
                }

            }
        });



        //Table 생성
        //db.createTable(tableName);
        //db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (schedule VARCHAR);");//schedule 칼럼 1개 있는 테이블 추가
        // adapter.DB_add();

        //listView.setAdapter(adapter);
    }
}