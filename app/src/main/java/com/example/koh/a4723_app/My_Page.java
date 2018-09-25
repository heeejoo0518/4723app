package com.example.koh.a4723_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class My_Page extends AppCompatActivity {
    final String tableName = "Weight";
    private final String dbName = "Weight_DB";
    SQLiteDatabase Weight_db = null;
    public static Context mContext;
    Button save_name;
    Button save_date;
    Button save_date_lastday;
    Button save_healthcenter;
    Button delete_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__page);

        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("마이페이지");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        final Context context = this;
        save_name = (Button) findViewById(R.id.save_name);
        save_date = (Button) findViewById(R.id.save_date);
        save_date_lastday = (Button) findViewById(R.id.save_date_lastday);
        save_healthcenter = (Button) findViewById(R.id.save_health_center);
        delete_all = (Button) findViewById(R.id.delete_all);


        getWindow().setWindowAnimations(0);
        String baby_name = getPreferences("아기이름");



        save_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_show();
            }
        });

        save_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_show();
            }
        });

        save_date_lastday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                last_day_show();
            }
        });

        save_healthcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                health_center_show();
            }
        });

        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete_data_show();


            }
        });

    }

    private void savePreferences(String code, String str) { //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code) { //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code, "");
        return temp;

    }

    public void Delete_DB(){


        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        ReadDB.execSQL("DROP TABLE IF EXISTS '" + tableName + "'");
        ReadDB.close();

    }
    void name_show() {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("아기 이름 저장");
        builder.setMessage("아기의 이름을 입력해주세요");
        builder.setView(edittext);
        builder.setPositiveButton("저장",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(edittext.getText().toString().length() <1 ){
                            Toast.makeText(getApplicationContext(), "한글자 이상 입력해주세요", Toast.LENGTH_LONG).show();
                        }
                        savePreferences("아기이름", edittext.getText().toString());
                        finish();
                        Intent refresh = new Intent(My_Page.this, My_Page.class);
                        startActivity(refresh);
                        ((MainActivity) (MainActivity.mContext)).onResume();

                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    void delete_data_show() {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("내 정보 모두 삭제");
        builder.setMessage("삭제 후 복원은 불가능합니다");
        builder.setPositiveButton("삭제",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Delete_DB();

                        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
                        preferences.edit().remove("아기이름").commit();
                        preferences.edit().remove("출산날짜").commit();
                        preferences.edit().remove("날짜").commit();
                        preferences.edit().remove("보건소").commit();
                        preferences.edit().remove("자동계산").commit();

                        Toast.makeText(getApplicationContext(), "삭제가 완료 되었습니다", Toast.LENGTH_LONG).show();

                        finish();
                        Intent refresh = new Intent(My_Page.this, My_Page.class);
                        startActivity(refresh);


                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }


    void date_show() {
        CustomDialog_DueDate dialog = new CustomDialog_DueDate(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();


    }

    void last_day_show() {
        CustomDialog_LastDay dialog_2 = new CustomDialog_LastDay(this);
        dialog_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_2.show();


    }

    void health_center_show() {

        CustomDialog_HealthCenter dialog_3 = new CustomDialog_HealthCenter(this);
        dialog_3.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_3.show();
        MainActivity.btSet(BtBenefit());//저장될 때마다 MainActivity 버튼 text 바꾸는 함수 호출
    }


    public void onResume() {
        super.onResume();


        String name_ = getPreferences("아기이름");
        String duedate_ = getPreferences("출산날짜");
        String lastday_ = getPreferences("날짜");
        String healthcenter_ = getPreferences("보건소");
        String check = getPreferences("자동계산");


        if (name_.length() > 1) {
            save_name.setText(name_);
        }

        if (duedate_ != "" && check == "false") {

            String year = duedate_.substring(0, 4);
            String month = null;
            String day = null;
            if (duedate_.substring(4, 5).equals("1")) {
                month = duedate_.substring(4, 6);
            } else {
                month = duedate_.substring(5, 6);
            }
            if (duedate_.substring(6, 7).equals("1")) {
                day = duedate_.substring(6, 8);
            } else {
                day = duedate_.substring(7, 8);
            }

            duedate_ =  year + "년" + month + "월" + day + "일";
            save_date.setText(duedate_);

        }
        if (lastday_ !="" && check == "true") {
            save_date.setText("자동계산");
        }
        if (lastday_ != "") {

            String year = lastday_.substring(0, 4);
            String month = null;
            String day = null;
            if (lastday_.substring(4, 5).equals("1")) {
                month = lastday_.substring(4, 6);
            } else {
                month = lastday_.substring(5, 6);
            }
            if (lastday_.substring(6, 7).equals("1")) {
                day = lastday_.substring(6, 8);
            } else {
                day = lastday_.substring(7, 8);
            }
            lastday_ =  year + "년" + month + "월" + day + "일";

            save_date_lastday.setText(lastday_);
        }
        if (healthcenter_ != "") {
            save_healthcenter.setText(healthcenter_);
        }

    }
    public String BtBenefit(){//혜택정리 버튼에 표시할 텍스트 설정
        SQLiteDatabase db = this.openOrCreateDatabase("Benefit", MODE_PRIVATE, null);//db이름: Benefit
        String str="";
        String tbName = Benefit.center(getPreferences("보건소"));
        if(tbName.equals("해당하는 보건소를 등록해주세요.")) return tbName;
        if(getPreferences("자동계산").equals("")) {
            str="출산 예정일을 입력해주세요";
            return str;
        }

        Cursor c = db.rawQuery("SELECT * FROM " + tbName, null);
        int week = (int)getSharedPreferences("pref", MODE_PRIVATE).getLong("몇주차",0);
        if(c.moveToFirst()){
            do {
                int start = c.getInt(c.getColumnIndex("_start"));
                int end = c.getInt(c.getColumnIndex("_end"));
                if(week>=start && week <=end){
                    str+=c.getString(c.getColumnIndex("get"));
                    str+="\n";
                }
            }while(c.moveToNext());
        }
        db.close();
        return str.substring(0,str.length()-1);
    }
}