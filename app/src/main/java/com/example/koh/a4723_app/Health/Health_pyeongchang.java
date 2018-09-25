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
import android.widget.Toast;

import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_0;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_1;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_2;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_3;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_4;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_5;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_6;
import com.example.koh.a4723_app.pyeongchang.pyeongchang_7;

public class Health_pyeongchang extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 건강관리", "영유아 건강관리",
            "산모·신생아건강관리 지원사업", "난임부부지원", "청소년산모 임신출산 의료비지원",
            "고위험 임산부 의료비지원","기저귀·조제분유 지원사업", "임산부 산후돌봄 지원"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__pyeongchang);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("평창군 보건소");
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
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_pyeongchang.this, pyeongchang_7.class);
                startActivity(intent);

            }

        }

    };
}
