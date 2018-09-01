package com.example.koh.a4723_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Health_Service extends Activity {

    //implements ListViewBtnAdapter.ListBtnClickListener
    Button button;
    ArrayList cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__service);


        cityList = new ArrayList();
        cityList.add("속초");
        cityList.add("평창");

        final String[] select_item = {""};

        final TextView tv = (TextView) findViewById(R.id.textView1);
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, cityList);
        s.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                select_item[0] = String.valueOf(cityList.get(arg2));
              /* int city = arg2;
                switch (city){
                    case 1:
                        tv.setText("선택한 지역은 " +parent.getItemAtPosition(position)+" 입니다");
                }
                tv.setText("선택한 지역은 " +arg0.getItemAtPosition(arg2)+" 입니다"); */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (select_item[0].equals("속초")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Sokcho.class);
                        startActivity(intent);
                        finish();
                    }
                } else if (select_item[0].equals("평창")) {
                    {
                        Intent intent = new Intent(Health_Service.this, Health_Pyeongchang.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });


     /*   ListView listview ;
        ListViewBtnAdapter btnadapter;
        ArrayList<ListViewBtnItem> items = new ArrayList<ListViewBtnItem>() ;

        // items 로드.
        //loadItemsFromDB(items) ;

        // Adapter 생성
        btnadapter = new ListViewBtnAdapter(this, R.layout.listview_btn_item, items, this) ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(btnadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        }) ;
    }

    public void onListBtnClick(int position) {
       // Toast.makeText(this, Integer.toString(position+1) + " Item is selected..", Toast.LENGTH_SHORT).show() ;
    }




    public boolean loadItemsFromDB(ArrayList<ListViewBtnItem> list) {
        ListViewBtnItem item ;
        int i ;

        if (list == null) {
            list = new ArrayList<ListViewBtnItem>() ;
        }

        // 순서를 위한 i 값을 1로 초기화.
        i = 1 ;

        // 아이템 생성.
        item = new ListViewBtnItem() ;
        item.setIcon(ContextCompat.getDrawable(this, R.drawable.heart)) ;
        item.setText(Integer.toString(i) + "번 아이템입니다.") ;
        list.add(item) ;
        i++ ;

        item = new ListViewBtnItem() ;
        item.setIcon(ContextCompat.getDrawable(this, R.drawable.heart)) ;
        item.setText(Integer.toString(i) + "번 아이템입니다.") ;
        list.add(item) ;
        i++ ;

        item = new ListViewBtnItem() ;
        item.setIcon(ContextCompat.getDrawable(this, R.drawable.heart)) ;
        item.setText(Integer.toString(i) + "번 아이템입니다.") ;
        list.add(item) ;
        i++ ;

        item = new ListViewBtnItem() ;
        item.setIcon(ContextCompat.getDrawable(this, R.drawable.heart)) ;
        item.setText(Integer.toString(i) + "번 아이템입니다.") ;
        list.add(item) ;

        return true ;
    } */

    }
}