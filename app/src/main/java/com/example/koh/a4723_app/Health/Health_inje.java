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
import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.inje.inje_0;
import com.example.koh.a4723_app.inje.inje_1;
import com.example.koh.a4723_app.inje.inje_2;
import com.example.koh.a4723_app.inje.inje_3;
import com.example.koh.a4723_app.inje.inje_4;
import com.example.koh.a4723_app.inje.inje_5;
import com.example.koh.a4723_app.inje.inje_6;
import com.example.koh.a4723_app.inje.inje_7;
import com.example.koh.a4723_app.inje.inje_8;

public class Health_inje extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 산전검사 쿠폰제", "청소년산모 임신,출산 의료비지원",
            "신생아 청각 선별검사", "미숙아 및 선천성이상아 의료비 지원", "영유아 건강관리",
            "난임부부 지원사업","출산장려지원사업",
            "산모신생아건강관리사 지원","예비부모 만들기 프로젝트사업"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_inje);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("인제군 보건소");
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
                Intent intent = new Intent(Health_inje.this, inje_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_inje.this, inje_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_inje.this, inje_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_inje.this, inje_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_inje.this, inje_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_inje.this, inje_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_inje.this, inje_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_inje.this, inje_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_inje.this, inje_8.class);
                startActivity(intent);

            }
        }

    };
}
