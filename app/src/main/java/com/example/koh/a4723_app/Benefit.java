package com.example.koh.a4723_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class Benefit extends AppCompatActivity {
    SQLiteDatabase db= null;
    SharedPreferences pref = null;
    String tableName = "";
    public void saveBenefits(){//레코드 입력 함수
        pref.edit().remove("save").apply();
        pref.edit().putInt("save", 1).apply();
        db.execSQL("CREATE TABLE IF NOT EXISTS YangYang (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//양양군
        db.execSQL("CREATE TABLE IF NOT EXISTS JeongSeon (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//정선군
        db.execSQL("CREATE TABLE IF NOT EXISTS SamCheok (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//삼척시
        db.execSQL("CREATE TABLE IF NOT EXISTS PyeongChang (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//평창군
        //==================================================================================양양군
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','64','표준모자보건수첩 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','12','엽산제 무료 지급');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','16','48','철분제 무료 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','40','유산균제 1회 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','44','신생아 청각선별검사');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','34','44','바우처 서비스 신청');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','64','의료비 지원사업');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','44','출산장려금 지원사업');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','48','저소득층 기저귀·조제분유 지원사업');");
        //==================================================================================정선군
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','0','12','엽산제 제공');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','16','40','철분제 제공');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','44','출산육아용품 상품권 지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','64','미숙아·선천성이상아 의료비지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','28','44','신생아 청각선별검사 지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','34','44','산모·신생아건강관리 지원사업');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','64','고위험임산부 의료비 지원사업');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','134','저소득층 기저귀·조제분유 지원사업');");
        //==================================================================================삼척시
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','20','40','고위험 임산부 의료비지원 사업');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','34','44','산모신생아 건강관리사업');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','134','저소득층 기저귀 조제분유 지원사업');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','28','44','신생아 난청조기진단 검사');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','44','출산장려금 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','44','출생아 건강보장보험료 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','32','44','공공산후조리원 이용료 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','0','40','임산부 산전검진비 지원');");
        //==================================================================================평창군
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','16','40','임산부 산전검진비 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','0','12','임산부 산전검진비 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','64','임산부 산전검진비 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','134','임산부 산전검진비 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','48','임산부 산전검진비 지원');");
        //==================================================================================

    }
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
        pref = getSharedPreferences("isSaved", Context.MODE_PRIVATE);
        int check = pref.getInt("save", 0);
        if(check==0) saveBenefits(); //한번도 DB 저장한 적이 없을 때만 레코드 입력

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