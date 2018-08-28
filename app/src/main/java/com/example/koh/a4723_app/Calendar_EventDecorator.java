package com.example.koh.a4723_app;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class Calendar_EventDecorator implements DayViewDecorator {
    //private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;

    public Calendar_EventDecorator(int color, Collection<CalendarDay> dates,Activity context) {
        //drawable = context.getResources().getDrawable(R.drawable.calendar_dot);
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.setSelectionDrawable(drawable);
        view.addSpan(new DotSpan(10, color));
    }
}
