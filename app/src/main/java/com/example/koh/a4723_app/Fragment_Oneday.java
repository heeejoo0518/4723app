package com.example.koh.a4723_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_Oneday extends Fragment {
    ListView listView;
    TextView textView;
    OnedayAdapter adapter;
    SQLiteDatabase db;
    Cursor c;
    String tableName;

    public void setDB(SQLiteDatabase db){
        this.db = db;
    }
    public void setTableName(String tableName){ this.tableName = tableName; }

    //onAttach -> onCreate -> onCreateView
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_oneday, container, false);
        listView = (ListView)rootView.findViewById(R.id.listview_oneday);
        textView = (TextView)rootView.findViewById(R.id.status_oneday);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //c = db.rawQuery()
        textView.setText("안뇽안뇽");
        set(tableName);

    }

    public void set(String tableName){
        c = db.rawQuery("SELECT schedule FROM " + tableName, null);
        adapter = new OnedayAdapter();
        if(c.moveToFirst()){
            do {
                adapter.addItem(c.getString(c.getColumnIndex("schedule")));
            }while(c.moveToNext());

        }
        listView.setAdapter(adapter);
    }
}
