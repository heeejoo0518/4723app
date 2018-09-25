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
import com.example.koh.a4723_app.wonju.wonju_0;
import com.example.koh.a4723_app.wonju.wonju_1;
import com.example.koh.a4723_app.wonju.wonju_2;
import com.example.koh.a4723_app.wonju.wonju_3;
import com.example.koh.a4723_app.wonju.wonju_4;

public class Health_wonju extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 아동 건강관리", "산모·신생아건강관리지원사업",
            "산모·신생아 건강관리 지원 본인부담금 지원사업",
            "산후 건강관리 지원사업(산후 의료비 지원)", "고위험임산부 의료비 지원사업"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_wonju);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("원주시 보건소");
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
                Intent intent = new Intent(Health_wonju.this, wonju_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_wonju.this, wonju_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_wonju.this, wonju_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_wonju.this, wonju_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_wonju.this, wonju_4.class);
                startActivity(intent);
            }


        }

    };
}
