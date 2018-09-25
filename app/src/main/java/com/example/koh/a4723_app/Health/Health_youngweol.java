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
import com.example.koh.a4723_app.youngweol.youngweol_0;
import com.example.koh.a4723_app.youngweol.youngweol_1;
import com.example.koh.a4723_app.youngweol.youngweol_2;
import com.example.koh.a4723_app.youngweol.youngweol_3;
import com.example.koh.a4723_app.youngweol.youngweol_4;
import com.example.koh.a4723_app.youngweol.youngweol_5;
import com.example.koh.a4723_app.youngweol.youngweol_6;
import com.example.koh.a4723_app.youngweol.youngweol_7;
import com.example.koh.a4723_app.youngweol.youngweol_8;

public class Health_youngweol extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 건강관리", "신생아 난청조기진단(청각선별검사) 지원", "선천성대사이상검사",
            "산모 · 신생아 건강관리사 지원사업", "산후돌봄 지원사업(산모 · 신생아 건강관리 본인부담금 지원)",
            "산후건강관리 지원사업","청소년산모 임신출산 의료비 지원사업","영양플러스 보충식품 지원",
            "아기와 엄마가 함께하는 행복한 건강교실 운영 "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_youngweol);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("영월군 보건소");
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
                Intent intent = new Intent(Health_youngweol.this, youngweol_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_youngweol.this, youngweol_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_youngweol.this, youngweol_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_youngweol.this, youngweol_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_youngweol.this, youngweol_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_youngweol.this, youngweol_5.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_youngweol.this, youngweol_6.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_youngweol.this, youngweol_7.class);
                startActivity(intent);
            }
            if(position == 8){
                Intent intent = new Intent(Health_youngweol.this, youngweol_8.class);
                startActivity(intent);
            }

        }

    };
}
