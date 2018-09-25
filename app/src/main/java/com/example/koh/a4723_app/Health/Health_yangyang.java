package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.yangyang.yangyang_0;
import com.example.koh.a4723_app.yangyang.yangyang_1;
import com.example.koh.a4723_app.yangyang.yangyang_10;
import com.example.koh.a4723_app.yangyang.yangyang_2;
import com.example.koh.a4723_app.yangyang.yangyang_3;
import com.example.koh.a4723_app.yangyang.yangyang_4;
import com.example.koh.a4723_app.yangyang.yangyang_5;
import com.example.koh.a4723_app.yangyang.yangyang_6;
import com.example.koh.a4723_app.yangyang.yangyang_7;
import com.example.koh.a4723_app.yangyang.yangyang_8;
import com.example.koh.a4723_app.yangyang.yangyang_9;

public class Health_yangyang extends AppCompatActivity  {

    private ListView _listview;

    private String[] items = { "임산부 등록 관리", "미숙아 및 선천성이상아 의료비 지원",
            "찾아가는 산부인과", "난임부부 지원사업", "고위험 임산부 의료비 지원","청소년 산모 의료비 지원",
            "산모신생아 건강관리 지원사업","산모신생아 건강관리 및 부가서비스 본인부담금 지원 신청",
            "산후 건강관리 의료비지원사업","출산장려금 지원사업","저소득층 기저귀•조제분유 지원사업"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_yangyang);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("양양군 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        _listview.setOnItemClickListener(onItemClickListener);

       // frag1 = new Frag1();
       // setFrag(0);

    }



    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(position == 0){
               Intent intent = new Intent(Health_yangyang.this, yangyang_0.class);
               startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_yangyang.this, yangyang_1.class);
                startActivity(intent);
            }
            if(position == 2){
              //  setFrag(2);
                Intent intent = new Intent(Health_yangyang.this, yangyang_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_yangyang.this, yangyang_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_yangyang.this, yangyang_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_yangyang.this, yangyang_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_yangyang.this, yangyang_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_yangyang.this, yangyang_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_yangyang.this, yangyang_8.class);
                startActivity(intent);

            }

            if(position == 9){
                Intent intent = new Intent(Health_yangyang.this, yangyang_9.class);
                startActivity(intent);

            }
            if(position==10){
                Intent intent = new Intent(Health_yangyang.this, yangyang_10.class);
                startActivity(intent);

            }
        }

    };

    }

