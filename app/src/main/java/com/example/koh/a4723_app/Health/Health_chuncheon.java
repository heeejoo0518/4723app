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
import com.example.koh.a4723_app.chuncheon.chuncheon_0;
import com.example.koh.a4723_app.chuncheon.chuncheon_1;
import com.example.koh.a4723_app.chuncheon.chuncheon_2;
import com.example.koh.a4723_app.chuncheon.chuncheon_3;
import com.example.koh.a4723_app.chuncheon.chuncheon_4;
import com.example.koh.a4723_app.chuncheon.chuncheon_5;
import com.example.koh.a4723_app.chuncheon.chuncheon_6;
import com.example.koh.a4723_app.chuncheon.chuncheon_7;
import com.example.koh.a4723_app.chuncheon.chuncheon_8;

public class Health_chuncheon extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "출산장려시책", "한부모가족 복지시설","저소득 한부모 가정지원",
            "청소년 한부모 자립지원", "임신출산 안내책자","임산부 지원사업",
            "난임부부 지원","한방난임 지원", "임산부 교실"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_chuncheon);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("춘천시 보건소");
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
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_8.class);
                startActivity(intent);

            }
        }

    };
}
