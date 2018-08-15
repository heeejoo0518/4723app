package com.example.koh.a4723_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class My_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__page);

        final Spinner spinner1 = (Spinner)findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        final Spinner spinner2 = (Spinner)findViewById(R.id.mySpinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.month, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner)findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.day, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        Button btn = (Button) findViewById(R.id.weeks_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Toast.makeText(My_Page.this,
                        spinner1.getSelectedItem() + " " + spinner2.getSelectedItem() + " " + spinner3.getSelectedItem() + " 을 선택하셨습니다.",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
