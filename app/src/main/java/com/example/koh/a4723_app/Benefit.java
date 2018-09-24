package com.example.koh.a4723_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Benefit extends AppCompatActivity {
    SQLiteDatabase db= null;
    String tableName = "";

    public ArrayList<CheckBox> b_current(){
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        final String tbName=center(getSharedPreferences("pref", Context.MODE_PRIVATE).getString("보건소",""));
        Cursor c = db.rawQuery("SELECT * FROM " + tbName, null);
        int week = (int)getSharedPreferences("pref", MODE_PRIVATE).getLong("몇주차",0);
        if(c.moveToFirst()){
            do {
                int start = c.getInt(c.getColumnIndex("_start"));
                int end = c.getInt(c.getColumnIndex("_end"));
                if(week>=start && week <=end){
                    CheckBox cb = new CheckBox(getApplicationContext());
                    final String get = c.getString(c.getColumnIndex("get"));
                    cb.setText(get);
                    cb.setTextColor(Color.BLACK);

                    if(c.getInt(c.getColumnIndex("checked"))==0) cb.setChecked(false);
                    else cb.setChecked(true);

                    cb.setOnClickListener(new CheckBox.OnClickListener() {
                        @Override public void onClick(View v) {
                            if (((CheckBox)v).isChecked()) {
                                Toast.makeText(getApplicationContext() , "OOO", Toast.LENGTH_SHORT).show();
                                db.execSQL("UPDATE "+tbName+" SET checked = '1' WHERE get='"+get+"';");
                            } else {
                                Toast.makeText(getApplicationContext() , "XXX", Toast.LENGTH_SHORT).show();
                                db.execSQL("UPDATE "+tbName+" SET checked = '0' WHERE get='"+get+"';");
                            }
                        }
                    });
                    checkBoxes.add(cb);
                }
            }while(c.moveToNext());
        }
        return checkBoxes;
    }
    public ArrayList<CheckBox> b_all(){
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        final String tbName=center(getSharedPreferences("pref", Context.MODE_PRIVATE).getString("보건소",""));
        Cursor c = db.rawQuery("SELECT * FROM " + tbName, null);
        if(c.moveToFirst()){
            do {
                int start = c.getInt(c.getColumnIndex("_start"));
                int end = c.getInt(c.getColumnIndex("_end"));
                CheckBox cb = new CheckBox(getApplicationContext());
                final String get = c.getString(c.getColumnIndex("get"));
                String str=start+"주~"+end+"주 "+get;
                cb.setText(str);
                cb.setTextColor(Color.BLACK);

                if(c.getInt(c.getColumnIndex("checked"))==0) cb.setChecked(false);
                else cb.setChecked(true);

                cb.setOnClickListener(new CheckBox.OnClickListener() {
                    @Override public void onClick(View v) {
                        if (((CheckBox)v).isChecked()) {
                            Toast.makeText(getApplicationContext() , "OOO", Toast.LENGTH_SHORT).show();
                            db.execSQL("UPDATE "+tbName+" SET checked = '1' WHERE get='"+get+"';");
                            } else {
                            Toast.makeText(getApplicationContext() , "XXX", Toast.LENGTH_SHORT).show();
                            db.execSQL("UPDATE "+tbName+" SET checked = '0' WHERE get='"+get+"';");
                        }
                    }
                });
                checkBoxes.add(cb);
            }while(c.moveToNext());
        }
        return checkBoxes;
    }
    public static String center(String center){
        String tbName="";
        switch(center){
            case "양양군보건소": tbName="YangYang"; break;
            case "정선군보건소": tbName="JeongSeon"; break;
            case "삼척시보건소": tbName="SamCheok"; break;
            case "평창군보건의료원": tbName="PyeongChang"; break;
            case "영월군보건소": break;
            case "강원도 고성군보건소": break;
            case "춘천시남면보건지소": break;
            case "주문진보건출장소": break;
            case "강릉시보건소": break;
            case "인제군보건소": break;
            case "태백시보건소": break;
            case "양구군보건소": break;
            case "원주시보건소": break;
            case "춘천시보건소": break;
            case "속초시보건소": break;
            case "홍천군보건소": break;
            case "동해시보건소": break;
            case "횡성군보건소": break;
            case "철원군보건소": break;
            default: tbName="해당하는 보건소를 등록해주세요."; break; //보건소가 저장되지 않았을 때
        }
        return tbName;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.linearlayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("혜택");
        setSupportActionBar(toolbar);

        db = this.openOrCreateDatabase("Benefit", MODE_PRIVATE, null);//db이름: Benefit

        String center = getSharedPreferences("pref", Context.MODE_PRIVATE).getString("보건소","");
        tableName=center(center); //tableName 설정

        ArrayList<CheckBox> checkBoxes = b_current();
        for(int i = 0; i < checkBoxes.size(); i++) {
           linearLayout.addView(checkBoxes.get(i));
        }

        ArrayList<CheckBox> CBs = b_all();
        for(int i = 0; i < CBs.size(); i++) {
            linearLayout2.addView(CBs.get(i));
        }

    }
}