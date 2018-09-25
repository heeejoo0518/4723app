package com.example.koh.a4723_app.yangyang;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.koh.a4723_app.R;

public class yangyang_2 extends AppCompatActivity {

    private String[] items = { "체외수정시술비지원사업", "산모·신생아 도우미지원사업 ", "선천성대사이상검사",
            "미숙아 및 선천성이상아 의료비지원", "영유아 건강진단",
            "임산부 철분제 지원","양구군 출생아 건강보험 가입지원","출산장려금 지원"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yangyang_1);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("양양군 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
    }
}
