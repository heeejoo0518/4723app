package com.example.koh.a4723_app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class Benefit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("혜택");
        setSupportActionBar(toolbar);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        for(int i = 0; i < 5; i++) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(i+1+"번째 checkbox");
            cb.setTextColor(Color.BLACK);
            linearLayout.addView(cb);
            final int finalI = i;
            cb.setOnClickListener(new CheckBox.OnClickListener() {
                @Override public void onClick(View v) {
                    //Toast.makeText(getApplicationContext() , "클릭함", Toast.LENGTH_SHORT).show();
                    if (((CheckBox)v).isChecked()) {
                        Toast.makeText(getApplicationContext() , finalI +1+"OOO", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext() , finalI +1+"XXX", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.linearlayout2);
        for(int i = 0; i < 10; i++) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(i+1+"번째 checkbox");
            cb.setTextColor(Color.BLACK);
            linearLayout2.addView(cb);
            final int finalI = i;
            cb.setOnClickListener(new CheckBox.OnClickListener() {
                @Override public void onClick(View v) {
                    //Toast.makeText(getApplicationContext() , "클릭함", Toast.LENGTH_SHORT).show();
                    if (((CheckBox)v).isChecked()) {
                        Toast.makeText(getApplicationContext() , finalI +1+"OOO", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext() , finalI +1+"XXX", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}