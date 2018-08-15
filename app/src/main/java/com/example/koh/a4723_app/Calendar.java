package com.example.koh.a4723_app;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class Calendar extends Activity {
    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleCalendarView = (CalendarView)findViewById(R.id.simpleCalendarView); // get the reference of CalendarView

        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), year + "년 "+ month +"월 " + dayOfMonth + "일", Toast.LENGTH_LONG).show();
            }
        });

        //ListView

        setContentView(R.layout.activity_calendar);
        final ListView listview = (ListView)findViewById(R.id.listview);
        final List<Add_Schedule> sche = new ArrayList<>();


        // ArrayList 객체에 데이터를 넣습니다.
        for( int i = 0; i< 100; i++) {
              sche.add(new Add_Schedule("test___schedule"));
        }

        final ScheduleAdapter scheAdapter = new ScheduleAdapter(this, sche, listview);
        listview.setAdapter(scheAdapter);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("@@@Changed", "mListView.getFirstVisiblePosition()="+ listview.getFirstVisiblePosition() );
                Log.d("@@@Changed", "mListView.getLastVisiblePosition()="+ listview.getLastVisiblePosition() );
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });

    }

}
