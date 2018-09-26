package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.content.SharedPreferences;

import com.example.koh.a4723_app.R;

import java.util.ArrayList;



public class Health_Service extends AppCompatActivity {

    //implements ListViewBtnAdapter.ListBtnClickListener
    Button button;
    ArrayList cityList;
    String str ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_health__service);

        //SharedPreferences sp = getSharedPreferences("보건소",MODE_PRIVATE);
        //health_center = sp.getString("보건소","");
        //String temp = getSharedPreferences("보건소", );


        String health_center = getPreferences("보건소");

       // Toast.makeText(getApplicationContext(),health_center, Toast.LENGTH_LONG).show();

        //str = "\""+"http://www.yonhapnews.co.kr/"+"\"";

        str = "http://health.yangyang.go.kr/page/dep/05_health/sub03/sub03_05.jsp";
        savePreferences("사이트주소",str);


        //툴바 설정=================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setTitle("보건 사업");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //==========================================

        if (health_center.equals("양양군보건소")) {
            { Intent intent = new Intent(Health_Service.this, Health_yangyang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("속초시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Sokcho.class);
                        startActivity(intent);
                        finish();
                    }
                } else if (health_center.equals("평창군보건의료원")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_pyeongchang.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("정선군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_jeongseon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("영월군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_youngweol.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("고성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_goseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("강릉시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_gangneug.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("인제군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_inje.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("양구군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_yanggu.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (health_center.equals("춘천시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chuncheon.class);
                        startActivity(intent);
                        finish();
                    }
                }else if (health_center.equals("홍천군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hongcheon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("동해시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_donghae.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("횡성군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_hseong.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("철원군보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_chulwon.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("원주시보건소")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_wonju.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (health_center.equals("태백시보건소")) {
                  {
                Intent intent = new Intent(Health_Service.this, Health_taebaek.class);
                startActivity(intent);
                finish();
                  }
                }
                else if (health_center.equals("삼척시보건소")) {
                    {
                Intent intent = new Intent(Health_Service.this, Health_samcheok.class);
                startActivity(intent);
                finish();
                    }
                }


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


}