package com.example.koh.a4723_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class Fragment_Status extends Fragment {
    final String tableName = "Table_status";
    StatusAdapter adapter;
    SQLiteDatabase db;
    String[] items;
    GridView gridView;
    String date;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        items = new String[]{"상태1","상태2","상태3","상태4","상태5","상태6"};

    }

    public void setDB(SQLiteDatabase db){
        this.db = db;
        //date,status 칼럼 두개 있는 테이블 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (date VARCHAR, status VARCHAR);");
    }

    public void setDate(String date){
        this.date = date;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_status, container, false);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new StatusAdapter();
        adapter.addItems(items);

        gridView = getView().findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //아이템 클릭 리스너
                String item = (String) adapter.getItem(position);
                db.execSQL("DELETE FROM Table_status WHERE date = '" + date + "';"); //Table_status 테이블의 date 칼럼 중에 현재 날짜와 같은 게 있으면 레코드 삭제
                db.execSQL("INSERT INTO " + tableName + "(date, status) Values ('" + date + "', '" + item + "');");
                ((Calendar)getActivity()).clickStatus();// 상태 클릭 시 activity로 업데이트를 알려줌
            }
        });

    }

    //현재 상태 업뎃용 arraylist 반환
    public ArrayList<String> update(){
        ArrayList<String> update = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + tableName, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String date = c.getString(c.getColumnIndex("date"));
                    update.add(date);
                } while (c.moveToNext());
            }
        }
        return update;
    }


}
