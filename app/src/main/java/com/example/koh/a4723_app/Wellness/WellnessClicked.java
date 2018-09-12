package com.example.koh.a4723_app.Wellness;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koh.a4723_app.R;

public class WellnessClicked extends Activity {
    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellness_clicked);

        Intent intent = getIntent();

        ImageView profile = (ImageView) findViewById(R.id.img_prof);
        TextView city = (TextView) findViewById(R.id.tv_city);
        TextView wellness = (TextView) findViewById(R.id.tv_wellness);

        img = Integer.parseInt(intent.getStringExtra("profile"));
        profile.setImageResource(img);
        city.setText(intent.getStringExtra("city"));
        wellness.setText(intent.getStringExtra("wellness"));
    }
}
