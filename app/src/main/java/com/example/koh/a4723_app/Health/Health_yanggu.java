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
import com.example.koh.a4723_app.yanggu.yanggu_0;
import com.example.koh.a4723_app.yanggu.yanggu_1;
import com.example.koh.a4723_app.yanggu.yanggu_2;
import com.example.koh.a4723_app.yanggu.yanggu_3;
import com.example.koh.a4723_app.yanggu.yanggu_4;
import com.example.koh.a4723_app.yanggu.yanggu_5;
import com.example.koh.a4723_app.yanggu.yanggu_6;
import com.example.koh.a4723_app.yanggu.yanggu_7;

public class Health_yanggu extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "체외수정시술비지원사업", "산모·신생아 도우미지원사업 ", "선천성대사이상검사",
            "미숙아 및 선천성이상아 의료비지원", "영유아 건강진단",
            "임산부 철분제 지원","양구군 출생아 건강보험 가입지원","출산장려금 지원"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_yanggu);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("양구군 보건소");
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
                Intent intent = new Intent(Health_yanggu.this, yanggu_0.class);
                intent.putExtra("b", items[0]);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_yanggu.this, yanggu_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_yanggu.this, yanggu_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_yanggu.this, yanggu_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_yanggu.this, yanggu_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_yanggu.this, yanggu_5.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_yanggu.this, yanggu_6.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_yanggu.this, yanggu_7.class);
                startActivity(intent);
            }


        }

    };
}
