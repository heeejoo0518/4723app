package com.example.koh.a4723_app.calendar;

import android.app.Activity;
import android.text.style.UnderlineSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class Calendar_EventDecorator implements DayViewDecorator {
    private HashSet<CalendarDay> dates;

    public Calendar_EventDecorator(Collection<CalendarDay> dates,Activity context) {
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new UnderlineSpan());
    }
}
