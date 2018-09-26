package com.example.koh.a4723_app.Health;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koh.a4723_app.MainActivity;
import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.Wellness.WellnessAdapter;
import com.example.koh.a4723_app.Wellness.WellnessClicked;
import com.example.koh.a4723_app.Wellness.WellnessItem;
import com.example.koh.a4723_app.sokcho.sokcho_0;
import com.example.koh.a4723_app.sokcho.sokcho_1;
import com.example.koh.a4723_app.sokcho.sokcho_2;
import com.example.koh.a4723_app.sokcho.sokcho_3;
import com.example.koh.a4723_app.sokcho.sokcho_4;
import com.example.koh.a4723_app.sokcho.sokcho_5;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Health_Sokcho extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "영유아 건강검진", "모성아동건강관리 사업",
            "영유아 사전예방적 관리", "인구증가 시책사업", "여성과어린이건강증진",
            "영양보충사업"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__sokcho);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("속초시 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

       _listview.setOnItemClickListener(onItemClickListener);

    }
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
       public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(position == 0){
               Intent intent = new Intent(Health_Sokcho.this, sokcho_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_Sokcho.this, sokcho_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_Sokcho.this, sokcho_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_Sokcho.this, sokcho_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_Sokcho.this, sokcho_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_Sokcho.this, sokcho_5.class);
                startActivity(intent);
            }

        }

    };



}

