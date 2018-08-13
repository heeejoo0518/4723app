package com.example.koh.a4723_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Pregnant_Week = (Button) findViewById(R.id.Pregnant_Week);
        Button Health_Service = (Button) findViewById(R.id.Health_Service);
        Button find_hospital = (Button) findViewById(R.id.find_hospital);
        Button Calendar = (Button) findViewById(R.id.Calendar);

        Pregnant_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pregnant_Week.class);
                startActivity(intent);
            }
        });


        Health_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Health_Service.class);
                startActivity(intent);
            }
        });

        find_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, find_hospital.class);
                startActivity(intent);
            }
        });

        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });


    }
}
