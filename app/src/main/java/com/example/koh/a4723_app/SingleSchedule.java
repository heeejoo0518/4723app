package com.example.koh.a4723_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class SingleSchedule  {
    String schedule_;
    public SingleSchedule(String sche){
        this.schedule_ = sche;
    }

    public String getSche() {
        return schedule_;
    }

    public void setSche(String sche) {
        this.schedule_ = sche;
    }

    @Override
    public String toString() {
        return schedule_;
    }
}
