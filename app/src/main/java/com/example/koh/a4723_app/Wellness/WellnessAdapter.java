package com.example.koh.a4723_app.Wellness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koh.a4723_app.R;

import java.util.ArrayList;

public class WellnessAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<WellnessItem> data;
    private int layout;

    public WellnessAdapter(Context context, int layout, ArrayList<WellnessItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout  = layout;
    }

    public int getCount(){
        return data.size();
    }

    public String getItem(int position){
        return data.get(position).getCity();
    }

    public long getItemId(int position){
        return position;
    }


    public String getWell(int position){
        return data.get(position).getWellness();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        WellnessItem wellnessItem = data.get(position);

        ImageView profile = (ImageView) convertView.findViewById(R.id.profile);
        profile.setImageResource(wellnessItem.getProfile());

        TextView city = (TextView) convertView.findViewById(R.id.city);
        city.setText(wellnessItem.getCity());

        TextView wellness = (TextView) convertView.findViewById(R.id.wellness);
        wellness.setText(wellnessItem.getWellness());

        return convertView;
    }

}