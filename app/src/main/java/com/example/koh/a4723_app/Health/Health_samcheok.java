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
import com.example.koh.a4723_app.samcheok.samcheok_0;
import com.example.koh.a4723_app.samcheok.samcheok_1;
import com.example.koh.a4723_app.samcheok.samcheok_2;
import com.example.koh.a4723_app.samcheok.samcheok_3;
import com.example.koh.a4723_app.samcheok.samcheok_4;
import com.example.koh.a4723_app.samcheok.samcheok_5;

public class Health_samcheok extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "영유아 건강검진", "모성아동건강관리 사업",
            "영유아 사전예방적 관리", "인구증가 시책사업", "여성과어린이건강증진",
            "영양보충사업"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_samcheok);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("삼척시 보건소");
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
                Intent intent = new Intent(Health_samcheok.this, samcheok_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_samcheok.this, samcheok_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_samcheok.this, samcheok_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_samcheok.this, samcheok_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_samcheok.this, samcheok_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_samcheok.this, samcheok_5.class);
                startActivity(intent);
            }

        }

    };
}
