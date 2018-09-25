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
import com.example.koh.a4723_app.donghae.donghae_0;
import com.example.koh.a4723_app.donghae.donghae_1;
import com.example.koh.a4723_app.donghae.donghae_2;
import com.example.koh.a4723_app.donghae.donghae_3;
import com.example.koh.a4723_app.donghae.donghae_4;
import com.example.koh.a4723_app.donghae.donghae_5;
import com.example.koh.a4723_app.donghae.donghae_6;
import com.example.koh.a4723_app.donghae.donghae_7;
import com.example.koh.a4723_app.donghae.donghae_8;
import com.example.koh.a4723_app.donghae.donghae_9;

public class Health_donghae extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "국민행복카드 발급 안내", "보건소 임산부 등록 전 신혼부부 무료검진", "보건소 임산부 등록 후 지원현황",
            "산전 지원현황", "산후 지원현황","임부(청소년)등록관리","임산부 검사",
            "영양제 보급","모유수유실 운영","임산부 프로그램(행복한 예비맘 건강교실)" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_donghae);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("동해시 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        _listview.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            if(position == 0){
                Intent intent = new Intent(Health_donghae.this, donghae_0.class);
                startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(Health_donghae.this, donghae_1.class);
                startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(Health_donghae.this, donghae_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_donghae.this, donghae_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_donghae.this,donghae_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_donghae.this, donghae_5.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_donghae.this, donghae_6.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_donghae.this, donghae_7.class);
                startActivity(intent);
            }
            if(position == 8){
                Intent intent = new Intent(Health_donghae.this, donghae_8.class);
                startActivity(intent);
            }

            if(position == 9){
                Intent intent = new Intent(Health_donghae.this, donghae_9.class);
                startActivity(intent);
            }
        }

    };
      }



