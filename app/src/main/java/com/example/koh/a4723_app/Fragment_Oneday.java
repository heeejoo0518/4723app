package com.example.koh.a4723_app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Oneday extends Fragment {
    ListView listView;
    TextView textView;
    OnedayAdapter adapter;
    SQLiteDatabase db;
    Cursor c;
    String tableName;
    String date;//date 칼럼 값 저장
    AlertDialog.Builder builder;

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
        setS(date);
        setT(tableName);

        textView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().equals("기분을 추가해주세요")) showMessage_add();
                else showMessage_delete();
            }
        }));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)adapter.getItem(position);
                showMessage_schedule(item);
            }
        });

    }

    public void showMessage_delete(){
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //YES
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSQL("DELETE FROM Table_status WHERE date = '" + date + "';");
                setS(date);
                ((Calendar)getActivity()).clickStatus();// 상태 클릭 시 activity로 업데이트를 알려줌
            }
        });

        //NO
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //만들어주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMessage_add(){
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("추가하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //YES
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Calendar)getActivity()).setFragment2();
            }
        });

        //NO
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //만들어주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMessage_schedule(final String item){
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //YES
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSQL("DELETE FROM "+ tableName +" WHERE schedule = '" + item + "';");
                setT(tableName);
            }
        });

        //NO
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //MODIFY
        builder.setNeutralButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage_modify(item);
            }
        });

        //만들어주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMessage_modify(final String item){
        builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        final EditText input = new EditText(getActivity());
        builder.setView(input);

        //YES
        builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                if(value.equals("")) Toast.makeText(getActivity(),"수정할 문구를 입력해주세요",Toast.LENGTH_SHORT).show();
                else {
                    db.execSQL("UPDATE "+ tableName +" SET schedule = '"+ value +"' WHERE schedule = '" + item + "';");
                    setT(tableName);
                }

            }
        });

        //NO
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //만들어주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void setT(String tableName){//tableName 받고 adapter와 새로 연결
        setTableName(tableName);
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
        setDate(date);
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
