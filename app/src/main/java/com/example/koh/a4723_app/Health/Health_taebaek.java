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
import com.example.koh.a4723_app.taebaek.taebaek_0;
import com.example.koh.a4723_app.taebaek.taebaek_1;
import com.example.koh.a4723_app.taebaek.taebaek_2;
import com.example.koh.a4723_app.taebaek.taebaek_3;
import com.example.koh.a4723_app.taebaek.taebaek_4;
import com.example.koh.a4723_app.taebaek.taebaek_5;
import com.example.koh.a4723_app.taebaek.taebaek_6;

public class Health_taebaek extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 관리", "신생아 난청 조기 진단사업",
            "출산 준비 교실", "산모,신생아 도우지 지원 사업", "미숙아 및 선천성 이상아 의료비 지원 사업",
            "선천성 대사이상 검사 및 환아 관리","고위험 임산부 의료비 지원"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_taebaek);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("태백시 보건소");
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
                Intent intent = new Intent(Health_taebaek.this, taebaek_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_taebaek.this, taebaek_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_taebaek.this, taebaek_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_taebaek.this, taebaek_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_taebaek.this, taebaek_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_taebaek.this, taebaek_5.class);
                startActivity(intent);
            }
            if(position == 6) {
            Intent intent = new Intent(Health_taebaek.this, taebaek_6.class);
            startActivity(intent);
        }

        }

    };
}
