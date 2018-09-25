package com.example.koh.a4723_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.koh.a4723_app.R;

import java.util.ArrayList;

public class StatusAdapter extends BaseAdapter {
    ArrayList<String> items = new ArrayList<>();

    public StatusAdapter(){}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String item){
        items.add(item);
        this.notifyDataSetChanged();
    }
    public void addItems(String[] item){
        for(int i=0;i<item.length;i++){
            items.add(item[i]);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        StatusView view = null;
        Context context = parent.getContext();

        if (convertView == null){
            view = new StatusView(context);
        }else{
            view = (StatusView)convertView;
        }

        int drawable;
        switch(items.get(position)){
            case "매우좋음": drawable=R.drawable.feel_veryvery_happy;break;
            case "좋음": drawable=R.drawable.feel_happy;break;
            case "신남":drawable=R.drawable.feel_excited;break;
            case "설렘":drawable=R.drawable.feel_in_love;break;
            case "놀람":drawable=R.drawable.feel_surprised;break;
            case "슬픔":drawable=R.drawable.feel_crying;break;
            case "지침":drawable=R.drawable.feel_unhappy;break;
            case "아픔":drawable=R.drawable.feel_ill;break;
            default:drawable=R.drawable.feel_mad;break;//나쁨
        }

        view.setStatus(items.get(position),drawable);

        return view;

    }
}
