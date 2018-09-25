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
import com.example.koh.a4723_app.gangneug.gangneug_0;
import com.example.koh.a4723_app.gangneug.gangneug_1;
import com.example.koh.a4723_app.gangneug.gangneug_2;
import com.example.koh.a4723_app.gangneug.gangneug_3;
import com.example.koh.a4723_app.gangneug.gangneug_4;
import com.example.koh.a4723_app.gangneug.gangneug_5;
import com.example.koh.a4723_app.gangneug.gangneug_6;
import com.example.koh.a4723_app.gangneug.gangneug_7;
import com.example.koh.a4723_app.gangneug.gangneug_8;

public class Health_gangneug extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "어떻게 할지 생각해보기", "출산장려금 지원",
            "신생아청각선별검사", "산모신생아 건강관리사지원", "고위험 임산부 의료비 지원","선천성대사이상 환아 지원",
            "난임시술비지원","청소년산모 임신,출산 의료비지원",
            "출산 후 의료,약제비지원","영양플러스사업"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_gangneug);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("강릉시 보건소");
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
                Intent intent = new Intent(Health_gangneug.this, gangneug_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_gangneug.this, gangneug_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_gangneug.this, gangneug_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_gangneug.this, gangneug_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_gangneug.this, gangneug_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_gangneug.this, gangneug_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_gangneug.this, gangneug_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_gangneug.this, gangneug_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_gangneug.this, gangneug_8.class);
                startActivity(intent);

            }


        }

    };
}
