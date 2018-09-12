package com.example.koh.a4723_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class My_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__page);
        final Context context = this;
        Button save_name = (Button)findViewById(R.id.save_name);
        Button save_date = (Button)findViewById(R.id.save_date);
        Button save_date_lastday = (Button)findViewById(R.id.save_date_lastday);
        Button save_healthcenter = (Button)findViewById(R.id.save_health_center);
        Button delete_all = (Button)findViewById(R.id.delete_all);

        final TextView baby_name_txt = (TextView) findViewById(R.id.baby_text2);

        String baby_name = getPreferences("아기이름");

        if(baby_name !=null){
            baby_name_txt.setText(baby_name);
        }

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


    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }


    void name_show()
    {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("아기 이름 저장");
        builder.setMessage("아기의 이름을 입력해주세요");
        builder.setView(edittext);
        builder.setPositiveButton("저장",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        intent.putExtra("아기이름",edittext.getText().toString());
                        setResult(RESULT_OK, intent) ;
                        ((MainActivity)(MainActivity.mContext)).onResume();

                        Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();


                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    void date_show(){
        CustomDialog_DueDate dialog = new CustomDialog_DueDate(this);
        dialog.show();



    }

    void last_day_show(){
        CustomDialog_LastDay dialog_2 = new CustomDialog_LastDay(this);
        dialog_2.show();



    }

    void health_center_show(){
        CustomDialog_HealthCenter dialog_3 = new CustomDialog_HealthCenter(this);
        dialog_3.show();


    }


    public void onResume() {
        super.onResume();

        TextView name = (TextView) findViewById(R.id.baby_text2);
        TextView duedate = (TextView) findViewById(R.id.delivery_text2);
        TextView lastday = (TextView) findViewById(R.id.last_day2);
        TextView healthcenter= (TextView) findViewById(R.id.health_center2);

        String name_ = getPreferences("아기이름");
        String duedate_ = getPreferences("출산날짜");
        String lastday_ = getPreferences("날짜");
        String healthcenter_ = getPreferences("보건소");

        if(name_ !=""){
            name.setText(name_);
        }
        if(duedate_ !=""){
            duedate.setText(duedate_);
        }
        if(lastday_ !=""){
            lastday.setText(lastday_);
        }
        if(healthcenter_ !=""){
            healthcenter.setText(healthcenter_);
        }
    }

}