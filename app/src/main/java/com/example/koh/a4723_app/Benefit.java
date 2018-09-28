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
import android.widget.TextView;

import java.util.ArrayList;

class benefits{
    private int start;
    private int end;
    private CheckBox checkBox;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
    public void add(int start,int end, CheckBox cb){
        this.start=start;
        this.end=end;
        this.checkBox=cb;
    }
}

public class Benefit extends AppCompatActivity {
    SQLiteDatabase db= null;
    String tableName = "";
    LinearLayout linearLayout;
    LinearLayout linearLayout2;
    public ArrayList<benefits> b_all(){
        ArrayList<benefits> checkBoxes = new ArrayList<>();
        final String tbName=center(getSharedPreferences("pref", Context.MODE_PRIVATE).getString("보건소",""));
        if(tbName.equals("해당하는 보건소를 등록해주세요.")) return checkBoxes;

        Cursor c = db.rawQuery("SELECT * FROM " + tbName, null);
        if(c.moveToFirst()){
            do {
                int start = c.getInt(c.getColumnIndex("_start"));
                int end = c.getInt(c.getColumnIndex("_end"));
                CheckBox cb = new CheckBox(getApplicationContext());
                final String get = c.getString(c.getColumnIndex("get"));
                //String str=start+"주~"+end+"주 "+get;
                cb.setText(get);
                cb.setTextColor(Color.BLACK);

                if(c.getInt(c.getColumnIndex("checked"))==0) cb.setChecked(false);
                else cb.setChecked(true);

                cb.setOnClickListener(new CheckBox.OnClickListener() {
                    @Override public void onClick(View v) {
                        if (((CheckBox)v).isChecked()) {
                            db.execSQL("UPDATE "+tbName+" SET checked = '1' WHERE get='"+get+"';");
                            setLinear();
                            } else {
                            db.execSQL("UPDATE "+tbName+" SET checked = '0' WHERE get='"+get+"';");
                            setLinear();
                        }
                    }
                });
                benefits be = new benefits();
                be.add(start,end,cb);
                checkBoxes.add(be);
            }while(c.moveToNext());
        }
        return checkBoxes;
    }
    public static String center(String center){ //보건소 테이블 이름 설정
        String tbName="";
        switch(center){
            case "양양군보건소": tbName="YangYang"; break;
            case "정선군보건소": tbName="JeongSeon"; break;
            case "삼척시보건소": tbName="SamCheok"; break;
            case "미탄보건지소": tbName="PyeongChang"; break;
            case "영월군보건소": tbName="YeongWol"; break;
            case "고성군보건소": tbName="Goseong"; break;
            case "강릉시보건소": tbName="GangNeung"; break;
            case "인제군보건소": tbName="InJe"; break;
            case "태백시보건소": tbName="TaeBaek"; break;
            case "양구군보건소": tbName="YangGu"; break;
            case "원주시보건소": tbName="WonJu"; break;
            case "춘천시보건소": tbName="ChunCheon"; break;
            case "속초시보건소": tbName="SokCho"; break;
            case "홍천군보건소": tbName="HongCheon"; break;
            case "동해시보건소": tbName="DongHae"; break;
            case "횡성군보건소": tbName="Hoengseong"; break;
            case "철원군보건소": tbName="ChearWon"; break;
            default: tbName="해당하는 보건소를 등록해주세요."; break; //보건소가 저장되지 않았을 때
        }
        return tbName;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit);
        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        linearLayout2 = (LinearLayout)findViewById(R.id.linearlayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("혜택");
        setSupportActionBar(toolbar);

        db = this.openOrCreateDatabase("Benefit", MODE_PRIVATE, null);//db이름: Benefit

        String center = getSharedPreferences("pref", Context.MODE_PRIVATE).getString("보건소","");
        tableName=center(center); //tableName 설정

        setLinear();

    }
    public void setLinear(){
        linearLayout.removeAllViews();
        linearLayout2.removeAllViews();
        int week = (int)getSharedPreferences("pref", MODE_PRIVATE).getLong("몇주차",0);
        ArrayList<benefits> checkBoxes = b_all();
        if(checkBoxes.size()<=0) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText("해당하는 보건소를 등록해주세요.");
            TextView textView2 = new TextView(getApplicationContext());
            textView2.setText("해당하는 보건소를 등록해주세요.");
            linearLayout.addView(textView);
            linearLayout2.addView(textView2);
            return;
        }
        if(getSharedPreferences("pref", MODE_PRIVATE).getString("날짜","").equals("")) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText("마지막 월경 날짜를 입력해주세요.");
            TextView textView2 = new TextView(getApplicationContext());
            textView2.setText("마지막 월경 날짜를 입력해주세요.");
            linearLayout.addView(textView);
            linearLayout2.addView(textView2);
            return;
        }
        for(int i = 0; i < checkBoxes.size(); i++) {
            if(week>=checkBoxes.get(i).getStart() && week <=checkBoxes.get(i).getEnd()){
                linearLayout.addView(checkBoxes.get(i).getCheckBox());
            }
        }

        ArrayList<benefits> CBs = b_all();
        for(int i = 0; i < CBs.size(); i++) {
            linearLayout2.addView(CBs.get(i).getCheckBox());
        }
    }
}