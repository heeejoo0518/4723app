package com.example.koh.a4723_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //툴바 설정
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setSubtitle("메인페이지^__________^"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.


        Button Pregnant_Week = (Button) findViewById(R.id.Pregnant_Week);
        Button Health_Service = (Button) findViewById(R.id.Health_Service);
        Button Find_Hospital = (Button) findViewById(R.id.Find_Hospital);
        Button Calendar = (Button) findViewById(R.id.Calendar);

        Intent intent = getIntent();
        String weeks = intent.getStringExtra("weeks");
        TextView my_weeks = (TextView) findViewById(R.id.my_weeks); // my_page 정보 보여주기
        my_weeks.setText(weeks);

        Pregnant_Week.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Pregnant_Week.class);
            startActivity(intent);
        }
    });


        Health_Service.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Health_Service.class);
            startActivity(intent);
        }
    });

        Find_Hospital.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Find_Hospital.class);
            startActivity(intent);
        }
    });

        Calendar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Calendar.class);
            startActivity(intent);
        }
    });

}

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.My_Page:
                Intent intent = new Intent(MainActivity.this,My_Page.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
