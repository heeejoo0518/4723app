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
    String date;//date 칼럼 값 저장

    public void setDB(SQLiteDatabase db){
        this.db = db;
    }
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    public void setDate(String date){
        this.date = date;
    }

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

        /*if(c.getCount()<=0) {
            textView.setText("기분을 추가해주세요");
        }
        else {
//            String Status = c.getString(1);
            textView.setText("");
            //textView.setText(Status);
        }*/

        setS(date);
        setT(tableName);

    }

    public void setT(String tableName){//tableName 받고 adapter와 새로 연결
        c = db.rawQuery("SELECT schedule FROM " + tableName, null);
        adapter = new OnedayAdapter();
        if(c.moveToFirst()){
            do {
                adapter.addItem(c.getString(c.getColumnIndex("schedule")));
            }while(c.moveToNext());

        }
        listView.setAdapter(adapter);
    }

    public void setS(String date){//새로 date로 status 설정
        c = db.rawQuery("SELECT * FROM Table_status",null);
        if(c.moveToFirst()){
            while(true){
                if(date.equals(c.getString(c.getColumnIndex("date")))) {
                    String Status = c.getString(c.getColumnIndex("status"));
                    textView.setText(Status);
                    break;
                }
                if(!c.moveToNext()) {
                    textView.setText("기분을 추가해주세요");
                    break;
                }
            }
        }
    }
}
