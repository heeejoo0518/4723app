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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;



public class Fragment_Schedule extends Fragment {
    Calendar calendar;
    String tableName;
    EditText editText;
    ListView listView;
    Button button;
    ScheduleAdapter adapter;
    SQLiteDatabase db;
    ViewGroup rootView;
    ArrayList<SingleSchedule> items = new ArrayList<>();

    public void setDB(SQLiteDatabase db){
        this.db = db;
    }


    //onAttach -> onCreate -> onCreateView  프래그먼트 생명주기
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        calendar = (Calendar)getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_schedule, container, false);
        editText = (EditText)rootView.findViewById(R.id.editText);
        listView = (ListView)rootView.findViewById(R.id.listview);
        button = (Button)rootView.findViewById(R.id.button);
        return rootView;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void DB_add(){
        items=new ArrayList<>();
        Cursor c = db.rawQuery("SELECT schedule FROM " + tableName, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String schedule = c.getString(c.getColumnIndex("schedule"));
                    items.add(new SingleSchedule(schedule));
                } while (c.moveToNext());
            }
        }
        adapter.addItems(items);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ScheduleAdapter();
        DB_add();
        listView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT schedule FROM " + tableName, null);
                c.moveToLast();//커서 이동

                String sche = editText.getText().toString();
                long time = System.currentTimeMillis();
                db.execSQL("INSERT INTO " + tableName + "(time, schedule) Values ('" + time + "', '" + sche + "');");

                adapter.addItem(new SingleSchedule(sche));
                editText.setText("");//EditText 내용 삭제
                adapter.notifyDataSetChanged(); // 이 메소드를 호출하면 어댑터 쪽에서 리스트뷰를 갱신하라 함.
            }
        });

    }
}