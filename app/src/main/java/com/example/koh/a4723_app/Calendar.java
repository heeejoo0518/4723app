package com.example.koh.a4723_app;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.widget.CalendarView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;




public class Calendar extends Activity {
    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // red color for the selected week's background
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // green color for the week separator line
        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), year + "년 "+ month +"월 " + dayOfMonth + "일", Toast.LENGTH_LONG).show();
            }
        });

    }

}
