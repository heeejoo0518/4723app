package com.example.koh.a4723_app.adapter;


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
