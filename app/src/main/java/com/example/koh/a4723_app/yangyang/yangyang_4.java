package com.example.koh.a4723_app.yangyang;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.koh.a4723_app.R;

public class yangyang_4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yangyang_4);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("양양군 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
    }
}
