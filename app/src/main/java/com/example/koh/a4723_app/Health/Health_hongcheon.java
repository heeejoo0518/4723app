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
public class Health_hongcheon extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "영유아 건강관리", "영양제 지원",
            "교실운영", "미숙아 및 선천성 이상아 의료비 지원", "신생아 청각선별검사 무료 쿠폰 지원",
            "저소득층 기저귀,조제분유 지원", "난임부부 지원","고위험 임산부 의료비 사업 지원",
            "산모,신생아 건강관리사 지원","산모신생아건강관리 본인부담금 지원","산후건강관리지원"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_hongcheon);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("홍성군 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

       // _listview.setOnItemClickListener(onItemClickListener);
    }

    /*private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(position == 0){
                Intent intent = new Intent(Health_goseong.this, goseong_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_goseong.this, goseong_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_goseong.this, goseong_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_goseong.this, goseong_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_goseong.this, goseong_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_goseong.this, goseong_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_goseong.this, goseong_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_goseong.this, goseong_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_goseong.this, goseong_8.class);
                startActivity(intent);

            }
            if(position == 9){
                Intent intent = new Intent(Health_goseong.this, goseong_9.class);
                startActivity(intent);

            }


        }

    };*/
}
