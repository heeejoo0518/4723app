package com.example.koh.a4723_app.yangyang;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.koh.a4723_app.R;

import com.example.koh.a4723_app.Health.Health_yangyang;

public class yangyang_3 extends AppCompatActivity {

    private String[] items;

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
