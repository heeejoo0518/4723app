package com.example.koh.a4723_app;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Health_Sokcho extends AppCompatActivity implements ListViewBtnAdapter.ListBtnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__sokcho);
        Toast.makeText(getApplicationContext(), "sokcho", Toast.LENGTH_LONG).show();


        ListView listview ;
        ListViewBtnAdapter adapter;
        ArrayList<ListViewBtnItem> items = new ArrayList<ListViewBtnItem>() ;

        // items 로드.
        //loadItemsFromDB(items) ;

        // Adapter 생성
        adapter = new ListViewBtnAdapter(this, R.layout.listview_btn_item, items, this) ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        }) ;
    }

    public void onListBtnClick(int position) {
        Toast.makeText(this, Integer.toString(position+1) + " Item is selected..", Toast.LENGTH_SHORT).show() ;
    }
   /* public boolean loadItemsFromDB(ArrayList<ListViewBtnItem> list) {
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
    }*/
}

